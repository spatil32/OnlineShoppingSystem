/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Products;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dell
 */
@Stateless
public class ProductService extends AbstractService<Products> {

    private static final Logger LOG = Logger.getLogger(ProductService.class.getName());

    /**
     *
     */
    public ProductService() {
        super(Products.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Products> findAll() {
        return em.createNamedQuery("Products.seeAllProducts").getResultList();
    }

    /**
     *
     * @param productId
     * @return
     */
    public Products findByProductID(Long productId) {
        TypedQuery<Products> query = em.createNamedQuery("Products.findProductById", Products.class);
        query.setParameter("id", productId);
        return query.getSingleResult();
    }

    /**
     *
     * @param product
     */
    public void updateProducByProductId(Products product) {
        TypedQuery<Products> query = em.createNamedQuery("Products.UpdateProductById", Products.class);
        query.setParameter("name", product.getProductName());
        query.setParameter("mfgDate", product.getMfgDate());
        query.setParameter("category", product.getCategory());
        query.setParameter("price", product.getPrice());
        query.setParameter("discount", product.getDiscount());
        query.setParameter("total", product.getTotalQty());
        query.setParameter("available", product.getAvailableQty());
        query.setParameter("id", product.getProductId());
        int updated = query.executeUpdate();
        LOG.info("Product Updated...");
    }

    /**
     *
     * @param id
     */
    public void deleteProductById(Long id) {
        TypedQuery<Products> query = em.createNamedQuery("Products.DeleteProductById", Products.class);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();
        LOG.info("Product deleted..");
    }
}
