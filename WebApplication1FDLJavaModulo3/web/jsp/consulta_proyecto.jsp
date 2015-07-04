<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CONSULTA DE PROYECTO</title>
        <link rel="stylesheet" href="../css/textbox.css" type="text/css" />
        <script type="text/javascript"  src="../javascript/funciones.js"></script>   
        <link rel="shortcut icon" href="../imagenes/ingreso.ico">  
    </head>
    <body>
        <left>

        </left>        
        <form method="post" action="../ServletProyectoConsultaCodigo">           
            <center><FONT FACE="verdana" SIZE="5"><strong>CONSULTA DE PROYECTO</strong></font></center>
            <center>
                <TABLE border="0">
                    <tr><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td></tr>
                    <tr><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td></tr>
                    <TR ALIGN=left><TH><FONT FACE="verdana" SIZE="3"><strong>CODIGO:</strong></font><TD><left><input type="text" name="txt_codigo" size="20" maxlength="10" style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();"><center><TD><TD><TD><TD><TD><TD><TD><TD><input type="submit" name="btn_consultar"  value="CONSULTAR POR CODIGO" style="font-weight: bold;" ></center>
                    <tr><td><br></td><td><br></td></tr>
                </TABLE>
            </center>
         </form>     
        <form method="post" action="../servlet_proyecto_nombre">                       
            <center>
                <TABLE border="0">
                    <tr><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td></tr>
                    <tr><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td></tr>
                    <tr><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td></tr>
                    <TR ALIGN=left><TH><FONT FACE="verdana" SIZE="3"><strong>NOMBRE:</strong></font><TD><left><input type="text" name="txt_nombre" size="20" maxlength="20" style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();"><TD><TD><TD><TD><TD><TD><TD><TD><TD><center><input type="submit" name="btn_consultar2"  value="CONSULTAR POR NOMBRE" style="font-weight: bold;" ></center>
                    <tr><td><br></td><td><br></td></tr>
                    <tr><td><br></td><td><br></td></tr>
                </TABLE>
            </center>
         </form>     
            <BR><BR>
        <center><a href="menuopciones.jsp?codUsuario=<%//=codUsuario%>&nomUsuario=<%//=nomUsuario%>"><img src="../imagenes/menuprincipal.jpg"></a></center>
    </body>
</html>
