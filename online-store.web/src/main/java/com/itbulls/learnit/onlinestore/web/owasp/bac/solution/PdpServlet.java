package com.itbulls.learnit.onlinestore.web.owasp.bac.solution;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;

import com.itbulls.learnit.onlinestore.core.facades.ProductFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultProductFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.Product;
import com.itbulls.learnit.onlinestore.web.Configurations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pdp")
public class PdpServlet extends HttpServlet {
	
	private ProductFacade productFacade = DefaultProductFacade.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product p = productFacade.getProductByGuid(request.getParameter("guid"));
		
		request.setAttribute("product", p);
		
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "pdp.jsp").forward(request, response);
		
	}

}
