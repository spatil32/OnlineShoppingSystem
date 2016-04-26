/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Basket;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;


/**
 *
 * @author Dell
 */
@Stateless
public class BasketService extends AbstractService<Basket>
{

    public BasketService()
    {
        super(Basket.class);
    }
    
    @Override
    public List<Basket> findAll()
    {
        return em.createNamedQuery("Basket.seeAllCustomersBaskets").getResultList();
    }
    
    public Basket findBasketByCustomerId(Long id)
    {
        TypedQuery<Basket> query = em.createNamedQuery("Basket.findBasketByCustomerId", Basket.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    
    public Basket findBasketByBasketId(Long id)
    {
        TypedQuery<Basket> query = em.createNamedQuery("Basket.findBasketByBasketId", Basket.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    
    
}
