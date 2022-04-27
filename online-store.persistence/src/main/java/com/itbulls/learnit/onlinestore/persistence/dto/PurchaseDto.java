package com.itbulls.learnit.onlinestore.persistence.dto;

import java.util.List;

public class PurchaseDto {
	
	private Integer id;
	private UserDto userDto;
	private List<ProductDto> productDtos;
	private PurchaseStatusDto purchaseStatusDto;
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public List<ProductDto> getProductDtos() {
		return productDtos;
	}
	public void setProductDtos(List<ProductDto> productDtos) {
		this.productDtos = productDtos;
	}
	public PurchaseStatusDto getPurchaseStatusDto() {
		return this.purchaseStatusDto;
	}
	public void setPurchaseStatusDto(PurchaseStatusDto purchaseStatusDto) {
		this.purchaseStatusDto = purchaseStatusDto;
	}

	

}
