/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.cdi.EmailBean;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author Dell
 */
@Singleton
public class TimerBean {

    @Inject
    private EmailBean emailBean;
    private static final Logger LOG = Logger.getLogger(TimerBean.class.getName());

    /**
     *
     */
    @Schedule(dayOfWeek = "Mon-Fri",
            month = "*",
            hour = "1-23",
            dayOfMonth = "*",
            year = "*",
            minute = "*",
            second = "*/5",
            persistent = false)
    public void myTimer() {
        LOG.log(Level.INFO, "Timer Event : {0}", new Date());
        String body = "Hello there, We are offering 30% discount on all Electronics Products."
                + " Login and shop to avail this cool shopping opportunity.";
        emailBean.sendMail("patilsr91@gmail.com", "Exciting offers..!!", body);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
