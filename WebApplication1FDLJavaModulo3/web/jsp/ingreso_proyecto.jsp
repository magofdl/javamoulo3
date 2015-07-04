

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.datos.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" href="../css/textbox.css" type="text/css" />
        <script type="text/javascript"  src="../javascript/validaciones.js"></script>          
        <title>INGRESO DE PROYECTO</title>
        <link rel="stylesheet" type="text/css" href="../css/datepickr.css" />
        <link rel="shortcut icon" href="../imagenes/ingreso.ico">  
         <%
         %> 
        
    </head>
    <body>
        <%

        %>
        <left>
             <table border="0">                                              
                 <tr>  
                     <td>
                         <img src="../imagenes/usuario.jpg" ></img>
                     </td>                    
                     <td>
                        <FONT FACE="verdana" SIZE="3"><strong><%=nomUsuario%></strong></FONT>
                     </td>                    
                 </tr>                
             </table>
        </left>        
        <form method="post" action="../servlet_ingreso_proyecto"  onSubmit="return validar_proyecto(this)" >           
            <center><FONT FACE="verdana" SIZE="5"><strong>INGRESO DE PROYECTO</strong></font></center>
            <center>
                <TABLE border="0">                                        
                  <TR ALIGN=left><TH><FONT FACE="verdana" SIZE="3"><strong>CODIGO: </strong></font><TD><input type="text" name="txt_codigo" sixe="10" maxlength="10" readonly="true" style="font-weight: bold;" value=<%=codigoProyecto%>>
                          
                            
                  <TR ALIGN=left><TH><FONT FACE="verdana" SIZE="3"><strong>RESPONSABLE: </strong></font><TD><Select name="cmb_responsable">        
                    <%  
                            
                            resultadoConsultaResponsable=instPosConn.ejecutarSpConsultasp("sp_consultaresponsable");                            
                            if(resultadoConsultaResponsable!=null)
                            while (resultadoConsultaResponsable.next())
                            {
                                categoriaCodigo=resultadoConsultaResponsable.getString("res_codigo");
                                categoriaDescripcion=resultadoConsultaResponsable.getString("res_nombre");
                    %>
                                <option value=<%=categoriaCodigo%>><%=categoriaDescripcion%></option>                            
                    <%                                    
                            }
                    %>
                    </Select>                             
                            
                            
                            
                        <td >
                                <center><input type="submit" name="btn_enviar"  style="font-weight: bold;" value="INGRESAR " ></center>
                        </td>                               
                        <td>
                                <center><a href="menuopciones.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>"><img src="../imagenes/menuprincipal.jpg"></a></center>
                        <td>                            
                    </tr>  
                    <tr><td><br></td><td><br></td></tr>                                        
                </TABLE>
                        
            </center>
        </form>  
        <center>          
             <TABLE border="0">                               
                    <TR>
                        <TD>
                              <input type="submit" name=submit  value="ARCHIVOS ADJUNTOS" style="font-weight: bold;" onclick="window.open('adjuntos.jsp?cod=<%=codigoServicio%>','','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, width=420, height=80, left=0, top=0');">
                        </TD>
                    </TR>
            </TABLE>		
        </center> 
               
    </body>
</html>
