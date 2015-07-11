/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.controladores.exceptions.IllegalOrphanException;
import com.controladores.exceptions.NonexistentEntityException;
import com.controladores.exceptions.PreexistingEntityException;
import com.negocio.Catalogo;
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
public class CatalogoJpaController implements Serializable {

    public CatalogoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Catalogo catalogo) throws PreexistingEntityException, Exception {
        if (catalogo.getProyectoCollection() == null) {
            catalogo.setProyectoCollection(new ArrayList<Proyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Proyecto> attachedProyectoCollection = new ArrayList<Proyecto>();
            for (Proyecto proyectoCollectionProyectoToAttach : catalogo.getProyectoCollection()) {
                proyectoCollectionProyectoToAttach = em.getReference(proyectoCollectionProyectoToAttach.getClass(), proyectoCollectionProyectoToAttach.getProCodigo());
                attachedProyectoCollection.add(proyectoCollectionProyectoToAttach);
            }
            catalogo.setProyectoCollection(attachedProyectoCollection);
            em.persist(catalogo);
            for (Proyecto proyectoCollectionProyecto : catalogo.getProyectoCollection()) {
                Catalogo oldCatCodigoOfProyectoCollectionProyecto = proyectoCollectionProyecto.getCatCodigo();
                proyectoCollectionProyecto.setCatCodigo(catalogo);
                proyectoCollectionProyecto = em.merge(proyectoCollectionProyecto);
                if (oldCatCodigoOfProyectoCollectionProyecto != null) {
                    oldCatCodigoOfProyectoCollectionProyecto.getProyectoCollection().remove(proyectoCollectionProyecto);
                    oldCatCodigoOfProyectoCollectionProyecto = em.merge(oldCatCodigoOfProyectoCollectionProyecto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCatalogo(catalogo.getCatCodigo()) != null) {
                throw new PreexistingEntityException("Catalogo " + catalogo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Catalogo catalogo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catalogo persistentCatalogo = em.find(Catalogo.class, catalogo.getCatCodigo());
            Collection<Proyecto> proyectoCollectionOld = persistentCatalogo.getProyectoCollection();
            Collection<Proyecto> proyectoCollectionNew = catalogo.getProyectoCollection();
            List<String> illegalOrphanMessages = null;
            for (Proyecto proyectoCollectionOldProyecto : proyectoCollectionOld) {
                if (!proyectoCollectionNew.contains(proyectoCollectionOldProyecto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proyecto " + proyectoCollectionOldProyecto + " since its catCodigo field is not nullable.");
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
            catalogo.setProyectoCollection(proyectoCollectionNew);
            catalogo = em.merge(catalogo);
            for (Proyecto proyectoCollectionNewProyecto : proyectoCollectionNew) {
                if (!proyectoCollectionOld.contains(proyectoCollectionNewProyecto)) {
                    Catalogo oldCatCodigoOfProyectoCollectionNewProyecto = proyectoCollectionNewProyecto.getCatCodigo();
                    proyectoCollectionNewProyecto.setCatCodigo(catalogo);
                    proyectoCollectionNewProyecto = em.merge(proyectoCollectionNewProyecto);
                    if (oldCatCodigoOfProyectoCollectionNewProyecto != null && !oldCatCodigoOfProyectoCollectionNewProyecto.equals(catalogo)) {
                        oldCatCodigoOfProyectoCollectionNewProyecto.getProyectoCollection().remove(proyectoCollectionNewProyecto);
                        oldCatCodigoOfProyectoCollectionNewProyecto = em.merge(oldCatCodigoOfProyectoCollectionNewProyecto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = catalogo.getCatCodigo();
                if (findCatalogo(id) == null) {
                    throw new NonexistentEntityException("The catalogo with id " + id + " no longer exists.");
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
            Catalogo catalogo;
            try {
                catalogo = em.getReference(Catalogo.class, id);
                catalogo.getCatCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catalogo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Proyecto> proyectoCollectionOrphanCheck = catalogo.getProyectoCollection();
            for (Proyecto proyectoCollectionOrphanCheckProyecto : proyectoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Catalogo (" + catalogo + ") cannot be destroyed since the Proyecto " + proyectoCollectionOrphanCheckProyecto + " in its proyectoCollection field has a non-nullable catCodigo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(catalogo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Catalogo> findCatalogoEntities() {
        return findCatalogoEntities(true, -1, -1);
    }

    public List<Catalogo> findCatalogoEntities(int maxResults, int firstResult) {
        return findCatalogoEntities(false, maxResults, firstResult);
    }

    private List<Catalogo> findCatalogoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catalogo.class));
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

    public Catalogo findCatalogo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catalogo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatalogoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catalogo> rt = cq.from(Catalogo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
