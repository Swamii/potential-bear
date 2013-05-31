package se.akseli.itemstore.services;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.Stateless;

import se.akseli.itemstore.entities.Customer;
import se.akseli.itemstore.entities.LoginInfo;
import se.akseli.itemstore.exceptions.CustomerDoesNotExistException;

/**
 * @author Swami
 * Class with functions made for the LoginInfo entity.
 * Logging customers in.
 */
@Stateless
public class LoginInfoService extends DatabaseService<LoginInfo> {
	
	public LoginInfoService() {
		super(LoginInfo.class);
	}
	
	/**
	 * Retrieves a Customer based on the parameters specified. Used to log a user in.
	 * @param loginName
	 * @param password
	 * @return The customer matching the params
	 * @throws CustomerDoesNotExistException
	 */
	public Customer find(String loginName, String password) throws CustomerDoesNotExistException {
		String query = "select li from LoginInfo li where li.loginName = :loginName and li.password = :password";
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("loginName", loginName);
		params.put("password", password);
		List<LoginInfo> lis = super.findWithQuery(query, params);
		
		if (lis.size() != 1) {
			throw new CustomerDoesNotExistException("Wrong login name or password");
		} else {
			return lis.get(0).getCustomer();
		}
	}
	
}
