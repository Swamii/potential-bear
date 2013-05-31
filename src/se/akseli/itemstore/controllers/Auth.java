package se.akseli.itemstore.controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import se.akseli.itemstore.annotations.LoggedIn;
import se.akseli.itemstore.entities.Customer;
import se.akseli.itemstore.exceptions.CustomerDoesNotExistException;
import se.akseli.itemstore.services.LoginInfoService;

/**
 * @author Swami
 * The Auth class. Handles logging in and out. Provides the currently logged in customer to
 * beans and classes with the producer method.
 */
@Named
@SessionScoped
public class Auth implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String loginName;
	private String password;
	
	@EJB
	private LoginInfoService loginInfoService;
	
	private Customer customer;
	
	public Auth() {}
	
	/**
	 * Login a customer
	 * @throws IOException
	 */
	public void login() throws IOException {
		System.out.println("Logging in: " + loginName + ", with password: " + password);
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
			customer = loginInfoService.find(loginName, password);
		} catch (CustomerDoesNotExistException e) {
			ctx.addMessage(null, 
						   new FacesMessage(
								   FacesMessage.SEVERITY_ERROR,
								   e.getMessage(), 
								   ""));
		}
	}

	/*
	 * Log out a customer and redirect to index.
	 */
	public void logout() throws IOException {
		ExternalContext extCtx = FacesContext.getCurrentInstance().getExternalContext();
		customer = null;
		extCtx.redirect(extCtx.getRequestContextPath() + "/index.xhtml");
	}
	
	public boolean isLoggedIn() {
		return customer != null;
	}
	
	@Produces
	@LoggedIn
	@Named
	public Customer getCurrentCustomer() {
		return customer;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
