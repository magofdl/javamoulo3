/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.controladores.exceptions.IllegalOrphanException;
import com.controladores.exceptions.NonexistentEntityException;
import com.controladores.exceptions.PreexistingEntityException;
import com.negocio.Personal;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.negocio.Proyecto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author CEC
 */
public class PersonalJpaController implements Serializable {

    public PersonalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personal personal) throws PreexistingEntityException, Exception {
        if (personal.getProyectoCollection() == null) {
            personal.setProyectoCollection(new ArrayList<Proyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Proyecto> attachedProyectoCollection = new ArrayList<Proyecto>();
            for (Proyecto proyectoCollectionProyectoToAttach : personal.getProyectoCollection()) {
                proyectoCollectionProyectoToAttach = em.getReference(proyectoCollectionProyectoToAttach.getClass(), proyectoCollectionProyectoToAttach.getProCodigo());
                attachedProyectoCollection.add(proyectoCollectionProyectoToAttach);
            }
            personal.setProyectoCollection(attachedProyectoCollection);
            em.persist(personal);
            for (Proyecto proyectoCollectionProyecto : personal.getProyectoCollection()) {
                Personal oldPerIdentificacionOfProyectoCollectionProyecto = proyectoCollectionProyecto.getPerIdentificacion();
                proyectoCollectionProyecto.setPerIdentificacion(personal);
                proyectoCollectionProyecto = em.merge(proyectoCollectionProyecto);
                if (oldPerIdentificacionOfProyectoCollectionProyecto != null) {
                    oldPerIdentificacionOfProyectoCollectionProyecto.getProyectoCollection().remove(proyectoCollectionProyecto);
                    oldPerIdentificacionOfProyectoCollectionProyecto = em.merge(oldPerIdentificacionOfProyectoCollectionProyecto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersonal(personal.getPerIdentificacion()) != null) {
                throw new PreexistingEntityException("Personal " + personal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personal personal) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personal persistentPersonal = em.find(Personal.class, personal.getPerIdentificacion());
            Collection<Proyecto> proyectoCollectionOld = persistentPersonal.getProyectoCollection();
            Collection<Proyecto> proyectoCollectionNew = personal.getProyectoCollection();
            List<String> illegalOrphanMessages = null;
            for (Proyecto proyectoCollectionOldProyecto : proyectoCollectionOld) {
                if (!proyectoCollectionNew.contains(proyectoCollectionOldProyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proyecto " + proyectoCollectionOldProyecto + " since its perIdentificacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Proyecto> attachedProyectoCollectionNew = new ArrayList<Proyecto>();
            for (Proyecto proyectoCollectionNewProyectoToAttach : proyectoCollectionNew) {
                proyectoCollectionNewProyectoToAttach = em.getReference(proyectoCollectionNewProyectoToAttach.getClass(), proyectoCollectionNewProyectoToAttach.getProCodigo());
                attachedProyectoCollectionNew.add(proyectoCollectionNewProyectoToAttach);
            }
            proyectoCollectionNew = attachedProyectoCollectionNew;
            personal.setProyectoCollection(proyectoCollectionNew);
            personal = em.merge(personal);
            for (Proyecto proyectoCollectionNewProyecto : proyectoCollectionNew) {
                if (!proyectoCollectionOld.contains(proyectoCollectionNewProyecto)) {
                    Personal oldPerIdentificacionOfProyectoCollectionNewProyecto = proyectoCollectionNewProyecto.getPerIdentificacion();
                    proyectoCollectionNewProyecto.setPerIdentificacion(personal);
                    proyectoCollectionNewProyecto = em.merge(proyectoCollectionNewProyecto);
                    if (oldPerIdentificacionOfProyectoCollectionNewProyecto != null && !oldPerIdentificacionOfProyectoCollectionNewProyecto.equals(personal)) {
                        oldPerIdentificacionOfProyectoCollectionNewProyecto.getProyectoCollection().remove(proyectoCollectionNewProyecto);
                        oldPerIdentificacionOfProyectoCollectionNewProyecto = em.merge(oldPerIdentificacionOfProyectoCollectionNewProyecto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = personal.getPerIdentificacion();
                if (findPersonal(id) == null) {
                    throw new NonexistentEntityException("The personal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personal personal;
            try {
                personal = em.getReference(Personal.class, id);
                personal.getPerIdentificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Proyecto> proyectoCollectionOrphanCheck = personal.getProyectoCollection();
            for (Proyecto proyectoCollectionOrphanCheckProyecto : proyectoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personal (" + personal + ") cannot be destroyed since the Proyecto " + proyectoCollectionOrphanCheckProyecto + " in its proyectoCollection field has a non-nullable perIdentificacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(personal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personal> findPersonalEntities() {
        return findPersonalEntities(true, -1, -1);
    }

    public List<Personal> findPersonalEntities(int maxResults, int firstResult) {
        return findPersonalEntities(false, maxResults, firstResult);
    }

    private List<Personal> findPersonalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personal.class));
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

    public Personal findPersonal(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personal.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personal> rt = cq.from(Personal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
