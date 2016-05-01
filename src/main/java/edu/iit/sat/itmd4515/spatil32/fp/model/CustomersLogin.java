/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.model;

import edu.iit.sat.itmd4515.spatil32.fp.security.User;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Customers login information domain class
 *
 * @author Dell
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CustomersLogin.findAll", query = "select c from CustomersLogin c"),
    @NamedQuery(name = "CustomersLogin.findByUsername", query = "select c from CustomersLogin c where c.user.userName = :username")
})
public class CustomersLogin extends CommonEntity {

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    /**
     * Default Constructor
     */
    public CustomersLogin() {
    }

    /**
     * Returns the User Information
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * Used to assign the User information to User class
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
