package com.itbulls.learnit.onlinestore.web.owasp.iim.problem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//Deprecated endpoint for debugging purposes
@WebServlet("/api/debug-info")
public class ProblemDebugInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Exposes sensitive server and application info
		String serverInfo = "Server Info: [OS: Linux, Java Version: 11, JVM Args: ...]";
		String appLogs = "Recent Logs: [INFO: Startup complete, ERROR: Null pointer exception ...]";

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println(serverInfo);
		out.println(appLogs);
	}
}
