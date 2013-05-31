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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Swami
 */
@Entity
@Table(name="ITEMS")
public class Item implements Serializable {

    @Id
    @Column(name="ITEM_ID")
    @GeneratedValue
    private Integer itemId;
    
    @Column(name="ITEM_NUMBER")
    private String itemNumber;
    
    @Column(name="ITEM_SHORT_DESC")
    private String itemShortDesc;
    
    @Column(name="ITEM_LONG_DESC")
    private String itemLongDesc;
    
    @ManyToMany(mappedBy="items")
    private List<Order> orders;

    /* 
     * Used to store quantity until this entity is persisted. Transient makes sure this field is not
     * added to the database. The quantity is instead used to update the OrderItem table.
     * Not the best solution.
     */
    @Transient
    private Integer quantity;
    
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemShortDesc() {
        return itemShortDesc;
    }

    public void setItemShortDesc(String itemShortDesc) {
        this.itemShortDesc = itemShortDesc;
    }

    public String getItemLongDesc() {
        return itemLongDesc;
    }

    public void setItemLongDesc(String itemLongDesc) {
        this.itemLongDesc = itemLongDesc;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.akseli.itemstore.entities.Item[ id=" + itemId + " ]";
    }
    
}
