package com.itbulls.learnit.onlinestore.web.tags;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.DynamicAttributes;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class DynamicAttributesTagHandler extends SimpleTagSupport implements DynamicAttributes {

	private Map<String, Object> dynamicAttributes = new LinkedHashMap<>();
	
	@Override
	public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
		dynamicAttributes.put(localName, value);
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<ul>");
		for (Map.Entry<String, Object> entry : dynamicAttributes.entrySet()) {
			out.println("<li style=\"color:" + entry.getValue() + "\">" 
					+ entry.getKey() + "</li>");
		}
		out.println("</ul>");
	}
	
	

}
