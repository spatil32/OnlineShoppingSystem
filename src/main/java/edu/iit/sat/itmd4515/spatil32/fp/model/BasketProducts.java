/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.fp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "spatil32_basket_products")
@NamedQueries({
    @NamedQuery(name = "BasketProducts.SelectAll", query = "select b from BasketProducts b"),
    @NamedQuery(name = "BasketProducts.FindByProductId", query = "select b from BasketProducts b where b.productId = :id"),
    @NamedQuery(name = "BasketProducts.FindByBasketId", query = "select b from BasketProducts b where b.basketId = :id"),
    @NamedQuery(name = "BasketProducts.DeleteByBasketId", query = "delete from BasketProducts b where b.basketId = :id")
})
public class BasketProducts 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int basketProductId;
    private int basketId;
    private int productId;    
    

    public BasketProducts() {
    }

    public BasketProducts(int productId) {
        this.productId = productId;
    }

    public BasketProducts(int productId, int basketId) {
        this.productId = productId;
        this.basketId = basketId;
    }

    @Override
    public String toString() {
        return "BasketProducts{" + "basketProductId=" + basketProductId + ", basketId=" + basketId + ", productId=" + productId + '}';
    }

    public int getBasketId() {
        return basketId;
    }

    public int getProductId() {
        return productId;
    }

    
}
