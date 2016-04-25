/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.service;

import edu.iit.sat.itmd4515.spatil32.fp.model.Customer;
import edu.iit.sat.itmd4515.spatil32.fp.model.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dell
 */
@Stateless
public class ProductService extends AbstractService<Products>
{

    public ProductService() {
        super(Products.class);
    }
    
    @Override
    public List<Products> findAll()
    {
        return em.createNamedQuery("Products.seeAllProducts").getResultList();
    }
    
    public Products findByProductID(Long productId)
    {
         TypedQuery<Products> query = em.createNamedQuery("Products.findProductById", Products.class);
         query.setParameter("id", productId);
         return query.getSingleResult();
    }
}
