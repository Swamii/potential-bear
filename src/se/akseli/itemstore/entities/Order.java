/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.akseli.itemstore.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Swami
 */
@Entity
@Table(name="ORDERS")
public class Order implements Serializable {
    
    @Id
    @Column(name="ORDER_ID")
    @GeneratedValue
    private Integer orderId;
    
    @Column(name="ORDER_NUMBER")
    private String orderNumber;
    
    @Column(name="ORDER_DESCRIPTION")
    private String orderDescription;
    
    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    
    @ManyToMany
    @JoinTable(name="ORDER_ITEMS",
            joinColumns = @JoinColumn(name="ORDER_ID",
                referencedColumnName="ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name="ITEM_ID",
                referencedColumnName="ITEM_ID"))
    private List<Item> items;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.akseli.itemstore.entities.Order[ id=" + orderId + " ]";
    }
    
}
