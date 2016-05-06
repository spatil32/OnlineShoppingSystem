/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.cdi.EmailBean;
import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.Orders;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Dell
 */
@Stateless
public class EmailService {

    private static final Logger LOG = Logger.getLogger(EmailService.class.getName());

    private String firstName;
    private String lastName;
    private String email;

    @Inject
    EmailBean emailBean;

    public EmailService() {
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public void doSendMail(Customer customer, Orders order) {
        LOG.log(Level.INFO, "Sending mail to {0}", customer.getFirstName());
        LOG.log(Level.INFO, "Sending mail on email {0}", customer.getEmail());

        String subject = "Order Confirmation";
        String body = "Hi " + customer.getFirstName() + ",\n Your order has been placed.!!!\n The total bill is "
                + order.getTotalBillAmount() + " & will be deliverd by " + order.getDeliveryDate()
                + "\n\n Thank you for shopping with us!! \n\n Enjoy shopping with our flat rate discounts in seasons.\n"
                + "We hope to see you again.!!";

        emailBean.sendMail(customer.getEmail(), subject, body);
        LOG.log(Level.INFO, "Mail sent to {0}", customer.getEmail());
    }

}
