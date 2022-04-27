package com.itbulls.learnit.onlinestore.persistence.dto.converters;

import java.util.ArrayList;
import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.dto.CategoryDto;
import com.itbulls.learnit.onlinestore.persistence.enteties.Category;
import com.itbulls.learnit.onlinestore.persistence.enteties.impl.DefaultCategory;

public class CategoryDtoToCategoryConverter {

	public CategoryDto convertCategoryNameToCategoryDtoWithOnlyName(String categoryName) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryName(categoryName);
		return categoryDto;
	}

	public List<Category> convertCategoryDtosToCategories(List<CategoryDto> categoryDtos) {
		List<Category> categories = new ArrayList<>();
		
		for (CategoryDto categoryDto : categoryDtos) {
			categories.add(convertCategoryDtoToCategory(categoryDto));
		}
		return categories;
	}

	private Category convertCategoryDtoToCategory(CategoryDto categoryDto) {
		DefaultCategory newCategory = new DefaultCategory();
		newCategory.setId(categoryDto.getId());
		newCategory.setCategoryName(categoryDto.getCategoryName());
		newCategory.setImgName(categoryDto.getImgName());
		return newCategory;
	}

}
