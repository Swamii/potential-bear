package se.akseli.itemstore.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import se.akseli.itemstore.entities.Customer;
import se.akseli.itemstore.entities.LoginInfo;
import se.akseli.itemstore.services.CustomerService;
import se.akseli.itemstore.services.LoginInfoService;

/**
 * Bean made for registering customers
 * @author Swami
 */
@Named
@RequestScoped
public class Register {
	
	@EJB
	private LoginInfoService loginInfoService;
	@EJB
	private CustomerService customerService;
	@Inject
	private Auth auth;
	
	private Customer customer = new Customer();
	private LoginInfo loginInfo = new LoginInfo();
	
	public void register() throws IOException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extCtx = ctx.getExternalContext();
		
		if (!auth.isLoggedIn()) {
			customer = customerService.persist(customer);
			loginInfo.setCustomer(customer);
			loginInfo = loginInfoService.persist(loginInfo);
			auth.setLoginName(loginInfo.getLoginName());
			auth.setPassword(loginInfo.getPassword());
			auth.login();
			ctx.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Registration successful.",
					""));
			extCtx.getFlash().setKeepMessages(true);
			extCtx.redirect(extCtx.getRequestContextPath() + "/index.xhtml");
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	
}
