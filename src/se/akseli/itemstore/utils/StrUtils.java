package se.akseli.itemstore.utils;

import java.util.Random;

/**
 * String utility functions
 * @author Swami
 */
public class StrUtils {
	private StrUtils() {}
	
	/**
	 * Generates a order number randomly.
	 * @param length The length of the order number.
	 * @return The order number.
	 */
	public static String generateOrderNumber(int length) {  
        String valid = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";  
        String res = "";  
        Random random = new Random();  
        while (0 < length--) {
            res += valid.charAt(random.nextInt(valid.length()));
        }
        return res;
    } 
}
