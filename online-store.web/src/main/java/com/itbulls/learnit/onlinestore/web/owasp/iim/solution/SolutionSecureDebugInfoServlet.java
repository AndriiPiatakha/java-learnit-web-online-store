package com.itbulls.learnit.onlinestore.web.owasp.iim.solution;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//Updated and secured endpoint
@WebServlet("/api/admin/debug-info")
public class SolutionSecureDebugInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		// Ensure the user is authenticated
		if (session == null || session.getAttribute("user") == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().println("Unauthorized access");
			return;
		}

		// Ensure the user has admin privileges
		String userRole = (String) session.getAttribute("role");
		if (!"ROLE_ADMIN".equals(userRole)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getWriter().println("Forbidden access");
			return;
		}

		// Access to debugging information should be restricted and controlled
		String serverInfo = "Server Info: [OS: Linux, Java Version: 11]";
		String appLogs = "Debugging Logs: [Access restricted]";

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println(serverInfo);
		out.println(appLogs);
	}
}