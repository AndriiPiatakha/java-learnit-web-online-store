package com.itbulls.learnit.onlinestore.persistence.dao;

import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.dto.UserDto;

public interface UserDao {
	
	boolean saveUser(UserDto user);
	
	List<UserDto> getUsers();

	UserDto getUserByEmail(String userEmail);

	UserDto getUserById(int id);

	UserDto getUserByPartnerCode(String partnerCode);

	void updateUser(UserDto convertUserToUserDto);

	List<UserDto> getReferralsByUserId(int id);

}
