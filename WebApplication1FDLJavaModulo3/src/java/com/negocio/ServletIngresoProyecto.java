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
@WebServlet(name = "ServletIngresoProyecto", urlPatterns = {"/ServletIngresoProyecto"})
public class ServletIngresoProyecto extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */

            mysSqlConnect = new MysqlConnect();
            mysSqlConnect.efectuarConexionDB();

            String codUsuario = request.getParameter("codUsuario");
            String nomUsuario = request.getParameter("nomUsuario");
            String per_codigo = request.getParameter("per_codigo");
            String per_descripcion = request.getParameter("per_descripcion");

            String[] parametrosIngresoProyecto = new String[5];
            parametrosIngresoProyecto[0] = request.getParameter("txt_codigo");
            parametrosIngresoProyecto[1] = request.getParameter("txt_nombre");
            parametrosIngresoProyecto[2] = request.getParameter("txt_observaciones");
            parametrosIngresoProyecto[3] = request.getParameter("cmb_responsable");
            parametrosIngresoProyecto[4] = request.getParameter("cmb_catalogo");

            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "codUsuario: {0}", request.getParameter("codUsuario"));
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "nomUsuario: {0}", request.getParameter("nomUsuario"));
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "per_codigo: {0}", request.getParameter("per_codigo"));
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "per_descripcion: {0}", request.getParameter("per_descripcion"));
            
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "cmb_responsable: {0}", request.getParameter("cmb_responsable"));
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "cmb_catalogo: {0}", request.getParameter("cmb_catalogo"));

            mysSqlConnect.ejecutarSP(parametrosIngresoProyecto, leerProperties.leerArchivoPropiedades("proyecto.spIngresoProyecto"));

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<META HTTP-EQUIV='REFRESH' CONTENT='1;URL=jsp/menuopciones.jsp?codUsuario=" + codUsuario + "&" + "nomUsuario=" + nomUsuario + "&" + "per_codigo=" + per_codigo +"&" + "per_descripcion=" + per_descripcion + "'>");
            out.println("<script type='text/javascript'>alert('PROYECTO INGRESADO CORRECTAMENTE');</script>");
            out.println("</head>");
            out.println("<body>");
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
            Logger.getLogger(ServletIngresoProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServletIngresoProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServletIngresoProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletIngresoProyecto.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletIngresoProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServletIngresoProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServletIngresoProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletIngresoProyecto.class.getName()).log(Level.SEVERE, null, ex);
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
