

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.datos.*"%>
<%@page import="com.utilidades.*"%>
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
            String codigoProyecto="PR"+String.valueOf((int)(Math.random()*(10000000-1+1)+10000000)); 
            String codUsuario=request.getParameter("codUsuario");
            String nomUsuario=request.getParameter("nomUsuario");        
            String perfilUsuario=request.getParameter("per_descripcion");
            String per_codigo=request.getParameter("per_codigo");
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
        <form method="post" action="../ServletIngresoProyecto"  onSubmit="return validar_proyecto(this)" >           
            <center><FONT FACE="verdana" SIZE="5"><strong>INGRESO DE PROYECTO</strong></font></center>
            <center>
                <TABLE border="0">                                        
                  <TR ALIGN=left><TH><FONT FACE="verdana" SIZE="3"><strong>CODIGO: </strong></font><TD><input type="text" name="txt_codigo" size="10" maxlength="10" readonly="true" style="font-weight: bold;" value=<%=codigoProyecto%>>
                   
                  <TR ALIGN=left><TH><FONT FACE="verdana" SIZE="3"><strong>NOMBRE: </strong></font><TD><input type="text" name="txt_nombre" size="40" maxlength="40">                          
                          
                  <TR ALIGN=left><TH><FONT FACE="verdana" SIZE="3"><strong>OBSERVACIONES</strong></font><TD>
                  <textarea name="txt_observaciones" rows="3" cols="50" maxlength="40"></textarea>
                            
                  <TR ALIGN=left><TH><FONT FACE="verdana" SIZE="3"><strong>RESPONSABLE:</strong></font><TD>
                  <Select name="cmb_responsable">        
                  <%  
                        MysqlConnect mysSqlConnect=new MysqlConnect();
                         mysSqlConnect.efectuarConexionDB();
                        String  personalIdentificacion="",personalNombre="";
                        ResultSet resultadoConsultaResponsable=mysSqlConnect.ejecutarSpConsultasp("sp_consultaresponsable");                            
                        if(resultadoConsultaResponsable!=null)
                        while (resultadoConsultaResponsable.next())
                        {
                            personalIdentificacion=resultadoConsultaResponsable.getString("per_identificacion");
                            personalNombre=resultadoConsultaResponsable.getString("per_nombres")+" "+resultadoConsultaResponsable.getString("per_apellidos");
                            %>
                                <option value=<%=personalIdentificacion%>><%=personalNombre%></option>                            
                            <%                                    
                        }
                  %>
                  </Select>   
                          
                  <TR ALIGN=left><TH><FONT FACE="verdana" SIZE="3"><strong>PRIORIDAD:</strong></font><TD>
                  <Select name="cmb_catalogo">        
                  <%  
                        MysqlConnect mysSqlConnect2=new MysqlConnect();
                         mysSqlConnect2.efectuarConexionDB();
                        String  prioridadCodigo="",prioridadDescripcion="";
                        
                        
                        ResultSet resultadoConsultaCatalogo=mysSqlConnect2.ejecutarSpConsultasp("sp_consultarcatalogo");                            
                        if(resultadoConsultaCatalogo!=null)
                        while (resultadoConsultaCatalogo.next())
                        {
                            prioridadCodigo=resultadoConsultaCatalogo.getString("cat_codigo");
                            prioridadDescripcion=resultadoConsultaCatalogo.getString("cat_descripcion");
                            %>
                                <option value=<%=prioridadCodigo%>><%=prioridadDescripcion%></option>                            
                            <%                                    
                        }
                  %>
                  </Select>                             
                          
                  <td >
                      <center><input type="submit" name="btn_enviar"  style="font-weight: bold;" value="INGRESAR " ></center>
                  </td>                               
                  <td>
                     <center><a href="menuopciones.jsp?codUsuario=<%=codUsuario%>&nomUsuario=<%=nomUsuario%>&per_descripcion=<%=perfilUsuario%>&per_codigo=<%=per_codigo%>"><img src="../imagenes/menuprincipal.jpg"></a></center>
                  <td>                            
                    
                    <tr><td><br></td><td><br></td></tr>                                        
                </TABLE>
                        
            </center>
            
            <input type="hidden" name="codUsuario" value="<%=codUsuario%>" />
            <input type="hidden" name="nomUsuario" value="<%=nomUsuario%>" />
            <input type="hidden" name="per_descripcion" value="<%=perfilUsuario%>" />
            <input type="hidden" name="per_codigo" value="<%=per_codigo%>" />
        </form>  
        <center>          
             <TABLE border="0">                               
                    <TR>
                        <TD>
                              <input type="submit" name=submit  value="ARCHIVOS ADJUNTOS" style="font-weight: bold;" onclick="window.open('adjuntos.jsp?cod=<%=codigoProyecto%>','','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, width=420, height=80, left=0, top=0');">
                        </TD>
                    </TR>
            </TABLE>		
        </center> 
               
    </body>
</html>
