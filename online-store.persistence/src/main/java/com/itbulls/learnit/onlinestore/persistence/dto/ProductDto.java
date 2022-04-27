package com.itbulls.learnit.onlinestore.persistence.dto;

import java.math.BigDecimal;

public class ProductDto {
	
	private Integer id;
	private String productName;
	private BigDecimal price;
	private CategoryDto categoryDto;
	private String imgName;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public CategoryDto getCategoryDto() {
		return categoryDto;
	}
	public void setCategoryDto(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgName() {
		return this.imgName;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
