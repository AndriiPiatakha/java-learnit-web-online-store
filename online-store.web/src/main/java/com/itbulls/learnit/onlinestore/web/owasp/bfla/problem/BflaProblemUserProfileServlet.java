package com.itbulls.learnit.onlinestore.web.owasp.bfla.problem;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;

public class BflaProblemUserProfileServlet extends HttpServlet {

	private UserFacade userFacade = DefaultUserFacade.getInstance();
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Integer userId = Integer.valueOf(request.getParameter("user_id"));

        User user = userFacade.getUserById(userId);
        if ("updateProfile".equals(action)) {
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
