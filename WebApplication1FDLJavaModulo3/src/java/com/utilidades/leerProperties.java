/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilidades;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 *
 * @author CEC
 */
public class leerProperties 
{
    public static String leerArchivoPropiedades(String clave) throws IOException
    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("propiedades"); // Propiedades.properties
        if (resourceBundle.containsKey(clave)) {
            return resourceBundle.getString(clave);
        }
        return null;
    }
    
}
