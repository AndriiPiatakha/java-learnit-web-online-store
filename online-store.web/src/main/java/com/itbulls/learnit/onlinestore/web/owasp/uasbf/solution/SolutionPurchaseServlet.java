package com.itbulls.learnit.onlinestore.web.owasp.uasbf.solution;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SolutionPurchaseServlet extends HttpServlet {
	private static final int MAX_ITEMS_PER_PRODUCT = 10; // Maximum items a user can purchase per product

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in.");
			return;
		}

		String userId = (String) session.getAttribute("user");
		String itemId = request.getParameter("itemId");
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		// Check current purchase limits for this specific product
		if (!isPurchaseAllowed(userId, itemId, quantity)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN,
					"Cannot purchase more than " + MAX_ITEMS_PER_PRODUCT + " items of this product. "
							+ "If you want to purchase more, reach to our sales department.");
			return;
		}

		boolean success = processPurchase(userId, itemId, quantity);

		if (success) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Purchase failed.");
		}
	}

	private boolean isPurchaseAllowed(String userId, String itemId, int quantity) {
		int totalPurchased = getTotalItemsPurchased(userId, itemId); // Retrieve the total items purchased by the user
																		// for this product
		return (totalPurchased + quantity) <= MAX_ITEMS_PER_PRODUCT;
	}

	private boolean processPurchase(String userId, String itemId, int quantity) {
		// Implement actual purchase processing logic here, including updating the
		// database
		updateTotalItemsPurchased(userId, itemId, quantity); // Update the number of items purchased by the user for
																// this product
		return true; // Simplified for this example
	}

	private int getTotalItemsPurchased(String userId, String itemId) {
		// Retrieve the total number of this specific product purchased by the user from
		// the database
		// This is a placeholder; actual implementation will involve database queries
		return 0; // Simplified for this example
	}

	private void updateTotalItemsPurchased(String userId, String itemId, int quantity) {
		// Update the total number of this specific product purchased by the user in the
		// database
		// This is a placeholder; actual implementation will involve database updates
	}
}
