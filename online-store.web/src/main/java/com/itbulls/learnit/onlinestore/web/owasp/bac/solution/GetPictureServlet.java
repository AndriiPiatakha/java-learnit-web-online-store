package com.itbulls.learnit.onlinestore.web.owasp.bac.solution;

import jakarta.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/get-pic-solution")
public class GetPictureServlet extends HttpServlet {
	
	private List<String> imageNames = new ArrayList<>();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		populateImageNames();
		
		String picName = request.getParameter("name");
		if (picName != null && !picName.isBlank()) {
			if (imageNames.contains(picName)) {
				request.getRequestDispatcher("images/" + picName).forward(request, response);
			} else {
				response.getWriter().println("No picutre with such name is available.");
			}
		}
	}


	private void populateImageNames() throws MalformedURLException {
		ServletContext context = getServletContext();
		URL imagesUrl = context.getResource("/images");
		try {
			Path imagesPath = Paths.get(imagesUrl.toURI());
			File f = imagesPath.toFile();
			Arrays.stream(f.listFiles()).forEach(image -> imageNames.add(image.getName()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
