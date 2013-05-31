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
 * Filter to limit the register page only to users not logged in
 */
@ManagedBean
@WebFilter("/register/*")
public class RegisterFilter implements Filter {

	@Inject
	private Auth auth;
	
    public RegisterFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (auth.isLoggedIn()) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(req.getContextPath() + "/index.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
