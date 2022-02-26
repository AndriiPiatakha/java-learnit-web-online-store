package com.itbulls.learnit.onlinestore.core.services;

import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.enteties.Product;

public interface ProductManagementService {

	List<Product> getProducts();

	Product getProductById(int id);
	
}
