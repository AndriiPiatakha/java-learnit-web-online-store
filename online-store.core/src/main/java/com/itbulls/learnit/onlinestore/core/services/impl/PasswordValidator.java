package com.itbulls.learnit.onlinestore.core.services.impl;

import com.itbulls.learnit.onlinestore.core.services.Validator;

public class PasswordValidator implements Validator {

	
	private static PasswordValidator instance;
	
	/**
	 * @return true in case password has 8 or more characters and at least one special character
	 */
	@Override
	public boolean isValid(String password) {
		if (password.length() < 8) {
			return false;
		}

		String specialCharactersString = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
		for (int i = 0; i < password.length(); i++) {
			char ch = password.charAt(i);
			if (specialCharactersString.contains(Character.toString(ch))) {
				return true;
			}
		}

		return false;
	}

	public static synchronized PasswordValidator getInstance() {
		if (instance == null) {
			instance = new PasswordValidator();
		}
		return instance;
	}

}
