/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Dell
 */
@Stateless
public class CustomerService extends AbstractService<Customer>
{

    public CustomerService()
    {
        super(Customer.class);
    }

    @Override
    public List<Customer> findAll() 
    {
        return em.createNamedQuery("Customer.seeAllCustomers").getResultList();
    }
    
}
