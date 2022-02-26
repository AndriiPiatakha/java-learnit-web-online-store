package com.itbulls.learnit.onlinestore.core.menu.impl;

import java.util.List;

import com.itbulls.learnit.onlinestore.core.configs.ApplicationContext;
import com.itbulls.learnit.onlinestore.core.menu.Menu;
import com.itbulls.learnit.onlinestore.core.services.PurchaseManagementService;
import com.itbulls.learnit.onlinestore.core.services.impl.MySqlPurchaseManagementService;
import com.itbulls.learnit.onlinestore.persistence.enteties.Purchase;


public class MyOrdersMenu implements Menu {

	private ApplicationContext context;
	private PurchaseManagementService purchaseManagementService;

	{
		context = ApplicationContext.getInstance();
		purchaseManagementService = new MySqlPurchaseManagementService();
	}

	@Override
	public void start() {
		printMenuHeader();
		if (context.getLoggedInUser() == null) {
			System.out.println(
					"Please, log in or create new account to see list of your orders");
			new MainMenu().start();
			return;
		} else {
			printUserOrdersToConsole();
		}
		new MainMenu().start();
	}

	private void printUserOrdersToConsole() {
		List<Purchase> loggedInUserOrders = purchaseManagementService
				.getPurchasesByUserId(context.getLoggedInUser().getId());
		if (loggedInUserOrders == null || loggedInUserOrders.size() == 0) {
			System.out.println(
					"Unfortunately, you don't have any orders yet. "
					+ "Navigate back to main menu to place a new order");
		} else {
			for (Purchase order : loggedInUserOrders) {
				System.out.println(order);
			}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MY ORDERS *****");		
	}

}
