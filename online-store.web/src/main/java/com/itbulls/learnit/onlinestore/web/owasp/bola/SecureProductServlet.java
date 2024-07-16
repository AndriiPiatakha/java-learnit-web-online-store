package com.itbulls.learnit.onlinestore.web.owasp.bola;

import java.io.IOException;

import com.itbulls.learnit.onlinestore.core.facades.ProductFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultProductFacade;
import com.itbulls.learnit.onlinestore.persistence.dto.RoleDto;
import com.itbulls.learnit.onlinestore.persistence.enteties.Product;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;
import com.itbulls.learnit.onlinestore.web.controllers.SignInServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bola-product-demo-solution")
public class SecureProductServlet extends HttpServlet {

	private ProductFacade productFacade = DefaultProductFacade.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productGuid = req.getParameter("productGuid");
		User loggedInUser = (User)req.getSession().getAttribute(SignInServlet.LOGGED_IN_USER_ATTR);
		Product product = productFacade.getProductByGuid(productGuid);

		if (product != null && product.getProductName() != null) {
			if (loggedInUser != null && loggedInUser.getRoleName().equals(RoleDto.ADMIN_ROLE_NAME)) {
				resp.getWriter().write("Product: " + product);
			} else {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized access to read product");
			}
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
		}
	}
}
