/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Basket;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dell
 */
@Stateless
public class BasketService extends AbstractService<Basket> {

    private static final Logger LOG = Logger.getLogger(BasketService.class.getName());

    /**
     *
     */
    public BasketService() {
        super(Basket.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Basket> findAll() {
        return em.createNamedQuery("Basket.seeAllCustomersBaskets").getResultList();
    }

    /**
     *
     * @param id
     * @return
     */
    public Basket findBasketByCustomerId(Long id) {
        TypedQuery<Basket> query = em.createNamedQuery("Basket.findBasketByCustomerId", Basket.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     *
     * @param id
     * @return
     */
    public List<Basket> findAllBasketByCustomerId(int id) {
        TypedQuery<Basket> query = em.createNamedQuery("Basket.findBasketByCustomerId", Basket.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    /**
     *
     * @param id
     * @return
     */
    public Basket findBasketByBasketId(Long id) {
        TypedQuery<Basket> query = em.createNamedQuery("Basket.findBasketByBasketId", Basket.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     *
     * @param id
     */
    public void deleteBasketByCustomerId(int id) {
        TypedQuery<Basket> query = em.createNamedQuery("Basket.deleteBasketByCustomerId", Basket.class);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();
        LOG.info("Deleted basket from customer id = " + deleted);
    }

    /**
     *
     * @param id
     */
    public void deleteBasketByBasketId(Long id) {
        TypedQuery<Basket> query = em.createNamedQuery("Basket.deleteBasketByBasketId", Basket.class);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();
        LOG.info("Deleted basket from basket id = " + deleted);
    }
}
