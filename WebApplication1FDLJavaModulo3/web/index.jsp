<%-- 
    Document   : index1
    Created on : 20-jun-2015, 8:24:09
    Author     : FDL
    Proposito  : Inicio del proyecto, contenido 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Centro de educación continua</title>
        <link rel="shortcut icon" href="imagenes/ingreso.ico">
        <link rel="stylesheet" href="css/presentacion.css" type="text/css">
        <link rel="stylesheet" href="css/textbox.css" type="text/css"
        <script type="text/javascript" scr="javascript/validaciones.js" ></script>
    </head>
    <body>
        
        <%--
        El request debe estar dentro del form. 
        action: el nombre del componente que va a recibir el request y la va procesar.
        onsubmit: que pasa cuando se quiere enviar el formulario, se dispara por ejemplo al dar un click.
        --%>  
        <%-- --%>  
        <form method="post" action=servlet_logeo" onsubmit="">
            <center>
                <table>
                    <tr>  <%-- fila --%>
                        <td>  <%-- columna --%>
                             <center>
                                  <%-- <img src="imagenes/logoCEC.jpg" width="" height=""> en pixeles--%>  
                                 <img src="imagenes/logoCEC.jpg">
                             </center>
                        </td>
                    </tr>
                    <tr>  
                        <td>
                            <center>
                                <b>Centro de Educación Continua </b>  <%-- En negrilla --%>
                            </center>
                        </td>
                    </tr>
                </table>      
            </center>
            <center>
                  <table>
                      <tr>
                          <td>
                              <b>Nombre de usuario:</b>
                          </td>
                          <td>
                              <input type="text" name="txt_nombreusuario" maxlength="20">
                          </td>
                      </tr> 
                      <tr>
                          <td>
                              <b>Contraseña:</b>
                          </td>
                          <td>
                              <input type="password" name="txt_contraseña" maxlength=30">
                          </td>
                      </tr> 
                      <tr>
                          <td colspan="2"><%--Fusionadas 2 columnas --%>
                               <center>
                                   <input type="submit" name="btn_validar" value="Ingresar">
                                </center>
                          </td>
                      </tr>
                  </table>
             </center>
        </form>
            
        <%
            String nombreUsuario="";
            int numeroAnios=12;
         %>
    </body>
</html>

