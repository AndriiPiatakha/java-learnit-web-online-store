package com.itbulls.learnit.onlinestore.core.facades.impl;

import static com.itbulls.learnit.onlinestore.persistence.dto.RoleDto.CUSTOMER_ROLE_NAME;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.services.AffiliateMarketingService;
import com.itbulls.learnit.onlinestore.core.services.impl.DefaultAffiliateMarketingService;
import com.itbulls.learnit.onlinestore.persistence.dao.UserDao;
import com.itbulls.learnit.onlinestore.persistence.dao.impl.MySqlJdbcUserDao;
import com.itbulls.learnit.onlinestore.persistence.dto.converters.UserDtoToUserConverter;
import com.itbulls.learnit.onlinestore.persistence.enteties.User;

public class DefaultUserFacade implements UserFacade {
	
	private static DefaultUserFacade instance;
	private UserDao userDao = new MySqlJdbcUserDao();
	private UserDtoToUserConverter userConverter = new UserDtoToUserConverter();
	private AffiliateMarketingService marketingService = new DefaultAffiliateMarketingService();
	
	public static synchronized DefaultUserFacade getInstance() {
		if (instance == null) {
			instance = new DefaultUserFacade();
		}
		
		return instance;
	}

	@Override
	public void registerUser(User user, String referrerCode) {
		user.setRoleName(CUSTOMER_ROLE_NAME);
		user.setPartnerCode(marketingService.generateUniquePartnerCode());
		user.setReferrerUser(userConverter.convertUserDtoToUser(userDao.getUserByPartnerCode(referrerCode)));
		userDao.saveUser(userConverter.convertUserToUserDto(user));
	}


	@Override
	public User getUserByEmail(String email) {
		return userConverter.convertUserDtoToUser(userDao.getUserByEmail(email));
	}
}
