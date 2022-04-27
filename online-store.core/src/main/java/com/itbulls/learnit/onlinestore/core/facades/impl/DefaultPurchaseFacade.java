package com.itbulls.learnit.onlinestore.core.facades.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.itbulls.learnit.onlinestore.core.CoreConfigurations;
import com.itbulls.learnit.onlinestore.core.facades.PurchaseFacade;
import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.persistence.dao.PurchaseDao;
import com.itbulls.learnit.onlinestore.persistence.dao.impl.MySqlJdbcPurchaseDao;
import com.itbulls.learnit.onlinestore.persistence.dto.PurchaseDto;
import com.itbulls.learnit.onlinestore.persistence.dto.converters.PurchaseDtoToPurchaseConverter;
import com.itbulls.learnit.onlinestore.persistence.enteties.Product;
import com.itbulls.learnit.onlinestore.persistence.enteties.Purchase;
import com.itbulls.learnit.onlinestore.persistence.enteties.PurchaseStatus;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;
import com.itbulls.learnit.onlinestore.persistence.enteties.impl.DefaultPurchase;
import com.itbulls.learnit.onlinestore.persistence.enteties.impl.DefaultPurchaseStatus;

public class DefaultPurchaseFacade implements PurchaseFacade {
	
	private static DefaultPurchaseFacade instance;
	private PurchaseDao purchaseDao = new MySqlJdbcPurchaseDao();
	private PurchaseDtoToPurchaseConverter purchaseConverter = new PurchaseDtoToPurchaseConverter();
	private UserFacade userFacade = DefaultUserFacade.getInstance();
	
	public static synchronized DefaultPurchaseFacade getInstance() {
		if (instance == null) {
			instance = new DefaultPurchaseFacade();
		}
		
		return instance;
	}

	@Override
	public void createPurchase(User user, Product product) {
		Purchase purchase = new DefaultPurchase();
		purchase.setCustomer(user);
		purchase.setProducts(new ArrayList<>(Arrays.asList(product)));
		
		var purchaseStatus = new DefaultPurchaseStatus();
		purchaseStatus.setId(1); // the initial, the first purchase status
		purchase.setPurchaseStatus(purchaseStatus);
		
		purchaseDao.savePurchase(purchaseConverter.convertPurchaseToPurchaseDto(purchase));
	}

	@Override
	public List<Purchase> getNotCompletedPurchases() {
		return purchaseConverter.convertPurchaseDtosToPurchases(purchaseDao.getNotCompletedPurchases(LAST_STATUS_OF_ORDER_FULFILMENT_ID));
	}

	@Override
	public void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId) {
		Purchase purchase = purchaseConverter.convertPurchaseDtoToPurchase(purchaseDao.getPurchaseById(purchaseId));
		PurchaseStatus purchaseStatus = purchase.getPurchaseStatus();
		int newPurchaseStatusId = purchaseStatus.getId() + 1;
		purchaseStatus.setId(newPurchaseStatusId);
		purchase.setPurchaseStatus(purchaseStatus);
		
		purchaseDao.updatePurchase(purchaseConverter.convertPurchaseToPurchaseDto(purchase));
		
		if (LAST_STATUS_OF_ORDER_FULFILMENT_ID.equals(newPurchaseStatusId) 
				&& purchase.getCustomer().getReferrerUser() != null) {
			User referrerUser = purchase.getCustomer().getReferrerUser();
			double shareFromPurchase = purchase.getTotalPurchaseCost() * CoreConfigurations.REFFERER_REWARD_RATE;
			referrerUser.setMoney(referrerUser.getMoney() + shareFromPurchase);
			userFacade.updateUser(referrerUser);
		}
	}

}
