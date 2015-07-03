
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.negocio.AESManagerExternal"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.datos.*"%>
<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es-es">
<head>
    <title>MENU DE OPCIONES</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />        
    <script type="text/javascript"  src="../javascript/funciones.js"></script>            
    <link rel="stylesheet" href="../css/menu-12.css" type="text/css" />
    <link rel="shortcut icon" href="../imagenes/ingreso.ico">  
    <%
        String codUsuario=request.getParameter("usu_codigo");
        String nomUsuario=request.getParameter("usu_nombre");        
        String perfilUsuario=request.getParameter("per_descripcion");
        String per_codigo=request.getParameter("per_codigo");
        
        codUsuario = URLDecoder.decode(codUsuario, "UTF-8");
        per_codigo = URLDecoder.decode(per_codigo, "UTF-8");
        
        
        
         AESManagerExternal aesManager = new AESManagerExternal();
         aesManager.initialiseCipher();
         per_codigo = aesManager.decryptText(per_codigo);
         codUsuario = aesManager.decryptText(codUsuario);
         
         Logger.getLogger(this.getClass().getName()).log(Level.INFO, "per_codigo "+per_codigo);
         
        
    %>        
</head>

<body > 
  <tr><td><br></td><td><br></td></tr>
   <center><FONT FACE="verdana" SIZE="5"><strong>CENTRO DE EDUCACION CONTINUA : MENU DE OPCIONES</strong></font></center>
    <left>
         <table border="0">                                              
             <%--<tr> --%> 
                 <tr>
                     <td rowspan="2">
                            <img src="../imagenes/usuario.jpg" ></img>
                     </td>
                     <td>
                         <FONT FACE="verdana" SIZE="3"><strong><%=nomUsuario%></strong></FONT>
                     </td>
                 </tr>                    
                 <tr>
                     <td>
                        <FONT FACE="verdana" SIZE="2"><%=perfilUsuario%></FONT>  
                     </td>
                 </tr>
                     <%--
                     
                     <td colspan="3">
                     
                 </td>  
                     
                     --%>
                                                    
              <%-- </tr>  --%>
         </table>
    </left>

        <div id="menu">
        <ul>
          <li class="nivel1"><a href="#" class="nivel1">PLANIFICACION</a>
               <ul class="nivel2">
                      <li class="nivel2"><a class="nivel2" href="#">PROYECTOS»»»»</a>
                                <!--[if lte IE 6]><a href="#" class="nivel2ie">Opción 2.3 »<table class="falsa"><tr><td><![endif]-->
                     
                                <ul class="nivel3">
                                        <li><a class="primera" href="consulta_proyecto.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>">CONSULTA DE PROYECTO</a></li>
                                        <li><a class="primera" href="ingreso_proyecto.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>">INGRESO DE PROYECTO</a></li>                                        
                                        <li><a class="primera" href="ingreso_actividades.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>">INGRESO DE ACTIVIDADES</a></li>
                                        <li><a class="primera" href="ingreso_recursos.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>">INGRESO DE RECURSOS</a></li>
                                </ul>
                        </li>                       
                        <li class="nivel2"><a class="nivel2" href="#">PERSONAL»»»»</a>
                                <!--[if lte IE 6]><a href="#" class="nivel2ie">Opción 2.3 »<table class="falsa"><tr><td><![endif]-->
                                <ul class="nivel3">
                                       <li><a class="primera" href="ingreso_personal.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>">INDICADOR CUANTITATIVO</a></li>
                                        <li><a class="primera" href="consulta_personal.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>">INDICADOR CUALITATIVO</a></li>								
                                </ul>
                        </li>                           
                        
                </ul>
         </li>
                                
          <li class="nivel1"><a href="#" class="nivel1">SEGUIMIENTO</a>
                              <ul class="nivel2">
                        <li class="nivel2"><a class="nivel2" href="ingreso_seguimiento.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>">SEGUIMIENTO ACTIVIDADES</a>
                        </li>
                       
              
                </ul>
         </li>                                


                        
          <li class="nivel1"><a href="#" class="nivel1">EVALUACION</a>
                              <ul class="nivel2">
                        <li class="nivel2"><a class="nivel2" href="ingreso_evaluacion.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>">EVALUACION DE ACTIVIDADES</a>
                        </li>
                       
              
                </ul>
         </li>                        

        <li class="nivel1"><a href="#" class="nivel1">SEGURIDAD</a>
                <ul class="nivel2">
                    <%if(per_codigo.equals("PER00001"))
                     {
                    %>
                        <li class="nivel2"><a class="nivel2" href="reseteo_clave.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>">RESETEO CLAVE</a>
                        </li>
                        <li class="nivel2"><a class="nivel2" href="desactiva_usuario.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>">DESACTIVAR USUARIO</a>
                        </li>                        
                        <li class="nivel2"><a class="nivel2" href="activa_usuario.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>">ACTIVAR USUARIO</a>
                        </li>                        
                        
                     <%
                     }
                    %>
                </ul>            
        </li>
                
                
 
   
            <li class="nivel1"><a href="../index.jsp" class="nivel1" href="#">SALIR</a>                                                                                                                                       
        </ul>
        </div>
</body>
</html>