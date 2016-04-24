/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Admin;
import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.security.Group;
import edu.iit.sat.itmd4515.spatil32.fp.security.User;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dell
 */
@Singleton
@Startup
public class StartupBean 
{
    @PersistenceContext(unitName = "spatil32PU")
    EntityManager em;
    
    @EJB
    CustomerService customerService;

    @EJB
    AdminService adminService;
    
    public StartupBean() {
    }
    
    @PostConstruct
    private void init()
    {
        //Customer admin = new Customer("admin", "admin", 27, 'M', "Pune", "admin@admin.com", new GregorianCalendar(1991, 5, 16).getTime(), "12345", "admin", "admin", 'Y');
        //customerService.create(admin);
        Group SystemAdmin = new Group("SystemAdmin", "This group is for Admin's only");
        Group SystemCustomer = new Group("SystemCustomer", "This group is for customers only");
        em.persist(SystemAdmin);
        em.persist(SystemCustomer);
        
        User admin = new User("admin", "admin");
        admin.addUserToGroup(SystemAdmin);
        em.persist(admin);
        
        Admin admins = new Admin();
        admins.setUser(admin);
        
        adminService.create(admins);
        System.out.println("Added admin");

    }
}
