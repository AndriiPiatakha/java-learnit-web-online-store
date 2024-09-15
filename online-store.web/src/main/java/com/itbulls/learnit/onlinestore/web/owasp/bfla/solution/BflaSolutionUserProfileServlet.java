package com.itbulls.learnit.onlinestore.web.owasp.bfla.solution;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;

public class BflaSolutionUserProfileServlet extends HttpServlet {

	private UserFacade userFacade = DefaultUserFacade.getInstance();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		// Ensure the user is logged in and retrieve the logged-in user's ID
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("user");

		if (loggedInUser == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, 
					"You must be logged in to perform this action.");
			return;
		}

		Integer userId = Integer.valueOf(request.getParameter("user_id"));

		// Authorization check: Ensure that the user can only update their own profile or ADMIN user
		if (!loggedInUser.getId().equals(userId) && !loggedInUser.getRoleName().equals("ROLE_ADMIN")) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to update this profile.");
			return;
		}

		if ("updateProfile".equals(action)) {
			User user = userFacade.getUserById(userId);

			// Ensure that user exists
			if (user == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
				return;
			}

			// Update profile information
			user.setCreditCard(request.getParameter("credit_card_number"));
			user.setEmail(request.getParameter("email"));

			// Update profile in the database
			userFacade.updateUser(user);
			response.sendRedirect("/profile");
		} else {
			// Handle other actions
			response.getWriter().println("Invalid action");
		}
	}
}