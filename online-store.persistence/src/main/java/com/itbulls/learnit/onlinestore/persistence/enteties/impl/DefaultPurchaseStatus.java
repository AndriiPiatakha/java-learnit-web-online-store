package com.itbulls.learnit.onlinestore.persistence.enteties.impl;

import com.itbulls.learnit.onlinestore.persistence.enteties.PurchaseStatus;

public class DefaultPurchaseStatus implements PurchaseStatus {
	
	private Integer id;
	private String statusName;

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public String getStatusName() {
		return this.statusName;
	}

}
