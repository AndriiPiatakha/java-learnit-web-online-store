package com.itbulls.learnit.onlinestore.web.owasp.bac.problem;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/get-pic")
public class GetPictureServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String picName = request.getParameter("name");
		if (picName != null && !picName.isBlank()) {
			request.getRequestDispatcher("images/" + picName).forward(request, response);
		}
		
		/*
		 * Validations can be bypassed:
		 * - instead of ../ I can use %2e%2e%2f
		 * - instead of validation of file extension, I can pass null byte
		 * 
		 */
		
	}
}
