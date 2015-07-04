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
@WebServlet(name = "ServletProyectoConsultaCodigo", urlPatterns = {"/ServletProyectoConsultaCodigo"})
public class ServletProyectoConsultaCodigo extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");

        MysqlConnect mysSqlConnect = null;

        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CONSULTA DE PROYECTO POR CODIGO</title>");            
            out.println("<link rel='shortcut icon' href='imagenes/ingreso.ico'>");
            out.println("<link rel='stylesheet' href='css/tabla.css' type='text/css'/>");   
            out.println("<link rel='stylesheet' href='css/consultas.css' type='text/css'/>");   
            out.println("</head>");
            out.println("<body>");
            out.println("<table border='0' class='tabla'>");

            out.println("<th>");
            out.println("<b><center>Codigo</center></b>");
            out.println("</th");

            out.println("<th>");
            out.println("<b><center>Nombre</center></b>");
            out.println("</th");

            out.println("<th>");
            out.println("<b><center>Fecha de registro</center></b>");
            out.println("</th");

            out.println("<th>");
            out.println("<b><center>Responsable</center></b>");
            out.println("</th");

            mysSqlConnect = new MysqlConnect();

            mysSqlConnect.efectuarConexionDB();

            String[] parametros = new String[1];
            parametros[0] = request.getParameter("txt_codigo");

            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "txt_codigo: {0}", request.getParameter("txt_codigo"));

            ResultSet resultSet = mysSqlConnect.ejecutarSpConsulta(parametros, leerProperties.leerArchivoPropiedades("proyecto.spConsultaProyectoCodigo"));

            String mensajeValidacion = "Credenciales no válidas.";
            while (resultSet.next()) {

                String pro_codigo=resultSet.getString("pro_codigo");
                String pro_nombre=resultSet.getString("pro_nombre");
                String pro_fechareg=resultSet.getString("pro_fechareg");
                String per_nombres=resultSet.getString("per_nombres");
                String per_apellidos=resultSet.getString("per_apellidos");
                
                
                out.println("<tr class='modo1'>");
                out.println("<td>");
                out.println("<center>"+pro_codigo+"</center>");
                out.println("</td>");

                
                out.println("<td>");
                out.println("<center>"+pro_nombre+"</center>");
                out.println("</td>");
                
                
                out.println("<td>");
                out.println("<center>"+pro_fechareg+"</center>");
                out.println("</td>");
                
                
                out.println("<td>");
                out.println("<center>"+per_nombres+" "+per_apellidos+"</center>");
                out.println("</td>");
                
                out.println("</tr>");
            }
            
             out.println("</table>");
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
        } catch (SQLException ex) {
            Logger.getLogger(ServletProyectoConsultaCodigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletProyectoConsultaCodigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServletProyectoConsultaCodigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServletProyectoConsultaCodigo.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(ServletProyectoConsultaCodigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletProyectoConsultaCodigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServletProyectoConsultaCodigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServletProyectoConsultaCodigo.class.getName()).log(Level.SEVERE, null, ex);
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
