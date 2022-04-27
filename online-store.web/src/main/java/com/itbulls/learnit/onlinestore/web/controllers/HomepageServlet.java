package com.itbulls.learnit.onlinestore.web.controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.itbulls.learnit.onlinestore.core.facades.CategoryFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultCategoryFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.Category;
import com.itbulls.learnit.onlinestore.web.Configurations;

@WebServlet("/homepage")
public class HomepageServlet extends HttpServlet {

	private CategoryFacade categoryFacade = DefaultCategoryFacade.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = categoryFacade.getCategories();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "homepage.jsp").forward(request, response);
	}

}
