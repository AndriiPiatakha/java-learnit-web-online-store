package com.itbulls.learnit.onlinestore.web.controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import com.itbulls.learnit.onlinestore.web.Configurations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/not-found")
public class NotFoundErrorHandlerServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "notfound.jsp").forward(request, response);
	}
}
