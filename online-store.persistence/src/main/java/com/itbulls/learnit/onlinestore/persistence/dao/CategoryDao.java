package com.itbulls.learnit.onlinestore.persistence.dao;

import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.dto.CategoryDto;

public interface CategoryDao {

	CategoryDto getCategoryByCategoryId(int id);

	List<CategoryDto> getCategories();

}
