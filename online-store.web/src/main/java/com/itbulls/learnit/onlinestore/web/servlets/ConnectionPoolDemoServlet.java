package com.itbulls.learnit.onlinestore.web.servlets;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cp-demo")
public class ConnectionPoolDemoServlet extends HttpServlet {
	
	private DataSource ds1;
	
	@Resource(name = "jdbc/connpool")
    private DataSource ds2;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		InitialContext initialContext;
		try {
			initialContext = new InitialContext();
			Context context = (Context) initialContext.lookup("java:comp/env");
			DataSource ds1 = (DataSource) context.lookup("jdbc/connpool");	
			
			Connection conn1 = getConnection(ds1);
			Connection conn2 = getConnection(ds2);
			
			try (conn1; conn2) {
				System.out.println("Connection 1 from DS1:\t" + conn1);
				System.out.println("Connection 2 from DS2:\t" + conn2);
			}
			
			System.out.println("Connection 1 from DS1 after closure:\t" + conn1);
			System.out.println("Connection 2 from DS2 after closure:\t" + conn2);
			
			System.out.println("DS1 equals DS2:\t" + ds1.equals(ds2));
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection(DataSource ds) throws SQLException {
		return ds.getConnection();
	}

}
