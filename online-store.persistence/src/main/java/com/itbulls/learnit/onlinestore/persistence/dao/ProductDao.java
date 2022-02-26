package com.itbulls.learnit.onlinestore.persistence.dao;

import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.dto.ProductDto;


public interface ProductDao {
	
	List<ProductDto> getProducts();

	ProductDto getProductById(int productId);
	
}
