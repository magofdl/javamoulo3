/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.controladores.exceptions.NonexistentEntityException;
import com.controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.negocio.Personal;
import com.negocio.Catalogo;
import com.negocio.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author CEC
 */
public class ProyectoJpaController implements Serializable {

    public ProyectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proyecto proyecto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personal perIdentificacion = proyecto.getPerIdentificacion();
            if (perIdentificacion != null) {
                perIdentificacion = em.getReference(perIdentificacion.getClass(), perIdentificacion.getPerIdentificacion());
                proyecto.setPerIdentificacion(perIdentificacion);
            }
            Catalogo catCodigo = proyecto.getCatCodigo();
            if (catCodigo != null) {
                catCodigo = em.getReference(catCodigo.getClass(), catCodigo.getCatCodigo());
                proyecto.setCatCodigo(catCodigo);
            }
            em.persist(proyecto);
            if (perIdentificacion != null) {
                perIdentificacion.getProyectoCollection().add(proyecto);
                perIdentificacion = em.merge(perIdentificacion);
            }
            if (catCodigo != null) {
                catCodigo.getProyectoCollection().add(proyecto);
                catCodigo = em.merge(catCodigo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProyecto(proyecto.getProCodigo()) != null) {
                throw new PreexistingEntityException("Proyecto " + proyecto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proyecto proyecto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proyecto persistentProyecto = em.find(Proyecto.class, proyecto.getProCodigo());
            Personal perIdentificacionOld = persistentProyecto.getPerIdentificacion();
            Personal perIdentificacionNew = proyecto.getPerIdentificacion();
            Catalogo catCodigoOld = persistentProyecto.getCatCodigo();
            Catalogo catCodigoNew = proyecto.getCatCodigo();
            if (perIdentificacionNew != null) {
                perIdentificacionNew = em.getReference(perIdentificacionNew.getClass(), perIdentificacionNew.getPerIdentificacion());
                proyecto.setPerIdentificacion(perIdentificacionNew);
            }
            if (catCodigoNew != null) {
                catCodigoNew = em.getReference(catCodigoNew.getClass(), catCodigoNew.getCatCodigo());
                proyecto.setCatCodigo(catCodigoNew);
            }
            proyecto = em.merge(proyecto);
            if (perIdentificacionOld != null && !perIdentificacionOld.equals(perIdentificacionNew)) {
                perIdentificacionOld.getProyectoCollection().remove(proyecto);
                perIdentificacionOld = em.merge(perIdentificacionOld);
            }
            if (perIdentificacionNew != null && !perIdentificacionNew.equals(perIdentificacionOld)) {
                perIdentificacionNew.getProyectoCollection().add(proyecto);
                perIdentificacionNew = em.merge(perIdentificacionNew);
            }
            if (catCodigoOld != null && !catCodigoOld.equals(catCodigoNew)) {
                catCodigoOld.getProyectoCollection().remove(proyecto);
                catCodigoOld = em.merge(catCodigoOld);
            }
            if (catCodigoNew != null && !catCodigoNew.equals(catCodigoOld)) {
                catCodigoNew.getProyectoCollection().add(proyecto);
                catCodigoNew = em.merge(catCodigoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = proyecto.getProCodigo();
                if (findProyecto(id) == null) {
                    throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.");
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
            Proyecto proyecto;
            try {
                proyecto = em.getReference(Proyecto.class, id);
                proyecto.getProCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proyecto with id " + id + " no longer exists.", enfe);
            }
            Personal perIdentificacion = proyecto.getPerIdentificacion();
            if (perIdentificacion != null) {
                perIdentificacion.getProyectoCollection().remove(proyecto);
                perIdentificacion = em.merge(perIdentificacion);
            }
            Catalogo catCodigo = proyecto.getCatCodigo();
            if (catCodigo != null) {
                catCodigo.getProyectoCollection().remove(proyecto);
                catCodigo = em.merge(catCodigo);
            }
            em.remove(proyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proyecto> findProyectoEntities() {
        return findProyectoEntities(true, -1, -1);
    }

    public List<Proyecto> findProyectoEntities(int maxResults, int firstResult) {
        return findProyectoEntities(false, maxResults, firstResult);
    }

    private List<Proyecto> findProyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proyecto.class));
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

    public Proyecto findProyecto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proyecto> rt = cq.from(Proyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
