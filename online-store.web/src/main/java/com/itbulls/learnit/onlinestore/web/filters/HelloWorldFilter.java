package com.itbulls.learnit.onlinestore.web.filters;

import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class HelloWorldFilter
 */
//@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
//					, urlPatterns = { "/*" })
public class HelloWorldFilter extends HttpFilter {
	
	public void init(FilterConfig config) {
		System.out.println(config.getInitParameter("initParam"));
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Hello World!");
		chain.doFilter(request, response);
		
	}

}
