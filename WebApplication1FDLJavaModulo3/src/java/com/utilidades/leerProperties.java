/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilidades;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author CEC
 */
public class leerProperties 
{
    public static String leerArchivoPropiedades(String clave) throws IOException
    {
        String valorPropiedad="";
        int i=0;
        Properties propiedades=new Properties();
        propiedades.load(new FileInputStream("src/propiedades.properties"));
        Object objeto;
        for(Enumeration e=propiedades.keys(); e.hasMoreElements();)
        {
            objeto=e.nextElement();
            if(clave.compareTo(objeto.toString())==0)
            {
                valorPropiedad=propiedades.getProperty(objeto.toString());
                break;
            }
            i++;
        }
        return valorPropiedad;
    }
    
}
