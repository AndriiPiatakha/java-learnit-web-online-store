package com.itbulls.learnit.onlinestore.persistence.dto;

public class RoleDto {
	
	public static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";
	public static final String MANAGER_ROLE_NAME = "ROLE_MANAGER";
	public static final String CUSTOMER_ROLE_NAME = "ROLE_CUSTOMER";

	private Integer id;
	private String roleName;
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
