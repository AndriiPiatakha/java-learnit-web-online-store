package com.itbulls.learnit.onlinestore.web.controllers;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import com.itbulls.learnit.onlinestore.core.facades.PurchaseFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultPurchaseFacade;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/management-fulfilment", name = "fulfilment")
public class FulfilmentServlet extends HttpServlet {
	
	private PurchaseFacade purchaseFacade = DefaultPurchaseFacade.getInstance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		purchaseFacade
			.markFulfilmentStageForPurchaseIdAsCompleted(
					Integer.valueOf(request.getParameter("purchaseId")));
		
		String baseUrl = request.getScheme()
			      + "://"
			      + request.getServerName()
			      + ":"
			      + request.getServerPort()
			      + request.getServletContext().getContextPath();
		
		response.sendRedirect(baseUrl + "/management-orders"); 
	}

}
