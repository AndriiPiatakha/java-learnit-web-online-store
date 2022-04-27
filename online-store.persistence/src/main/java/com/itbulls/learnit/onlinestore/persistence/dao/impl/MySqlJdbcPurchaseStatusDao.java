package com.itbulls.learnit.onlinestore.persistence.dao.impl;

import java.sql.SQLException;

import com.itbulls.learnit.onlinestore.persistence.dao.PurchaseStatusDao;
import com.itbulls.learnit.onlinestore.persistence.dto.PurchaseStatusDto;
import com.itbulls.learnit.onlinestore.persistence.utils.DBUtils;

public class MySqlJdbcPurchaseStatusDao implements PurchaseStatusDao {

	@Override
	public PurchaseStatusDto getPurchaseStatusById(Integer id) {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("SELECT * FROM purchase_status WHERE id = ?")) {

			ps.setInt(1, id);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					var purchaseStatusDto = new PurchaseStatusDto();
					purchaseStatusDto.setId(rs.getInt("id"));
					purchaseStatusDto.setStatusName(rs.getString("status_name"));
					return purchaseStatusDto;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
