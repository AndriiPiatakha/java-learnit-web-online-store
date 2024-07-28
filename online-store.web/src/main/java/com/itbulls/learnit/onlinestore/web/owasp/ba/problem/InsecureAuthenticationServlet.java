package com.itbulls.learnit.onlinestore.web.owasp.ba.problem;

import java.io.IOException;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insecure-auth-servlet")
public class InsecureAuthenticationServlet extends HttpServlet {

	private UserFacade userFacade = DefaultUserFacade.getInstance();
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
		User user = userFacade.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            String token = generateWeakToken(email);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"token\": \"" + token + "\"}");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String generateWeakToken(String email) {
        // Weak token generation using email only, no expiration policy, easy to guess
        return "token_" + email;
    }
}
