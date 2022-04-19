package com.itbulls.learnit.onlinestore.web.tags;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class HelloTagHandler extends SimpleTagSupport {
	private String message;
	private Collection<Object> coll;

	public void setMessage(String msg) {
		this.message = msg;
	}
	
	public void setColl(Collection<Object> coll) {
		this.coll = coll;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		if (coll != null) {
			out.println("<p>Hello from Custom Tag!" + " Value of 'message' attribute: \"" 
					+ message + "\" and size of \"coll\" attribue is " + coll.size() + "</p>");
		} else {
			out.println("<p>Hello from Custom Tag!" + " Value of 'message' attribute: \"" 
					+ message + "\"</p>");
		}
		
	}
}
