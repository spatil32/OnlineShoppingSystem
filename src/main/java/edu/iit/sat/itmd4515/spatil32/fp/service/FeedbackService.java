/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Feedback;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Dell
 */
@Stateless
public class FeedbackService extends AbstractService<Feedback>
{

    public FeedbackService() {
        super(Feedback.class);
    }
    
    @Override
    public List<Feedback> findAll() 
    {
        return em.createNamedQuery("Feedback.seeAllFeedbacks").getResultList();
    }
}
