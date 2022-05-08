package com.itbulls.learnit.onlinestore.persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.dao.RoleDao;
import com.itbulls.learnit.onlinestore.persistence.dao.UserDao;
import com.itbulls.learnit.onlinestore.persistence.dto.UserDto;
import com.itbulls.learnit.onlinestore.persistence.utils.DBUtils;

public class MySqlJdbcUserDao implements UserDao {

	private RoleDao roleDao = new MySqlJdbcRoleDao();

	@Override
	public UserDto getUserById(int id) {
		try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement("SELECT * FROM user WHERE id = ?")) {

			ps.setInt(1, id);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					return parseUserDtoFromResultSet(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDto getUserByEmail(String email) {
		try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement("SELECT * FROM user WHERE email = ?")) {

			ps.setString(1, email);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					return parseUserDtoFromResultSet(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean saveUser(UserDto user) {
		try (var conn = DBUtils.getConnection();
				var ps = conn.prepareStatement("INSERT INTO user (first_name, last_name, email, fk_user_role, "
						+ "money, credit_card, password, partner_code, referrer_user_id) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			if (user.getRoleDto() != null && user.getRoleDto().getId() != null) {
				ps.setInt(4, user.getRoleDto().getId());
			} else if (user.getRoleDto() != null && !user.getRoleDto().getRoleName().isEmpty()) {
				ps.setInt(4, roleDao.getRoleByRoleName(user.getRoleDto().getRoleName()).getId());
			} else {
				ps.setNull(4, java.sql.Types.NULL);
			}
			ps.setBigDecimal(5, user.getMoney());
			ps.setString(6, user.getCreditCard());
			ps.setString(7, user.getPassword());
			ps.setString(8, user.getPartnerCode());
			
			if (user.getReferrerUser() != null) {
				ps.setInt(9, user.getReferrerUser().getId());
			} else {
				ps.setNull(9, java.sql.Types.NULL);
			}
			

			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<UserDto> getUsers() {
		try (var conn = DBUtils.getConnection();
				var ps = conn.prepareStatement("SELECT * FROM user");
				var rs = ps.executeQuery()) {
			List<UserDto> users = new ArrayList<>();

			while (rs.next()) {
				UserDto user = parseUserDtoFromResultSet(rs);
				users.add(user);
			}

			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDto getUserByPartnerCode(String partnerCode) {
		try (var conn = DBUtils.getConnection();
				var ps = conn.prepareStatement("SELECT * FROM user WHERE partner_code = ?")) {

			ps.setString(1, partnerCode);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					return parseUserDtoFromResultSet(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private UserDto parseUserDtoFromResultSet(ResultSet rs) {
		UserDto user = new UserDto();
		try {
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setEmail(rs.getString("email"));
			user.setRoleDto(roleDao.getRoleById(rs.getInt("fk_user_role")));
			user.setMoney(rs.getBigDecimal("money"));
			user.setCreditCard(rs.getString("credit_card"));
			user.setPassword(rs.getString("password"));
			user.setPartnerCode(rs.getString("partner_code"));
			user.setReferrerUser(this.getUserById(rs.getInt("referrer_user_id")));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(UserDto user) {
		try (var conn = DBUtils.getConnection();
				var ps = conn.prepareStatement("UPDATE user "
						+ "SET first_name = ?, "
						+ "last_name = ?, "
						+ "email = ?, "
						+ "fk_user_role = ?, "
						+ "money = ?, "
						+ "credit_card = ?, "
						+ "password = ?, "
						+ "partner_code = ?, "
						+ "referrer_user_id = ? "
						+ "WHERE id = ?")) {
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			if (user.getRoleDto() != null && user.getRoleDto().getId() != null) {
				ps.setInt(4, user.getRoleDto().getId());
			} else if (user.getRoleDto() != null && !user.getRoleDto().getRoleName().isEmpty()) {
				ps.setInt(4, roleDao.getRoleByRoleName(user.getRoleDto().getRoleName()).getId());
			} else {
				ps.setNull(4, java.sql.Types.NULL);
			}
			ps.setBigDecimal(5, user.getMoney());
			ps.setString(6, user.getCreditCard());
			ps.setString(7, user.getPassword());
			ps.setString(8, user.getPartnerCode());
			
			if (user.getReferrerUser() != null) {
				ps.setInt(9, user.getReferrerUser().getId());
			} else {
				ps.setNull(9, java.sql.Types.NULL);
			}
			
			ps.setInt(10, user.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UserDto> getReferralsByUserId(int id) {
		try (var conn = DBUtils.getConnection();
				var ps = conn.prepareStatement("SELECT * FROM user WHERE referrer_user_id = ?")) {
			
			ps.setInt(1, id);
			
			try (var rs = ps.executeQuery()) {
				List<UserDto> users = new ArrayList<>();

				while (rs.next()) {
					UserDto user = parseUserDtoFromResultSet(rs);
					users.add(user);
				}

				return users;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
