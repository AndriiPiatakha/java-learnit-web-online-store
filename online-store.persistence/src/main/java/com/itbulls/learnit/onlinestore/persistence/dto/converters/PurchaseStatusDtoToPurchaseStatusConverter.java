package com.itbulls.learnit.onlinestore.persistence.dto.converters;

import com.itbulls.learnit.onlinestore.persistence.dto.PurchaseStatusDto;
import com.itbulls.learnit.onlinestore.persistence.enteties.PurchaseStatus;
import com.itbulls.learnit.onlinestore.persistence.enteties.impl.DefaultPurchaseStatus;

public class PurchaseStatusDtoToPurchaseStatusConverter {

	public PurchaseStatus convertPurchaseStatusDtoToPurchaseStatus(PurchaseStatusDto purchaseStatusDto) {
		PurchaseStatus purchaseStatus = new DefaultPurchaseStatus();
		purchaseStatus.setId(purchaseStatusDto.getId());
		purchaseStatus.setStatusName(purchaseStatusDto.getStatusName());
		return purchaseStatus;
	}

	public PurchaseStatusDto convertPurchaseStatusToPurchaseStatusDto(PurchaseStatus purchaseStatus) {
		PurchaseStatusDto dto = new PurchaseStatusDto();
		dto.setId(purchaseStatus.getId());
		dto.setStatusName(purchaseStatus.getStatusName());
		return dto;
	}

}
