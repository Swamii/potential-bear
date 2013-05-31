/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.akseli.itemstore.entities;

import java.io.Serializable;

/**
 * The id class for the OrderItem entity.
 * @author Swami
 */
public class OrderItemPK implements Serializable {
    public Integer orderId;
    public Integer itemId;
    
    public OrderItemPK() {}
    public OrderItemPK(Integer orderId, Integer itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
    
}
