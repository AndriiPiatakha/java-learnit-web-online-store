package com.itbulls.learnit.onlinestore.web.servlets;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/demo", "/demo1" }, initParams = {
		@WebInitParam(name = "firstName", value = "Andrii"),
        @WebInitParam(name = "lastName", value = "Piatakha")
})
public class ServletDemo extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = getInitParameter("firstName");
        String lastName = getInitParameter("lastName");

        PrintWriter writer = response.getWriter();

        writer.println("firstName = " + firstName + "; ");
        writer.println("lastName = " + lastName);
        
        writer.println("</br>");
        
        writer.println("Servlet name: " + getServletConfig().getServletName());
        writer.println("</br>");
        writer.println("Servlet name: " + getServletName());
        writer.println("</br>");
        writer.println("Context path: " + getServletContext().getContextPath());
        writer.println("</br>");
        
        getServletContext().setAttribute("locale", Locale.US);
        
        String param = request.getParameter("param");
        writer.println("Request param value: " + param);
        writer.println("</br>");
        writer.println("Request attribute: " + request.getAttribute("attr"));
        
        
	}

}
