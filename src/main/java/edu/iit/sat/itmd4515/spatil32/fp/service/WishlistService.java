/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Wishlist;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dell
 */
@Stateless
public class WishlistService extends AbstractService<Wishlist> {

    private static final Logger LOG = Logger.getLogger(WishlistService.class.getName());

    public WishlistService() {
        super(Wishlist.class);
    }

    @Override
    public List<Wishlist> findAll() {
        return em.createNamedQuery("Wishlist.seeAllWishlists").getResultList();
    }

    public List<Wishlist> findWishlistByCustomerId(Integer id) {
        TypedQuery<Wishlist> query = em.createNamedQuery("Wishlist.seeWishlistsByCustomerId", Wishlist.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public void deleteWishlistProductByProductId(Long id) {
        TypedQuery<Wishlist> query = em.createNamedQuery("Wishlist.deleteWishlistsByProductId", Wishlist.class);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();
        LOG.info("Deleted product from wishlist");
    }

    public void deleteWishlistByCustomerId(Integer id) {
        TypedQuery<Wishlist> query = em.createNamedQuery("Wishlist.deleteWishlistsByCustomerId", Wishlist.class);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();
        LOG.info("Deleted product from wishlist");
    }
}
