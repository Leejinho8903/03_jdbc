package com.greedy.section01.model.dao;

import static com.greedy.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.greedy.section01.model.dto.CategoryDTO;
import com.greedy.section01.model.dto.MenuDTO;
import com.greedy.section01.model.dto.OrderDTO;
import com.greedy.section01.model.dto.OrderMenuDTO;

public class OrderDAO {

	private Properties prop;
	
	public OrderDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("mapper/order-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 1. 카테고리 전체 조회 */
	public List<CategoryDTO> selectAllCategory(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CategoryDTO> categoryList = null;
		String query = prop.getProperty("selectAllCategory");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
		
			categoryList = new ArrayList<>();
			
			while(rset.next()) {
				CategoryDTO category = new CategoryDTO();
				category.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				category.setCategoryName(rset.getString("CATEGORY_NAME"));
				category.setRefCategoryCode(rset.getInt("REF_CATEGORY_CODE"));
				
				categoryList.add(category);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return categoryList;
	}

	public List<MenuDTO> selectMenuByCategory(Connection conn, int categoryCode) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<MenuDTO> menuList = null;
		String query = prop.getProperty("selectMenuByCategory");
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryCode);
			
			rset = pstmt.executeQuery();
			
			menuList = new ArrayList<>();
			
			while(rset.next()) {
				
				MenuDTO menu = new MenuDTO();
				menu.setMenuCode(rset.getInt("MENU_CODE"));
				menu.setMenuName(rset.getString("MENU_NAME"));
				menu.setMenuPrice(rset.getInt("MENU_PRICE"));
				menu.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				menu.setOrderableStatus(rset.getString("ORDERABLE_STATUS"));
				
				menuList.add(menu);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return menuList;
		
	}

	public int insertOrder(Connection conn, OrderDTO order) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertOrder");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, order.getOrderDate());
			pstmt.setString(2, order.getOrderTime());
			pstmt.setInt(3, order.getTotalOrderPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertOrderMenu(Connection conn, OrderMenuDTO orderMenu) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertOrderMenu");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderMenu.getMenuCode());
			pstmt.setInt(2, orderMenu.getOrderAmount());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
