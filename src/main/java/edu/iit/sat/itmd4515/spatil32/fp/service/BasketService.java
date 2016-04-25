/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Basket;
import java.util.List;


/**
 *
 * @author Dell
 */
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
}
