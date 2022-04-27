package com.itbulls.learnit.onlinestore.persistence.enteties.impl;

import com.itbulls.learnit.onlinestore.persistence.enteties.Category;

public class DefaultCategory implements Category {
	
	private Integer id;
	private String categoryName;
	private String imgName;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
}
