package com.itbulls.learnit.onlinestore.core.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;


import com.itbulls.learnit.onlinestore.core.configs.ApplicationContext;
import com.itbulls.learnit.onlinestore.core.menu.Menu;
import com.itbulls.learnit.onlinestore.core.services.PurchaseManagementService;
import com.itbulls.learnit.onlinestore.core.services.impl.MySqlPurchaseManagementService;
import com.itbulls.learnit.onlinestore.persistence.enteties.Purchase;
import com.itbulls.learnit.onlinestore.persistence.enteties.impl.DefaultPurchase;

public class CheckoutMenu implements Menu {

	private ApplicationContext context;
	private PurchaseManagementService purchaseManagementService;
	private ResourceBundle rb;
	
	{
		context = ApplicationContext.getInstance();
		purchaseManagementService = new MySqlPurchaseManagementService();
		rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
	}
	
	@Override
	public void start() {
		while (true) {
			printMenuHeader();
			Scanner sc = new Scanner(System.in);
			String userInput = sc.next();

			if (!createOrder(userInput)) {
				continue;
			}
			context.getSessionCart().clear();
			break;
		}
		
		System.out.println(rb.getString("thank.you.msg"));
		new MainMenu().start();

	}
	
	private boolean createOrder(String creditCardNumber) {
		Purchase order = new DefaultPurchase();
		if (!order.isCreditCardNumberValid(creditCardNumber)) {
			return false;
		}
		
		order.setCreditCardNumber(creditCardNumber);
		order.setProducts(context.getSessionCart().getProducts());
		order.setCustomer(context.getLoggedInUser());
		purchaseManagementService.addPurchase(order);
		return true;
	}

	@Override
	public void printMenuHeader() {
		System.out.println(rb.getString("checkout.menu.header"));
		System.out.print(rb.getString("enter.credit.card.number.cta"));
	}

}
