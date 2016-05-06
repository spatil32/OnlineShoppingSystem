/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.CustomersLogin;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dell
 */
@Stateless
public class CustomerServiceLogin extends AbstractService<CustomersLogin> {

    public CustomerServiceLogin() {
        super(CustomersLogin.class);
    }

    @Override
    public List<CustomersLogin> findAll() {
        return em.createNamedQuery("CustomersLogin.findAll").getResultList();
    }

    public CustomersLogin findByUsername(String username) {
        TypedQuery<CustomersLogin> query = em.createNamedQuery("CustomersLogin.findByUsername", CustomersLogin.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
