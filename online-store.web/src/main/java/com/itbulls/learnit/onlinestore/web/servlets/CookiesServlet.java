package com.itbulls.learnit.onlinestore.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cookies-demo")
public class CookiesServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get an array of Cookies associated with this domain
		Cookie[] cookies = request.getCookies();

		// Set response content type
		response.addCookie(new Cookie("name", "value"));

		PrintWriter out = response.getWriter();
		String title = "Reading Cookies Example";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
				+ "<body bgcolor = \"#f0f0f0\">\n");
		out.println("<h2> Found Cookies Name and Value</h2>");

		
		
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
// 				to delete cookie setMaxAge to zero and add this cookie back to response
//				cookie.setMaxAge(0);
//				response.addCookie(cookie);
			out.print("Name : " + cookie.getName() + ",  ");
			out.print("Value: " + cookie.getValue() + " <br>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}