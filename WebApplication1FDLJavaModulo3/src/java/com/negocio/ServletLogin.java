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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CEC
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");

        MysqlConnect mysSqlConnect = null;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            mysSqlConnect = new MysqlConnect();

            mysSqlConnect.efectuarConexionDB();

            String[] credenciales = new String[2];
            credenciales[0] = request.getParameter("txt_nombreusuario");
            credenciales[1] = request.getParameter("txt_clave");

            String nombreUsuario = request.getParameter("txt_nombreusuario");
            ResultSet resultSet = mysSqlConnect.ejecutarSpConsulta(credenciales, leerProperties.leerArchivoPropiedades("usuario.spLogin"));

            String mensajeValidacion = "Credenciales no válidas.";
            if (resultSet.next()) {
                String per_descripcion = resultSet.getString("per_descripcion");
                String usu_nombrecompleto = resultSet.getString("usu_nombrecompleto");
                String usu_codigo = resultSet.getString("usu_codigo");
                mensajeValidacion = "Login Exitoso" + " Perfil: " + per_descripcion + " Nombre: " + usu_nombrecompleto + " Código: " + usu_codigo;
                String urlLoginCorrecto = "jsp/menuopciones.jsp?usu_codigo="+usu_codigo + "&usu_nombre=" + nombreUsuario + "&per_descripcion=" + per_descripcion;
                response.sendRedirect(urlLoginCorrecto);
            } else {
                mensajeValidacion = "Credenciales no válidas.";
                response.sendRedirect("paginas/errorlogeo.html");
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletLogeo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Usuario: " + request.getParameter("txt_nombreusuario"));
            out.println("<h1>Contraseña: " + request.getParameter("txt_clave"));
            out.println("<h1>Resultado: " + mensajeValidacion);
            out.println("<h1>Servlet ServletLogeo at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            if (mysSqlConnect != null) {
                mysSqlConnect.getConnection().close();
            }
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
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
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
