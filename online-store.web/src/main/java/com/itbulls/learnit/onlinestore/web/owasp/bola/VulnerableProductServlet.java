package com.itbulls.learnit.onlinestore.web.owasp.bola;

import java.io.IOException;

import com.itbulls.learnit.onlinestore.core.facades.ProductFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultProductFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bola-product-demo-problem")
public class VulnerableProductServlet extends HttpServlet {

	private ProductFacade productFacade = DefaultProductFacade.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer productId = Integer.valueOf(req.getParameter("productId"));
		Product product = productFacade.getProductById(productId);

		if (product != null) {
			resp.getWriter().write("Product: " + product);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
		}
	}
	
}