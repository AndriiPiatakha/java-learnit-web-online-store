package com.itbulls.learnit.onlinestore.core.facades;

import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.enteties.Product;
import com.itbulls.learnit.onlinestore.persistence.enteties.Purchase;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;

public interface PurchaseFacade {

	Integer LAST_STATUS_OF_ORDER_FULFILMENT_ID = 6;
	
	void createPurchase(User attribute, Product productId);

	List<Purchase> getNotCompletedPurchases();

	void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId);

}
