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
        onsumit: que pasa cuando se quiere enviar el formulario, se dispara por ejemplo al dar un click.
        --%>
        <form method="post" action=servlet_logeo" onsubmit="">
            
        </form>
            
        <%
            String nombreUsuario="";
            int numeroAnios=12;
         %>
    </body>
</html>

