package com.itbulls.learnit.onlinestore.web.servlets;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/form-demo")
public class FormDemoServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var writer = response.getWriter();
		writer.println("<p>Text Input: " + request.getParameter("text") + "</p>");
		writer.println("<p>Password Input: " + request.getParameter("password") + "</p>");
		writer.println("<p>Email Input: " + request.getParameter("email") + "</p>");
		writer.println("<p>Date Input: " + request.getParameter("date") + "</p>");
		writer.println("<p>Radio Input: " + request.getParameter("radio") + "</p>");
		writer.println("<p>Checkbox Input: " + request.getParameter("checkbox") + "</p>");
		writer.println("<p>Disabled Input: " + request.getParameter("disabled") + "</p>");
		writer.println("<p>Readonly Input: " + request.getParameter("readonly") + "</p>");
		writer.println("<p>Hidden Input: " + request.getParameter("hidden") + "</p>");
		writer.println("<p>Car Input: " + request.getParameter("car") + "</p>");

	}
	

}
