/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.BasketProducts;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dell
 */
@Stateless
public class Basket_ProductsService extends AbstractService<BasketProducts> {

    private static final Logger LOG = Logger.getLogger(Basket_ProductsService.class.getName());

    /**
     *
     */
    public Basket_ProductsService() {
        super(BasketProducts.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<BasketProducts> findAll() {
        return em.createNamedQuery("BasketProducts.SelectAll").getResultList();
    }

    /**
     *
     */
    public void removeAll() {
        List<BasketProducts> list = em.createNamedQuery("BasketProducts.SelectAll").getResultList();
        LOG.info("Basket products query size : " + list.size());
        for (BasketProducts basket_Products : list) {
            em.remove(basket_Products);
        }
        LOG.info("Deleted from basket_Products");
    }

    /**
     *
     * @param id
     */
    public void deleteByBasketId(Long id) {
        TypedQuery<BasketProducts> query = em.createNamedQuery("BasketProducts.DeleteByBasketId", BasketProducts.class);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();
        LOG.info("Selected product deleted from basket.");
    }

    /**
     *
     * @param id
     * @return
     */
    public BasketProducts findBasketProductByBasketId(Long id) {
        TypedQuery<BasketProducts> query = em.createNamedQuery("BasketProducts.FindByBasketId", BasketProducts.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
