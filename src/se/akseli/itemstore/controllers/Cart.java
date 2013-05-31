package se.akseli.itemstore.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import se.akseli.itemstore.entities.Item;

/**
 * @author Swami
 * The session scoped cart. Works even when not logged in.
 */

@Named
@SessionScoped
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Item> items = new ArrayList<Item>();
	
	@PostConstruct
	public void init() {
		System.out.println("Cart initialized");
	}
	
	/**
	 * Adds an item to the cart. Used from facelets. 
	 * Checks that there isn't already an item with the same id in the cart.
	 * @param item
	 */
	public void addItem(Item item) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (items.contains(item)) {
			ctx.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					item.getItemShortDesc() + 
					" (" + item.getItemLongDesc() + 
					") already in cart.",
					""));
		} else {
			System.out.println("Adding item: " + item.getItemShortDesc());
			if (item.getQuantity() == null) {
				item.setQuantity(1);
			}
			items.add(item);
			ctx.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					item.getItemShortDesc() + " successfully added to cart.",
					""));
		}
	}
	
	public void clear() {
		items = new ArrayList<Item>();
	}
	
	/**
	 * Removes an item from the cart.
	 * @param item
	 */
	public void removeItem(Item item) {
		System.out.println("Removing item: " + item.getItemShortDesc());
		items.remove(item);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO,
				item.getItemShortDesc() + " removed from the cart.",
				""));
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
