package se.akseli.itemstore.filters;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.akseli.itemstore.controllers.Auth;

/**
 * A web filters that makes sure you are logged in before you can access customer specific parts of the site. 
 * @author Swami
 */
@WebFilter("/account/*")
@ManagedBean
public class AuthFilter implements Filter {
	
	@Inject
	private Auth auth;
	
    public AuthFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		try {
			if (!auth.isLoggedIn()) {
				res.sendRedirect(req.getContextPath() + "/pleaselogin.xhtml");
			} else {
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			res.sendRedirect(req.getContextPath() + "/index.xhtml");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
