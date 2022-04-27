package com.itbulls.learnit.onlinestore.web.filters;

import static com.itbulls.learnit.onlinestore.web.controllers.SignInServlet.*;
import static com.itbulls.learnit.onlinestore.persistence.dto.RoleDto.*;

import java.io.IOException;

import com.itbulls.learnit.onlinestore.persistence.enteties.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/admin/*")
public class AdminFilter extends HttpFilter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		User user = (User)((HttpServletRequest)request).getSession().getAttribute(LOGGED_IN_USER_ATTR);
		if (user != null) {
			if (user.getRoleName().equals(ADMIN_ROLE_NAME)) {
				chain.doFilter(request, response);
			} else {
				((HttpServletResponse)response).sendError(403);
			}
		} else {
			((HttpServletResponse)response).sendRedirect(
					request.getScheme()
				      + "://"
				      + request.getServerName()
				      + ":"
				      + request.getServerPort()
				      + request.getServletContext().getContextPath() + "/signin");
		}
		
	}

}
