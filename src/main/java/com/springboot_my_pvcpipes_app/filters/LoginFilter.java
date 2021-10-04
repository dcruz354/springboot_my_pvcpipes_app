/**
 * 
 */
package com.springboot_my_pvcpipes_app.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author Dcruz
 * Tracking the authenticated user after 
 * user enters correct username and password.
 */
@Component
public class LoginFilter implements Filter {
	
	private final static Logger LOG = LoggerFactory.getLogger(TransactionFilter.class);

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOG.info("Initializing filter :{}", this);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		LOG.info("User: " + username + " is logged in.");
		chain.doFilter(request, response);
		
	}
	
	@Override
	public void destroy() {
		LOG.warn("Destructing filter :{}", this);
	}
	
	

}
