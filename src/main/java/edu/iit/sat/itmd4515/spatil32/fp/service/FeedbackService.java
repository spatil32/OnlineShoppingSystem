/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Feedback;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dell
 */
@Stateless
public class FeedbackService extends AbstractService<Feedback> {

    /**
     *
     */
    public FeedbackService() {
        super(Feedback.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Feedback> findAll() {
        return em.createNamedQuery("Feedback.seeAllFeedbacks").getResultList();
    }

    /**
     *
     * @param id
     */
    public void deleteFeedbackByCustomerId(Integer id) {
        TypedQuery<Feedback> query = em.createNamedQuery("Feedback.DeleteFeedbackByCustomerId", Feedback.class);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();
    }
}
