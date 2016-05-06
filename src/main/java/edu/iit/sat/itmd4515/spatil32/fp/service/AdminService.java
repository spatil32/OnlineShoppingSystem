/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Admin;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * Used to provide services to Administrator Group
 *
 * @author Dell
 */
@Stateless
public class AdminService extends AbstractService<Admin> {

    /**
     * Default Constructor
     */
    public AdminService() {
        super(Admin.class);
    }

    /**
     * Returns the list of all Employee's login information
     *
     * @return
     */
    @Override
    public List<Admin> findAll() {
        return em.createNamedQuery("Admin.findAll").getResultList();
    }

    /**
     * Return the information of Single Employee by username
     *
     * @param username
     * @return
     */
    public Admin findByUsername(String username) {
        TypedQuery<Admin> query = em.createNamedQuery("Admin.findByUsername", Admin.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
