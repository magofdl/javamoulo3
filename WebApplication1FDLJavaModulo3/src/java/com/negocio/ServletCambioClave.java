/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocio;

import com.datos.MysqlConnect;
import com.utilidades.leerProperties;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author magofdl
 */
@WebServlet(name = "ServletCambioClave", urlPatterns = {"/ServletCambioClave"})
public class ServletCambioClave extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        MysqlConnect mysSqlConnect = null;
        try (PrintWriter out = response.getWriter()) {

            mysSqlConnect = new MysqlConnect();

            mysSqlConnect.efectuarConexionDB();

            String[] credenciales = new String[3];
            credenciales[0] = request.getParameter("codUsuario");
            credenciales[1] = request.getParameter("txt_claveactual");
            credenciales[2] = request.getParameter("txt_clavenueva");

            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "usu_codigo: {0}", request.getParameter("usu_codigo"));
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "clave actual: {0}", request.getParameter("txt_claveactual"));
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "clave nueva: {0}", request.getParameter("txt_clavenueva"));

            ResultSet resultSet = mysSqlConnect.ejecutarSpConsulta(credenciales, leerProperties.leerArchivoPropiedades("usuario.spCambioClave"));

            String mensajeValidacion = "Credenciales no válidas.";
            if (resultSet.next()) {
                String resultaCambioClave = resultSet.getString("resultado");
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "resultaCambioClave: {0}", resultaCambioClave);
                if (resultaCambioClave.equalsIgnoreCase("1") == true) {//cambiada correctamente la contraseña
                    mensajeValidacion = "Contraseña cambiada exitosamente";
                    String urlLoginCorrecto = "index.jsp";
                    response.sendRedirect(urlLoginCorrecto);
                } else {
                    mensajeValidacion = "Por favor verifique su contraseña";

                    request.setAttribute("mensajeValidacion", mensajeValidacion);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/cambioclave.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "sin resultado sp ");
            }
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletCambioClave</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletCambioClave at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletCambioClave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServletCambioClave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServletCambioClave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletCambioClave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletCambioClave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServletCambioClave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServletCambioClave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletCambioClave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
