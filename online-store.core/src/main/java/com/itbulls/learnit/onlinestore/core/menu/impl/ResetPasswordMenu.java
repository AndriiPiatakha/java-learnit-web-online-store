package com.itbulls.learnit.onlinestore.core.menu.impl;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import com.itbulls.learnit.onlinestore.core.menu.Menu;
import com.itbulls.learnit.onlinestore.core.services.UserManagementService;
import com.itbulls.learnit.onlinestore.core.services.impl.MySqlUserManagementService;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;

public class ResetPasswordMenu implements Menu {
	
	private UserManagementService userManagementService;
	private ResourceBundle rb;

	{
		userManagementService = new MySqlUserManagementService();
		rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc = new Scanner(System.in);
		String userInput = sc.next();
		System.out.println(rb.getString("pass.sent.to.email"));
		CompletableFuture.runAsync(() -> {
			User user = userManagementService.getUserByEmail(userInput);
			userManagementService.resetPasswordForUser(user);
		});
		new MainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(rb.getString("reset.pass.header"));
		System.out.print(rb.getString("enter.your.email.msg"));
	}

}
