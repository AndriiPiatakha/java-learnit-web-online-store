package com.itbulls.learnit.onlinestore.web.servlets;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/jstl-demo")
public class JstlDemoServlet extends HttpServlet {
	
	private UserFacade userFacade = DefaultUserFacade.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = userFacade.getUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/jstl-demo.jsp").forward(request, response);
	}
}
