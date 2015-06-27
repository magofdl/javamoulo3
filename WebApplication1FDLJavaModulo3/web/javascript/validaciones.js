/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validarCredenciales(formulario){
    var nombreUsuario=formulario.txt_nombreusuario.value;
    var claveUsuario=formulario.txt_clave.value;
    if (nombreUsuario=="" || claveUsuario==""){
        alert('Por favor ingrese sus credenciales');
        return false;
    }
    else{
        var segura = calcMD5(claveUsuario);
        formulario.txt_clave.value=segura;
        return true;
    }
    
    
}