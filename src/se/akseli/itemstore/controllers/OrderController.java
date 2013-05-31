package se.akseli.itemstore.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import se.akseli.itemstore.annotations.LoggedIn;
import se.akseli.itemstore.entities.Customer;
import se.akseli.itemstore.entities.Order;
import se.akseli.itemstore.services.OrderService;
import se.akseli.itemstore.utils.StrUtils;

/**
 * @author Swami
 * The controller of orders. Getting and adding.
 */
@Named
@RequestScoped
public class OrderController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private OrderService orderService;
	
	@Inject
	@LoggedIn
	private Customer currentCustomer;
	
	@Inject
	private Cart cart;
	
	private List<Order> orders;
	
	private Order newOrder;
	
	public OrderController() {}
	
	@PostConstruct
	public void init() {
		orders = orderService.getOrders(currentCustomer);
		newOrder = new Order();
	}
	
	/**
	 * Place a new order. Requires a customer to be logged in and the cart having at least one item.
	 */
	public void placeOrder() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extCtx = ctx.getExternalContext();
		
		if (currentCustomer == null || cart.getItems().size() < 1) {
			ctx.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Error adding order", 
					""));
			return;
		}
		newOrder.setOrderNumber(StrUtils.generateOrderNumber(10));
		try {
			orderService.newOrder(newOrder, currentCustomer, cart.getItems());
			cart.clear();
			extCtx.redirect(
					extCtx.getRequestContextPath() + "/account/order_success.xhtml");
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Error adding order", 
					""));
			e.printStackTrace();
		}
	} 
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(Order newOrder) {
		this.newOrder = newOrder;
	}
	
}
