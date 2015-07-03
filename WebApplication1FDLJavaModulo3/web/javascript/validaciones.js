/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validarCredenciales(formulario) {
    var nombreUsuario = formulario.txt_nombreusuario.value;
    var claveUsuario = formulario.txt_clave.value;
    if (nombreUsuario == "" || claveUsuario == "") {
        alert('Por favor ingrese sus credenciales');
        return false;
    }
    else {
        var segura = calcMD5(claveUsuario);
        formulario.txt_clave.value = segura;
        return true;
    }
}

function validarCambioClave(formulario) {
    var claveActual = formulario.txt_claveactual.value;
    var nuevaClave = formulario.txt_clavenueva.value;
    var nuevaClaveConfirmacion = formulario.txt_clavenuevaconfirmacion.value;

    if (nuevaClave != nuevaClaveConfirmacion) {
        alert('La nueva contraseña no coincide con la confirmación.');
        return false;
    }
    else {
        var claveActualSegura = calcMD5(claveActual);
        var nuevaClaveSegura = calcMD5(nuevaClave);
        var nuevaClaveConfirmacionSegura = calcMD5(nuevaClaveConfirmacion);
        
        formulario.txt_claveactual.value = claveActualSegura;
        formulario.txt_clavenueva.value = nuevaClaveSegura;
        formulario.txt_clavenuevaconfirmacion.value = nuevaClaveConfirmacionSegura;
        return true;
    }
}