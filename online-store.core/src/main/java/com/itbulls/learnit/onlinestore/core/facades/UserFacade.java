package com.itbulls.learnit.onlinestore.core.facades;

import com.itbulls.learnit.onlinestore.persistence.enteties.User;

public interface UserFacade {

	void registerUser(User user, String partnerCode);

	User getUserByEmail(String email);
}
