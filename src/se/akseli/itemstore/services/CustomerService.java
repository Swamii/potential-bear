package se.akseli.itemstore.services;

import javax.ejb.Stateless;

import se.akseli.itemstore.entities.Customer;

/**
 * Class that can be used to insert, update and remove customers.
 * @author Swami
 */
@Stateless
public class CustomerService extends DatabaseService<Customer> {
	
	public CustomerService() {
		super(Customer.class);
	}
	
}
