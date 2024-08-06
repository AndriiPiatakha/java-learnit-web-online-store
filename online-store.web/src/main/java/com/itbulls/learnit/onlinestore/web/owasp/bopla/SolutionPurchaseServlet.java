package com.itbulls.learnit.onlinestore.web.owasp.bopla;

import static com.itbulls.learnit.onlinestore.persistence.dto.RoleDto.ADMIN_ROLE_NAME;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Base64;

import com.google.gson.Gson;
import com.itbulls.learnit.onlinestore.core.facades.PurchaseFacade;
import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultPurchaseFacade;
import com.itbulls.learnit.onlinestore.core.facades.impl.DefaultUserFacade;
import com.itbulls.learnit.onlinestore.persistence.dto.PurchaseDto;
import com.itbulls.learnit.onlinestore.persistence.dto.converters.PurchaseDtoToPurchaseConverter;
import com.itbulls.learnit.onlinestore.persistence.enteties.Purchase;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bopla/solution/purchases/*")
public class SolutionPurchaseServlet extends HttpServlet {

    private PurchaseFacade purchaseFacade = DefaultPurchaseFacade.getInstance(); // Assume this facade handles data operations
    private UserFacade userFacade = DefaultUserFacade.getInstance(); // Assume this facade handles user operations
    private Gson gson = new Gson(); // For JSON conversion
    private PurchaseDtoToPurchaseConverter purchaseConverter = new PurchaseDtoToPurchaseConverter();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer purchaseId = Integer.parseInt(request.getPathInfo().substring(1)); // Extract purchaseId from URL
            
            // Extract user from Basic Auth
            User authenticatedUser = extractUserFromBasicAuth(request, response);
            if (authenticatedUser == null) {
                return; // If user is null, it means authentication failed
            }
            Purchase purchase = purchaseFacade.getPurchaseById(purchaseId); // Facade retrieves DTO from DAO, and converts it to business object

            // Authorization check
            if (!purchase.getCustomer().equals(authenticatedUser) && !authenticatedUser.getRoleName().equals(ADMIN_ROLE_NAME)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Access is denied.");
                return;
            }

            // Optionally apply any additional business logic to the Purchase object
            // For example, let's set Credit Card Number and Customer information to null before returning the purchase information back to the client
            purchase.setCreditCardNumber("");
            purchase.setCustomer(null);
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(purchase)); // Expose safe, business-relevant object
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred.");
        }
    }
    

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Extract user from Basic Auth
            User authenticatedUser = extractUserFromBasicAuth(request, response);
            if (authenticatedUser == null) {
                return; // If user is null, it means authentication failed
            }

            Integer purchaseId = Integer.parseInt(request.getPathInfo().substring(1)); // Extract purchaseId
            BufferedReader reader = request.getReader();
            PurchaseDto updatedPurchaseDto = gson.fromJson(reader, PurchaseDto.class); // Directly bind request body to DTO
            Purchase updatedPurchase = purchaseConverter.convertPurchaseDtoToPurchase(updatedPurchaseDto);
            
            // Purchase Facade retrieves the purchase DTO from the database and converts it to the business object
            Purchase originalPurchase = purchaseFacade.getPurchaseById(purchaseId);

            // Authorization check
            if (!originalPurchase.getCustomer().equals(authenticatedUser) && !authenticatedUser.getRoleName().equals(ADMIN_ROLE_NAME)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Access is denied.");
                return;
            }

            // Apply ONLY allowed updates - update list of products
            originalPurchase.setProducts(updatedPurchase.getProducts());
            // Additional validation can be applied here if necessary

            // Save the updated purchase
            purchaseFacade.updatePurchase(originalPurchase);

            // Convert updated purchase to DTO and return JSON
            response.setContentType("application/json");
            originalPurchase.setCreditCardNumber("");
            originalPurchase.setCustomer(null);
            response.getWriter().write(gson.toJson(originalPurchase));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("An error occurred.");
        }
    }
    
    private User extractUserFromBasicAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Extract Basic Auth credentials
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authorization header missing or invalid.");
            return null;
        }

        String encodedCredentials = authHeader.substring("Basic ".length()).trim();
        String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials), "UTF-8");
        String[] credentials = decodedCredentials.split(":", 2);
        if (credentials.length != 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid authorization credentials.");
            return null;
        }

        String email = credentials[0];
        String password = credentials[1];
        User authenticatedUser = userFacade.getUserByEmail(email);

        // Verify user credentials
        if (authenticatedUser == null || !authenticatedUser.getPassword().equals(password)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid email or password.");
            return null;
        }

        return authenticatedUser;
    }
}