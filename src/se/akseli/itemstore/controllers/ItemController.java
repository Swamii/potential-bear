package se.akseli.itemstore.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import se.akseli.itemstore.entities.Item;
import se.akseli.itemstore.services.ItemService;

/**
 * The controller for retrieving items from the ItemService and
 * making it visible to the facelets.
 * @author Swami
 */
@Named
@SessionScoped
public class ItemController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ItemService itemService;
	
	private DataModel<Item> itemModel;
	// the selected item is used for the 'More info...' popup in list.xhtml
	private Item selectedItem;
	
	public ItemController() {}
	
	@PostConstruct
	public void init() {
		itemModel = new ListDataModel<Item>(itemService.getItems());
		selectedItem = new Item();
		System.out.println("ItemController initialized");
	}

	public DataModel<Item> getItemModel() {
		return itemModel;
	}

	public void setItemModel(DataModel<Item> itemModel) {
		this.itemModel = itemModel;
	}

	public Item getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Item selectedItem) {
		System.out.println("Setting selected item to: " + selectedItem.getItemShortDesc());
		this.selectedItem = selectedItem;
	}
	
}
