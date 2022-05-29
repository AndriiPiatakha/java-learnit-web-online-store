package com.itbulls.learnit.onlinestore.web.owasp.i.problem;

import jakarta.servlet.http.HttpServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/comm-inj")
public class CommandInjectionServletDemo extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String comm = "cmd.exe /c dir " + ".\\products\\" 
					+ request.getParameter("productCategory");
			Process process = Runtime.getRuntime().exec(comm);
			BufferedReader stdInput = new BufferedReader(
					new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));

			String s = null;
			while ((s = stdInput.readLine()) != null) {
				response.getWriter().println(s);
			}
		} catch (IOException e) {
			System.out.println("Error executing command");
		}

	}

}
