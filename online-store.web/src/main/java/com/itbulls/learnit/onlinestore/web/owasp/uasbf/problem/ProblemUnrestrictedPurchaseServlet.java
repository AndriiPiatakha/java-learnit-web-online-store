package com.itbulls.learnit.onlinestore.web.owasp.uasbf.problem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProblemUnrestrictedPurchaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("itemId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        boolean success = processPurchase(itemId, quantity);

        if (success) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Purchase failed.");
        }
    }

    private boolean processPurchase(String itemId, int quantity) {
        // Implement actual purchase processing logic here
        // No limit on the quantity of items purchased
        return true; // Simplified for this example
    }
}
