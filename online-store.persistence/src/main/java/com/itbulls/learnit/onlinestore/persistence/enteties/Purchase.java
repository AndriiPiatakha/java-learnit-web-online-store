package com.itbulls.learnit.onlinestore.persistence.enteties;

import java.io.Serializable;
import java.util.List;

public interface Purchase extends Serializable {

	boolean isCreditCardNumberValid(String userInput);

	void setCreditCardNumber(String userInput);

	void setProducts(List<Product> products);
	
	List<Product> getProducts();

	void setCustomer(User customer);
	
	User getCustomer();

	void setPurchaseStatus(PurchaseStatus purchaseStatus);

	PurchaseStatus getPurchaseStatus();

	void setId(Integer id);

	Integer getId();

	double getTotalPurchaseCost();

}
