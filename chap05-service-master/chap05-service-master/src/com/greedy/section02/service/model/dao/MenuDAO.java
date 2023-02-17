package com.greedy.section02.service.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static com.greedy.common.JDBCTemplate.close;
import com.greedy.section02.service.model.dto.CategoryDTO;
import com.greedy.section02.service.model.dto.MenuDTO;

public class MenuDAO {
	
	private Properties prop;
	
	public MenuDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 1. 신규 카테고리 등록 */
	public int insertNewCategory(Connection conn, CategoryDTO newCategory) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertCategory");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newCategory.getCategoryName());
			pstmt.setObject(2, newCategory.getRefCategoryCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/* 2. 마지막 카테고리 코드 조회 */
	public int selectLastCategoryCode(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int newCategoryCode = 0;
		String query = prop.getProperty("getCurrentSequence");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				newCategoryCode = rset.getInt("CURRVAL");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return newCategoryCode;
	}

	/* 3. 신규 메뉴 등록 */
	public int insertNewMenu(Connection conn, MenuDTO newMenu) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertMenu");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newMenu.getMenuName());
			pstmt.setInt(2, newMenu.getMenuPrice());
			pstmt.setInt(3, newMenu.getCategoryCode());
			pstmt.setString(4, newMenu.getOrderableStatus());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	
	
	
}
