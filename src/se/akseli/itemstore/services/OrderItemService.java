package se.akseli.itemstore.services;

import javax.ejb.Stateless;

import se.akseli.itemstore.entities.OrderItem;

/**
 * Class with functions to retrieve and modify the OrderItem join table
 * @author Swami
 */
@Stateless
public class OrderItemService extends DatabaseService<OrderItem> {
	
	public OrderItemService() {
		super(OrderItem.class);
	}
	
}
