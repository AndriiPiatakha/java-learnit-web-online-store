package com.itbulls.learnit.onlinestore.persistence.enteties;

public interface User {
	
	String getFirstName();
	void setFirstName(String firstName);
	
	String getLastName();
	void setLastName(String lastName);
	
	String getPassword();
	
	String getEmail();
	
	int getId();
	void setId(int id);
	
	void setPassword(String newPassword);
	void setEmail(String newEmail);
	
	String getRoleName();
	void setRoleName(String roleName);
	
	double getMoney();
	void setMoney(double money);
	
	String getCreditCard();
	void setCreditCard(String creditCard);
	
	String getPartnerCode();
	void setPartnerCode(String partnerCode);
	
	void setReferrerUser(User convertUserDtoToUser);
	User getReferrerUser();
	
	
}
