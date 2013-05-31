/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.akseli.itemstore.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author Swami
 */
@Entity
@Table(name="ORDER_ITEMS")
@IdClass(value=OrderItemPK.class)
public class OrderItem implements Serializable {

    @Id
    @Column(name="ORDER_ID")
    private Integer orderId;
    
    @Id
    @Column(name="ITEM_ID")
    private Integer itemId;
    
    @Column(name="ITEM_QTY")
    private Integer itemQty;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

}
