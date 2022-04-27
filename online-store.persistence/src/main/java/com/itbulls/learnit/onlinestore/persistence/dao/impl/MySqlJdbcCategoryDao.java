package com.itbulls.learnit.onlinestore.persistence.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itbulls.learnit.onlinestore.persistence.dao.CategoryDao;
import com.itbulls.learnit.onlinestore.persistence.dto.CategoryDto;
import com.itbulls.learnit.onlinestore.persistence.utils.DBUtils;


public class MySqlJdbcCategoryDao implements CategoryDao {

	@Override
	public CategoryDto getCategoryByCategoryId(int id) {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("SELECT * FROM category WHERE id = ?")) {
			
			ps.setInt(1, id);
			
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					CategoryDto category = new CategoryDto();
					category.setId(rs.getInt("id"));
					category.setCategoryName(rs.getString("category_name"));
					category.setImgName(rs.getString("img_name"));
					return category;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CategoryDto> getCategories() {
		try (var conn = DBUtils.getConnection(); 
				var ps = conn.prepareStatement("SELECT * FROM category");
				var rs = ps.executeQuery()) {
			
			List<CategoryDto> categories = new ArrayList<>();
			
			while (rs.next()) {
				CategoryDto category = new CategoryDto();
				category.setId(rs.getInt("id"));
				category.setCategoryName(rs.getString("category_name"));
				category.setImgName(rs.getString("img_name"));
				categories.add(category);
			}
			
			return categories;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
