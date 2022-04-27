package com.itbulls.learnit.onlinestore.web.controllers;

import static com.itbulls.learnit.onlinestore.persistence.dto.RoleDto.*;

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

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	
	public static final String LOGGED_IN_USER_ATTR = "loggedInUser";
	
	private UserFacade userFacade = DefaultUserFacade.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "signin.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = userFacade.getUserByEmail(request.getParameter("email"));
		String baseUrl = request.getScheme()
			      + "://"
			      + request.getServerName()
			      + ":"
			      + request.getServerPort()
			      + request.getServletContext().getContextPath();
		if (user != null && user.getPassword().equals(request.getParameter("password"))) {
			request.getSession().setAttribute(LOGGED_IN_USER_ATTR, user);
			if (user.getRoleName().equals(ADMIN_ROLE_NAME)) {
				response.sendRedirect(baseUrl + "/admin/panel");
			} else {
				response.sendRedirect(baseUrl + "/homepage");
			}
		} else {
			response.sendRedirect(baseUrl + "/signin");
		}
		
	}

}
