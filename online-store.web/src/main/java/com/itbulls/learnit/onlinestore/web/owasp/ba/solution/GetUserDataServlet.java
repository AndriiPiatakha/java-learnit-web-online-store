package com.itbulls.learnit.onlinestore.web.owasp.ba.solution;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;
import com.google.gson.Gson;

@WebServlet("/protected-endpoint-get-user-data")
public class GetUserDataServlet extends HttpServlet {

	private UserFacade userFacade = DefaultUserFacade.getInstance();
	private final Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String authHeader = req.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			if (JwtUtils.isTokenValid(token)) {
				try {
					String userEmail = JwtUtils.parseToken(token).getPayload().getSubject();
					System.out.println("User Email: " + userEmail);
					User userData = userFacade.getUserByEmail(userEmail);
					resp.setContentType("application/json");
					resp.getWriter().write("{\"data\": \"" + gson.toJson(userData) + "\"}");
					resp.setStatus(HttpServletResponse.SC_OK);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
}
