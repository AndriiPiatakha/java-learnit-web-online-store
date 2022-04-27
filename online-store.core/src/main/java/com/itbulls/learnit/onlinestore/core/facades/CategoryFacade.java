package com.itbulls.learnit.onlinestore.core.facades;

import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.enteties.Category;

public interface CategoryFacade {

	List<Category> getCategories();

}
