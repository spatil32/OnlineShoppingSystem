/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Orders;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author patils03
 */
@Stateless
public class OrderService extends AbstractService<Orders>
{

    public OrderService()
    {
        super(Orders.class);
    }
    
    @Override
    public List<Orders> findAll()
    {
        return em.createNamedQuery("Orders.seeAllOrders").getResultList();
    }
}