/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.cdi;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author Dell
 */
@Dependent
public class EmailBean
{
    @Resource(lookup = "demo/mailSession")
    private Session session;

    /**
     *
     * @param to
     * @param subject
     * @param body
     */
    public void sendMail(String to, String subject, String body) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(session.getProperty("mail.from")));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
