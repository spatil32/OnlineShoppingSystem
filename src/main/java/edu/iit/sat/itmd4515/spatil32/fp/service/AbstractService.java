/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dell
 * @param <T>
 */
public abstract class AbstractService<T> {

    /**
     *
     */
    @PersistenceContext(unitName = "spatil32PU")
    protected EntityManager em;

    private Class<T> entityClass;

    /**
     *
     * @param entityClass
     */
    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     *
     * @param entity
     */
    public void create(T entity) {
        em.persist(entity);
    }

    /**
     *
     * @param id
     * @return
     */
    public T find(Object id) {
        return em.find(entityClass, id);
    }

    /**
     *
     * @param entity
     */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     *
     * @param entity
     */
    public void delete(T entity) {
        em.remove(entity);
    }

    /**
     *
     * @return
     */
    public abstract List<T> findAll();
}
