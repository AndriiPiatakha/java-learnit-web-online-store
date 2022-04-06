package com.itbulls.learnit.onlinestore.persistence.dao;

import com.itbulls.learnit.onlinestore.persistence.dto.RoleDto;

public interface RoleDao {

	RoleDto getRoleById(int id);

	RoleDto getRoleByRoleName(String roleName);

}
