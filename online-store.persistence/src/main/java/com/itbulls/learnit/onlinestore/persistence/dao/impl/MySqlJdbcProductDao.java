package com.itbulls.learnit.onlinestore.persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.dao.CategoryDao;
import com.itbulls.learnit.onlinestore.persistence.dao.ProductDao;
import com.itbulls.learnit.onlinestore.persistence.dto.ProductDto;
import com.itbulls.learnit.onlinestore.persistence.utils.DBUtils;

public class MySqlJdbcProductDao implements ProductDao {
	
	private CategoryDao categoryDao = new MySqlJdbcCategoryDao();
	

	@Override
	public List<ProductDto> getProducts() {
		try (var conn = DBUtils.getConnection();
				var ps = conn.prepareStatement("SELECT * FROM product");
				var rs = ps.executeQuery()) {
			List<ProductDto> products = new ArrayList<>();
			
			while (rs.next()) {
				ProductDto product = populateProductDto(rs);
				products.add(product);
			}
			
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public ProductDto getProductById(int productId) {
		try (var conn = DBUtils.getConnection();
				var ps = conn.prepareStatement("SELECT * FROM product WHERE id = ?")) {
			
			ps.setInt(1, productId);
			try (var rs = ps.executeQuery()) {
				
				if (rs.next()) {
					ProductDto product = populateProductDto(rs);
					return product;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ProductDto populateProductDto(ResultSet rs) throws SQLException {
		ProductDto product = new ProductDto();
		product.setId(rs.getInt("id"));
		product.setProductName(rs.getString("product_name"));
		product.setPrice(rs.getBigDecimal("price"));
		product.setCategoryDto(categoryDao.getCategoryByCategoryId(rs.getInt("category_id")));
		product.setImgName(rs.getString("img_name"));
		product.setDescription(rs.getString("description"));
		return product;
	}

	@Override
	public List<ProductDto> getProductsLikeName(String searchQuery) {
		try (var conn = DBUtils.getConnection();
				var ps = conn
						.prepareStatement("SELECT * FROM learn_it_db.product "
								+ "WHERE UPPER(product_name) LIKE UPPER(CONCAT('%',?,'%'))");) {
			
			ps.setString(1, searchQuery);
			
			try (var rs = ps.executeQuery()) {
				List<ProductDto> products = new ArrayList<>();
				
				while (rs.next()) {
					ProductDto product = populateProductDto(rs);
					products.add(product);
				}
				
				return products;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductDto> getProductsByCategoryId(Integer id) {
		try (var conn = DBUtils.getConnection();
				var ps = conn
						.prepareStatement("SELECT * FROM learn_it_db.product "
								+ "WHERE category_id = ?");) {
			
			ps.setInt(1, id);
			
			try (var rs = ps.executeQuery()) {
				List<ProductDto> products = new ArrayList<>();
				
				while (rs.next()) {
					ProductDto product = populateProductDto(rs);
					products.add(product);
				}
				
				return products;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<ProductDto> getProductsByCategoryIdPaginationLimit(Integer categoryId, Integer page,
			Integer paginationLimit) {
		try (var conn = DBUtils.getConnection();
				var ps = conn
						.prepareStatement("SELECT * FROM learn_it_db.product "
								+ "WHERE category_id = ? LIMIT ?, ?");) {
			
			ps.setInt(1, categoryId);
			ps.setInt(2, ((paginationLimit * page) - paginationLimit)); // offset - number of pages multiplied by limit and minus items on the page to show products from current page
			ps.setInt(3, paginationLimit);
			
			try (var rs = ps.executeQuery()) {
				List<ProductDto> products = new ArrayList<>();
				
				while (rs.next()) {
					ProductDto product = populateProductDto(rs);
					products.add(product);
				}
				
				return products;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Integer getProductCountForCategory(Integer categoryId) {
		try (var conn = DBUtils.getConnection();
				var ps = conn
						.prepareStatement("SELECT  COUNT(id) as amount FROM product WHERE category_id=?;");) {
			
			ps.setInt(1, categoryId);
			
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("amount");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Integer getProductCountForSearch(String searchQuery) {
		try (var conn = DBUtils.getConnection();
				var ps = conn
						.prepareStatement("SELECT COUNT(id) as amount FROM product WHERE UPPER(product_name) LIKE UPPER(CONCAT('%',?,'%'));");) {
			
			ps.setString(1, searchQuery);
			
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("amount");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<ProductDto> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page, Integer paginationLimit) {
		try (var conn = DBUtils.getConnection();
				var ps = conn
						.prepareStatement("SELECT * FROM learn_it_db.product "
								+ "WHERE UPPER(product_name) LIKE UPPER(CONCAT('%',?,'%')) LIMIT ?, ?");) {
			
			ps.setString(1, searchQuery);
			ps.setInt(2, ((paginationLimit * page) - paginationLimit)); // offset - number of pages multiplied by limit and minus items on the page to show products from current page
			ps.setInt(3, paginationLimit);
			
			try (var rs = ps.executeQuery()) {
				List<ProductDto> products = new ArrayList<>();
				
				while (rs.next()) {
					ProductDto product = populateProductDto(rs);
					products.add(product);
				}
				
				return products;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
