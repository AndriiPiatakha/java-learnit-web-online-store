package com.itbulls.learnit.onlinestore.web.owasp.cf.problem;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.itbulls.learnit.onlinestore.persistence.utils.DBUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sql-injection-demo")
public class SqlInjectionDemoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (var conn = DBUtils.getConnection()) {
			
			String idParam = request.getParameter("id");
			Statement st = conn.createStatement();
			
			try (var rs = st.executeQuery("SELECT last_name, email FROM user WHERE id = " + idParam)) {
				while (rs.next()) {
					response.getWriter().println("Last name: " 
								+ rs.getString("last_name") + "\tEmail: " 
								+ rs.getString("email"));
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
