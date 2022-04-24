package com.itbulls.learnit.onlinestore.web.servlets;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;
import com.itbulls.learnit.onlinestore.web.Configurations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UserController extends HttpServlet {
	
	private UserFacade userFacade = DefaultUserFacade.getInstance();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userIdString = request.getParameter("id");
		if (userIdString != null && !userIdString.isBlank()) {
			User user = userFacade.getUserById(Integer.valueOf(userIdString));
			request.setAttribute("user", user);
			request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
					+ "generalUserInfo.jsp").forward(request, response);
		}
	}


}
