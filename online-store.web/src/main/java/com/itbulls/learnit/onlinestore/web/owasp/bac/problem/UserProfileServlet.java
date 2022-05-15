package com.itbulls.learnit.onlinestore.web.owasp.bac.problem;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;
import com.itbulls.learnit.onlinestore.web.Configurations;
import com.itbulls.learnit.onlinestore.web.controllers.SignInServlet;
import com.itbulls.learnit.onlinestore.web.filters.PartnerCodeFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user-profile")
public class UserProfileServlet extends HttpServlet {

	private UserFacade userFacade = DefaultUserFacade.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stringId = request.getParameter("id");
		if (stringId != null && !stringId.isBlank()) {
			User user = userFacade.getUserById(Integer.valueOf(stringId));
			
			String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getServletContext().getContextPath();
			String partnerLink = baseUrl + "?" + PartnerCodeFilter.PARTNER_CODE_PARAMETER_NAME + "="
					+ user.getPartnerCode();
			List<User> referrals = userFacade.getReferralsForUser(user);
			request.setAttribute(SignInServlet.LOGGED_IN_USER_ATTR, user);
			request.setAttribute("referrals", referrals);
			request.setAttribute("partnerLink", partnerLink);
			request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "/myProfile.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "/signin.jsp").forward(request, response);
		}

	}
}
