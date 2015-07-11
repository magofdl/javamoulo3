/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.controladores.exceptions.NonexistentEntityException;
import com.controladores.exceptions.PreexistingEntityException;
import com.negocio.Producto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.negocio.Proveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author CEC
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor prvCodigo = producto.getPrvCodigo();
            if (prvCodigo != null) {
                prvCodigo = em.getReference(prvCodigo.getClass(), prvCodigo.getPrvCodigo());
                producto.setPrvCodigo(prvCodigo);
            }
            em.persist(producto);
            if (prvCodigo != null) {
                prvCodigo.getProductoList().add(producto);
                prvCodigo = em.merge(prvCodigo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProducto(producto.getPrdCodigo()) != null) {
                throw new PreexistingEntityException("Producto " + producto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getPrdCodigo());
            Proveedor prvCodigoOld = persistentProducto.getPrvCodigo();
            Proveedor prvCodigoNew = producto.getPrvCodigo();
            if (prvCodigoNew != null) {
                prvCodigoNew = em.getReference(prvCodigoNew.getClass(), prvCodigoNew.getPrvCodigo());
                producto.setPrvCodigo(prvCodigoNew);
            }
            producto = em.merge(producto);
            if (prvCodigoOld != null && !prvCodigoOld.equals(prvCodigoNew)) {
                prvCodigoOld.getProductoList().remove(producto);
                prvCodigoOld = em.merge(prvCodigoOld);
            }
            if (prvCodigoNew != null && !prvCodigoNew.equals(prvCodigoOld)) {
                prvCodigoNew.getProductoList().add(producto);
                prvCodigoNew = em.merge(prvCodigoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = producto.getPrdCodigo();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getPrdCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            Proveedor prvCodigo = producto.getPrvCodigo();
            if (prvCodigo != null) {
                prvCodigo.getProductoList().remove(producto);
                prvCodigo = em.merge(prvCodigo);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Producto findProducto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
