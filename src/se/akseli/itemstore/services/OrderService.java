package se.akseli.itemstore.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import se.akseli.itemstore.entities.Customer;
import se.akseli.itemstore.entities.Item;
import se.akseli.itemstore.entities.Order;
import se.akseli.itemstore.entities.OrderItem;
import se.akseli.itemstore.entities.OrderItemPK;

/**
 * The class made for retrieving and adding orders to the database
 * @author Swami
 */
@Stateless
public class OrderService extends DatabaseService<Order> {
	
	@EJB
	private OrderItemService orderItemService;
	
	public OrderService() {
		super(Order.class);
	}
	
	/**
	 * Function to retrieve past orders by a customer
	 * @param Customer currently logged in (usually)
	 * @return List of orders of said customer
	 */
	public List<Order> getOrders(Customer customer) {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("customer", customer);
		String query = "select o from Order o where o.customer = :customer";
		List<Order> orders = super.findWithQuery(query, map);
		for (Order o : orders) {
			for (Item i : o.getItems()) {
				OrderItemPK orderItemId = new OrderItemPK(o.getOrderId(), i.getItemId());
				i.setQuantity(orderItemService.find(orderItemId)
								 .getItemQty());
			}
		}
		return orders;
	}
	
	/**
	 * Function to place a new order
	 * @param order The order
	 * @param customer The customer that made the order
	 * @param items The items that will be in the order
	 * @throws Exception Any kind of database exception
	 */
	public void newOrder(Order order, Customer customer, List<Item> items) throws Exception {
		// since the quantity disappears at commit we need to store it outside the transaction
		LinkedList<Integer> qtys = new LinkedList<Integer>();
		for (Item i : items) {
			qtys.add(i.getQuantity());
		}
		
		order.setCustomer(customer);
		order.setItems(items);
		order = super.persist(order);
		
		/* 
		 * A kind of inefficient loop to retrieve the OrderItem entity and add the quantity.
		 * TODO: Refactoring the entities, making the OrderItem entity owner of the item and order
		 * and explicitly persisting it would probably be more efficient.
		 */
		for (Item i : order.getItems()) {
			OrderItem oi = orderItemService.find(new OrderItemPK(order.getOrderId(), i.getItemId()));
			oi.setItemQty(qtys.pop());
			orderItemService.update(oi);
		}
	}
	
}
