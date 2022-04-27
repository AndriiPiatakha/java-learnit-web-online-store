package com.itbulls.learnit.onlinestore.persistence.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.dao.ProductDao;
import com.itbulls.learnit.onlinestore.persistence.dao.PurchaseDao;
import com.itbulls.learnit.onlinestore.persistence.dao.PurchaseStatusDao;
import com.itbulls.learnit.onlinestore.persistence.dao.UserDao;
import com.itbulls.learnit.onlinestore.persistence.dto.ProductDto;
import com.itbulls.learnit.onlinestore.persistence.dto.PurchaseDto;
import com.itbulls.learnit.onlinestore.persistence.dto.PurchaseStatusDto;
import com.itbulls.learnit.onlinestore.persistence.enteties.Purchase;
import com.itbulls.learnit.onlinestore.persistence.utils.DBUtils;

public class MySqlJdbcPurchaseDao implements PurchaseDao {
	
	
	private UserDao userDao = new MySqlJdbcUserDao();
	private ProductDao productDao = new MySqlJdbcProductDao();
	private PurchaseStatusDao purchaseStatusDao = new MySqlJdbcPurchaseStatusDao();
	

	@Override
	public void savePurchase(PurchaseDto purchase) {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("INSERT INTO purchase (fk_purchase_user, fk_purchase_purchase_status) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
				var psPurchaseProduct = conn.prepareStatement("INSERT INTO purchase_product (purchase_id, product_id) VALUES (?, ?)")) {
			System.out.println("=============== " + purchase.getPurchaseStatusDto().getId());
			ps.setInt(1, purchase.getUserDto().getId());
			ps.setInt(2, purchase.getPurchaseStatusDto().getId());
			ps.executeUpdate();
			
			try (var generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	
	            	for (ProductDto product : purchase.getProductDtos()) {
	            		psPurchaseProduct.setLong(1, generatedKeys.getLong(1));
	            		psPurchaseProduct.setInt(2, product.getId());
	            		psPurchaseProduct.addBatch();
	            	}
	            	
	            	psPurchaseProduct.executeBatch();
	            }
	            else {
	                throw new SQLException("Creating purchase failed, no ID obtained.");
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<PurchaseDto> getPurchasesByUserId(int userId) {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("SELECT * FROM purchase WHERE fk_purchase_user = ?")) {
			ps.setInt(1, userId);
			
			List<PurchaseDto> purchases = new ArrayList<>();
			
			try (var rs = ps.executeQuery()) {
				while (rs.next()) {
					PurchaseDto purchase = populatePurchaseDto(conn, rs);
					
					purchases.add(purchase);
				}
			}
			
			return purchases;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	private PurchaseDto populatePurchaseDto(Connection conn, ResultSet rs) throws SQLException {
		PurchaseDto purchase = new PurchaseDto();
		purchase.setId(rs.getInt("id"));
		purchase.setUserDto(userDao.getUserById(rs.getInt("fk_purchase_user")));
		
		List<ProductDto> products = new ArrayList<>();
		try (var psProducts = conn.prepareStatement("SELECT * FROM purchase_product WHERE purchase_id = " + purchase.getId());
				var rsProducts = psProducts.executeQuery()) {
			
			while (rsProducts.next()) {
				products.add(productDao.getProductById(rsProducts.getInt("product_id")));
			}
		}
		purchase.setProductDtos(products);
		purchase.setPurchaseStatusDto(purchaseStatusDao.getPurchaseStatusById(rs.getInt("fk_purchase_purchase_status")));
		return purchase;
	}

	@Override
	public List<PurchaseDto> getPurchases() {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("SELECT * FROM purchase")) {
			
			List<PurchaseDto> purchases = new ArrayList<>();
			
			try (var rs = ps.executeQuery()) {
				while (rs.next()) {
					PurchaseDto purchase = populatePurchaseDto(conn, rs);
					purchases.add(purchase);
				}
			}
			
			return purchases;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PurchaseDto> getNotCompletedPurchases(Integer lastFulfilmentStageId) {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("SELECT * FROM purchase WHERE fk_purchase_purchase_status != " + lastFulfilmentStageId)) {
			
			List<PurchaseDto> purchases = new ArrayList<>();
			
			try (var rs = ps.executeQuery()) {
				while (rs.next()) {
					PurchaseDto purchase = populatePurchaseDto(conn, rs);
					purchases.add(purchase);
				}
			}
			
			return purchases;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PurchaseDto getPurchaseById(Integer purchaseId) {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("SELECT * FROM purchase WHERE id = ?;")) {
			
			
			ps.setInt(1, purchaseId);
			
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					return populatePurchaseDto(conn, rs);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public void updatePurchase(PurchaseDto purchase) {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("UPDATE `learn_it_db`.`purchase` SET `fk_purchase_purchase_status` = ? WHERE (`id` = ?);")) {
			ps.setInt(1, purchase.getPurchaseStatusDto().getId());
			ps.setInt(2, purchase.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
