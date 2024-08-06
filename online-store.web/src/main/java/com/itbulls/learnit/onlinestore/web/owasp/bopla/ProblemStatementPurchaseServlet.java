package com.itbulls.learnit.onlinestore.web.owasp.bopla;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.itbulls.learnit.onlinestore.persistence.dao.PurchaseDao;
import com.itbulls.learnit.onlinestore.persistence.dao.impl.MySqlJdbcPurchaseDao;
import com.itbulls.learnit.onlinestore.persistence.dto.ProductDto;
import com.itbulls.learnit.onlinestore.persistence.dto.PurchaseDto;
import com.itbulls.learnit.onlinestore.persistence.dto.PurchaseStatusDto;
import com.itbulls.learnit.onlinestore.persistence.dto.UserDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bopla/problem/purchases/*")
public class ProblemStatementPurchaseServlet extends HttpServlet {

    private PurchaseDao purchaseDao = new MySqlJdbcPurchaseDao(); // Assume this DAO handles data operations
    private Gson gson = new Gson(); // For JSON conversion

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer purchaseId = Integer.parseInt(request.getPathInfo().substring(1)); // Extract purchaseId
        PurchaseDto purchaseDto = purchaseDao.getPurchaseById(purchaseId); // Retrieve DTO directly from DAO
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(purchaseDto)); // Exposes all fields, including sensitive data
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Integer purchaseId = Integer.parseInt(request.getPathInfo().substring(1)); // Extract purchaseId
        BufferedReader reader = request.getReader();
        PurchaseDto updatedPurchaseDto = gson.fromJson(reader, PurchaseDto.class); // Directly bind request body to DTO

        PurchaseDto existingPurchaseDto = purchaseDao.getPurchaseById(purchaseId); // Retrieve the existing purchase from the database
        copyProperties(updatedPurchaseDto, existingPurchaseDto); // Vulnerable to mass assignment
        purchaseDao.updatePurchase(existingPurchaseDto); // Save the updated purchase

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(existingPurchaseDto)); // Expose updated purchase directly
    }
    
    
    // === Helper Methods, decided to keep them in one class for the sake of the demo
    public void copyProperties(PurchaseDto source, PurchaseDto target) {
        // Copy basic fields
        if (source.getId() != null) {
            target.setId(source.getId());
        }
        
        // Copy nested UserDto
        if (source.getUserDto() != null) {
            target.setUserDto(copyUserDto(source.getUserDto(), target.getUserDto()));
        }
        
        // Copy nested ProductDtos
        if (source.getProductDtos() != null) {
            target.setProductDtos(copyProductDtos(source.getProductDtos(), target.getProductDtos()));
        }
        
        // Copy nested PurchaseStatusDto
        if (source.getPurchaseStatusDto() != null) {
            target.setPurchaseStatusDto(copyPurchaseStatusDto(source.getPurchaseStatusDto(), target.getPurchaseStatusDto()));
        }
    }

    // Helper methods for copying nested objects
    private UserDto copyUserDto(UserDto source, UserDto target) {
        if (target == null) {
            target = new UserDto();
        }
        target.setId(source.getId()); // Ensure you handle ID carefully
        // Add other fields if necessary, avoid sensitive info exposure
        return target;
    }

    private List<ProductDto> copyProductDtos(List<ProductDto> source, List<ProductDto> target) {
        if (target == null) {
            target = new ArrayList<>();
        } else {
            target.clear(); // Clear the target list before copying
        }
        for (ProductDto sourceProduct : source) {
            ProductDto targetProduct = new ProductDto();
            targetProduct.setId(sourceProduct.getId()); // Ensure you handle ID carefully
            // Add other fields if necessary, avoid sensitive info exposure
            target.add(targetProduct);
        }
        return target;
    }


    private PurchaseStatusDto copyPurchaseStatusDto(PurchaseStatusDto source, PurchaseStatusDto target) {
        if (target == null) {
            target = new PurchaseStatusDto();
        }
        target.setId(source.getId()); // Ensure you handle ID carefully
        // Add other fields if necessary, avoid sensitive info exposure
        return target;
    }
}