<%-- 
    Document   : cambioclave
    Created on : 30-jun-2015, 19:38:21
    Author     : magofdl
--%>

<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambio de clave</title>
        <link rel="shortcut icon" href="../imagenes/ingreso.ico">
        <link rel="stylesheet" href="../css/presentacion.css" type="text/css">
        <link rel="stylesheet" href="../css/textbox.css" type="text/css">
        <script type="text/javascript" src="../javascript/validaciones.js" ></script>
        <script type="text/javascript" src="../javascript/encriptacion.js" ></script>
        <script type="text/javascript" src="../javascript/encriptacion2.js" ></script>
    </head>
    <body>
    <center>
        <h1>Cambio de contraseña</h1>
        Es necesario que cambie su contraseña para poder continuar
    </center>



</body>
<% String usu_codigo=request.getParameter("usu_codigo"); %>
<% String mensajeValidacion=request.getParameter("mensajeValidacion"); %>


<form method="post" action="../ServletCambioClave" onsubmit="return  validarCambioClave(this)">

    <center>
        <table>
            <tr>
                <td>
                    <b>Contaseña actual:</b>
                </td>
                <td>
                    <input type="password" name="txt_claveactual" maxlength="20">
                </td>
            </tr> 
            <tr>
                <td>
                    <b>Nueva contraseña:</b>
                </td>
                <td>
                    <input type="password" name="txt_clavenueva" maxlength=30">
                </td>
            </tr>
            <tr>
                <td>
                    <b>Confirme nueva contraseña:</b>
                </td>
                <td>
                    <input type="password" name="txt_clavenuevaconfirmacion" maxlength=30">
                </td>
            </tr>
            <tr>
                <td colspan="2"><%--Fusionadas 2 columnas --%>
            <center>
                <input type="submit" name="btn_validarclave" value="Cambiar contraseña">
            </center>
            </td>
            </tr>
        </table>
    </center>
    <input type="hidden" name="usu_codigo" value="<%=usu_codigo%>" />
</form>
<script type="text/javascript">
        var mensajeValidacionAux = '<%=mensajeValidacion%>';
        
       if (if ('null' != mensajeValidacionAux)) {
           function alertName() {
                alert(mensajeValidacionAux);
            } 
        }
</script>   
<script type="text/javascript"> window.onload = alertName;</script>       
</html>
