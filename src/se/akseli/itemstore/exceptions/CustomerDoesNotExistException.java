package se.akseli.itemstore.exceptions;

/**
 * Exception thrown when a customer cant be found 
 * @author Swami
 */
public class CustomerDoesNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CustomerDoesNotExistException(String message) {
		super(message);
	}

}
