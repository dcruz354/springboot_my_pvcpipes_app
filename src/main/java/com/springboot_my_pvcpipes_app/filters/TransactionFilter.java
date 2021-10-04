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
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Dcruz
 * Filtering transactions on the request.
 * Capturing the request's URL.
 */
@Component
@Order(1)
public class TransactionFilter implements Filter {
	
	private final static Logger LOG = LoggerFactory.getLogger(TransactionFilter.class);
	
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOG.info("Initializing filter :{}", this);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		LOG.info("Starting Transaction for req :{}", req.getRequestURI());
		chain.doFilter(request, response);
		LOG.info("Committing Transaction for req :{}", req.getRequestURI());
	}
	
	@Override
	public void destroy() {
		LOG.warn("Destructing filter :{}", this);
	}

}
