/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.CustomersLogin;
import edu.iit.sat.itmd4515.spatil32.fp.web.LoginCustomer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

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
    
    public Customer findByCustomerName(String username)
    {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findCustomerByName", Customer.class);
        query.setParameter("name", username);
        return query.getSingleResult();
    }
    
    public Customer findByUsernameAndPassword(String username , String password)
    {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.loginCustomer", Customer.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
    
    public Customer findByCustomerId(Integer id)
    {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findCustomerById", Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    
    public CustomersLogin findByUsername(String username) {
        TypedQuery<CustomersLogin> query = em.createNamedQuery("CustomersLogin.findByUsername", CustomersLogin.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
