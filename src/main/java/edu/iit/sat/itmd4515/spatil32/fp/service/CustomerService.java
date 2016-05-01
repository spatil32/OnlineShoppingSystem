/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dell
 */
@Stateless
public class CustomerService extends AbstractService<Customer>
{

    private static final Logger LOG = Logger.getLogger(CustomerService.class.getName());

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

        public Customer findCustomerByUsername(String username)
    {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findCustomerByUsername", Customer.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
        
    public Customer findByCustomerId(Integer id)
    {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findCustomerById", Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    
    public void UpdateCustomer(Customer updateCustomer)
    {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.updateCustomerById", Customer.class);
        query.setParameter("fname", updateCustomer.getFirstName());
        query.setParameter("lname", updateCustomer.getLastName());
        query.setParameter("age", updateCustomer.getAge());
        query.setParameter("gender", updateCustomer.getGender());
        query.setParameter("address", updateCustomer.getAddress());
        query.setParameter("email", updateCustomer.getEmail());
        query.setParameter("birth", updateCustomer.getBirthDate());
        query.setParameter("phone", updateCustomer.getPhoneNo());
        query.setParameter("id", updateCustomer.getCustomerId());
        
        int updated = query.executeUpdate();
        LOG.info("Account updated.");        
    }
    
    public void deleteCustomerById(Integer id)
    {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.DeleteCustomerById", Customer.class);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();
        LOG.info("Account deleted.");     
    }
}
