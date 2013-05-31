package se.akseli.itemstore.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import se.akseli.itemstore.entities.Item;

/**
 * Class for retrieving items from database. It's a singleton since the items won't change during the app's up time.
 * Caches the items for as long as the app is running.
 * @author Swami
 */
@Singleton
public class ItemService extends DatabaseService<Item> {
	
	List<Item> items;

	public ItemService() {
		super(Item.class);
	}
	
	@PostConstruct
	public void init() {
		items = super.findWithQuery("select i from Item i");
		System.out.println(items.size() + " items retrieved from database.");
	}
	
	public List<Item> getItems() {
		return items;
	}
}
