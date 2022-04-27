package com.itbulls.learnit.onlinestore.web.controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

import com.itbulls.learnit.onlinestore.core.facades.PurchaseFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultPurchaseFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.Purchase;
import com.itbulls.learnit.onlinestore.web.Configurations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/management-orders", name = "purchase")
public class PurchasesServlet extends HttpServlet {
	
	private PurchaseFacade purchaseFacade = DefaultPurchaseFacade.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Purchase> purchases = purchaseFacade.getNotCompletedPurchases();
		request.setAttribute("purchases", purchases);
		request
			.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
					+ "orders.jsp").forward(request, response);
	}

}
