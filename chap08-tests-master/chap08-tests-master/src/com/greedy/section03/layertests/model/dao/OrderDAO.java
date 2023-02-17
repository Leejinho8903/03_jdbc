package com.greedy.section03.layertests.model.dao;

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

import com.greedy.section03.layertests.model.dto.CategoryDTO;
import com.greedy.section03.layertests.model.dto.MenuDTO;
import com.greedy.section03.layertests.model.dto.OrderDTO;
import com.greedy.section03.layertests.model.dto.OrderMenuDTO;

public class OrderDAO {
	
	private Properties prop = new Properties();
	
	public OrderDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/order-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 모든 카테고리 조회용 메소드 */
	public List<CategoryDTO> selectAllCategory(Connection con) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<CategoryDTO> categoryList = null;
		
		String query = prop.getProperty("selectAllCategory");
		
		try {
			pstmt = con.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			categoryList = new ArrayList<>();
			
			while(rset.next()) {
				CategoryDTO category = new CategoryDTO();
				category.setCode(rset.getInt("CATEGORY_CODE"));
				category.setName(rset.getString("CATEGORY_NAME"));
				
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
	
	/* 카테고리별 메뉴 조회용 메소드 */
	public List<MenuDTO> selectMenuByCategory(Connection con, int categoryCode){
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<MenuDTO> menuList = null;
		
		String query = prop.getProperty("selectMenuByCategory");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, categoryCode);
			
			rset = pstmt.executeQuery();
			
			menuList = new ArrayList<>();
			
			while(rset.next()) {
				MenuDTO menu = new MenuDTO();
				menu.setCode(rset.getInt("MENU_CODE"));
				menu.setName(rset.getString("MENU_NAME"));
				menu.setPrice(rset.getInt("MENU_PRICE"));
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

	/* 주문 정보 입력용 메소드 */
	public int insertOrder(Connection con, OrderDTO order) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertOrder");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, order.getDate());
			pstmt.setString(2, order.getTime());
			pstmt.setInt(3, order.getTotalOrderPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 주문 메뉴별 입력용 메소드 */
	public int insertOrderMenu(Connection con, OrderMenuDTO orderMenu) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertOrderMenu");
		
		try {
			pstmt = con.prepareStatement(query);
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
