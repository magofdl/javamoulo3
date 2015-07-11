/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocio;

import com.controladores.ProveedorJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author CEC
 */
public class Prueba {
    

    
    public static void main(String[] args) {
        
        EntityManagerFactory factory=Persistence.createEntityManagerFactory("WebApplication1FDLJavaModulo3PU", System.getProperties());
        
        
        
        EntityManager em = factory.createEntityManager();

       
        

      
        ProveedorJpaController  proveedorJpaController=new ProveedorJpaController(factory);
        Proveedor proveedor= new Proveedor();
        
        proveedor.setPrvCodigo("PRV001");
        proveedor.setPrvDireccion("Lugo");
        proveedor.setPrvTelefono("3227398");
        proveedor.setPrvNombre("Laboratorio ARM");
        proveedor.setPrvCelular("0987299095");
        
        try {
//            proveedorJpaController.create(proveedor);
                    em.getTransaction().begin();
                    em.persist(proveedor);
                    em.getTransaction().commit();
                    em.close();
            
        } catch (Exception ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
