package com.itbulls.learnit.onlinestore.web.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, 
		HttpSessionAttributeListener {

    @Override
	public void attributeAdded(HttpSessionBindingEvent event) {
    	System.out.println("Attribute is added to the session. "
    			+ "Attribute name: " + event.getName() + "; Attribute value: " + event.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("Attribute is removed from the session");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("Attribute is replaced in the session");
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("Session is created");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Session is destroyed");
	}

}
