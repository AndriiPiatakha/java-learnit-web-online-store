package com.itbulls.learnit.onlinestore.web.owasp.ba.problem;

import java.io.IOException;

import com.google.gson.Gson;
import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insecure-get-user-data")
public class InsecureGetUserDataServlet extends HttpServlet {

	private UserFacade userFacade = DefaultUserFacade.getInstance();
	private final Gson gson = new Gson();
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = validateWeakToken(token);
            if (email != null) {
                User userData = userFacade.getUserByEmail(email);
                resp.setContentType("application/json");
                resp.getWriter().write("{\"data\": \"" + gson.toJson(userData) + "\"}");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String validateWeakToken(String token) {
        // Improper token validation, just extracts the email without verifying integrity
        if (token.startsWith("token_")) {
            return token.substring(6);
        }
        return null;
    }

}