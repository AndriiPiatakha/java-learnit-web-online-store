package com.itbulls.learnit.onlinestore.core.facades;

import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.enteties.Product;

public interface ProductFacade {
	
	List<Product> getProductsLikeName(String searchQuery);

	List<Product> getProductsByCategoryId(Integer id);

	List<Product> getProductsByCategoryIdForPageWithLimit(Integer categoryId, Integer page, Integer paginationLimit);

	Integer getNumberOfPagesForCategory(Integer categoryId, Integer paginationLimit);

	Integer getNumberOfPagesForSearch(String searchQuery, Integer paginationLimit);

	List<Product> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page,
			Integer paginationLimit);

	Product getProductById(Integer parameter);

}
