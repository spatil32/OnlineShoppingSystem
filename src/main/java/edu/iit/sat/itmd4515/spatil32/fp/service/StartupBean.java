/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Admin;
import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.CustomersLogin;
import edu.iit.sat.itmd4515.spatil32.fp.model.Products;
import edu.iit.sat.itmd4515.spatil32.fp.security.Group;
import edu.iit.sat.itmd4515.spatil32.fp.security.User;
import java.util.Date;
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
public class StartupBean {

    @PersistenceContext(unitName = "spatil32PU")
    EntityManager em;

    @EJB
    CustomerService customerService;

    @EJB
    CustomerServiceLogin customersServiceLogin;

    @EJB
    AdminService adminService;

    @EJB
    ProductService productService;

    /**
     *
     */
    public StartupBean() {
    }

    @PostConstruct
    private void init() {
        Group SystemAdmin = new Group("SystemAdmin", "This group is for Admin's only");
        Group SystemCustomer = new Group("SystemCustomer", "This group is for customers only");
        em.persist(SystemAdmin);
        em.persist(SystemCustomer);

        User admin = new User("admin", "admin");
        User cust = new User("shreyas", "patil");

        admin.addUserToGroup(SystemAdmin);
        cust.addUserToGroup(SystemCustomer);

        em.persist(admin);
        em.persist(cust);

        CustomersLogin cust1 = new CustomersLogin();
        cust1.setUser(cust);
        Admin admin1 = new Admin();
        admin1.setUser(admin);

        customersServiceLogin.create(cust1);
        adminService.create(admin1);
        //Without security code

        Customer adminCustomer = new Customer("admin", "admin", 27, 'M', "Pune", "shreyas.itmd4515@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12345", "admin", "admin", 'Y');
        customerService.create(adminCustomer);

        Customer newCustomer1 = new Customer("Shreyas", "Patil", 25, 'M', "Pune", "patilsr91@gmail.com", new GregorianCalendar(1991, 5, 16).getTime(), "12345", "shreyas", "patil", 'N');
        customerService.create(newCustomer1);

        Customer newCustomer2 = new Customer("Revan", "Patil", 25, 'M', "Pune", "shreyas@patil.com", new GregorianCalendar(1991, 5, 16).getTime(), "12345", "revan", "patil", 'N');
        customerService.create(newCustomer2);

        Products newProduct1 = new Products("LED TV", new Date(), 'E', 5000, 10, 150, 120);
        Products newProduct2 = new Products("BOSE Speakers", new Date(), 'E', 800, 20, 50, 20);
        Products newProduct3 = new Products("iPhone 6", new Date(), 'E', 700, 30, 70, 30);
        Products newProduct4 = new Products("Galaxy Edge", new Date(), 'E', 500, 10, 60, 20);
        Products newProduct5 = new Products("Levis Mens Jeans", new Date(), 'C', 30, 10, 100, 50);
        Products newProduct6 = new Products("Nike Sports Shoes", new Date(), 'S', 100, 50, 100, 30);
        Products newProduct7 = new Products("Phillips Juicer", new Date(), 'E', 200, 20, 50, 20);
        Products newProduct8 = new Products("Sony Bravia", new Date(), 'E', 1700, 10, 150, 30);
        Products newProduct9 = new Products("Begining JAVA EE", new Date(), 'B', 50, 20, 50, 20);
        Products newProduct10 = new Products("Twilight", new Date(), 'B', 60, 30, 80, 50);

        productService.create(newProduct1);
        productService.create(newProduct2);
        productService.create(newProduct3);
        productService.create(newProduct4);
        productService.create(newProduct5);
        productService.create(newProduct6);
        productService.create(newProduct7);
        productService.create(newProduct8);
        productService.create(newProduct9);
        productService.create(newProduct10);
    }
}
