package com.greedy.section01.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.greedy.section01.dto.CategoryDTO;
import com.greedy.section01.dto.MenuDTO;

import static com.greedy.common.JDBCTemplate.close;

/* DAO(Data Access Object) : 데이터베이스 접근용 객체
 * CRUD 연산을 담당하는 메소드들의 집합으로 이루어진 클래스를 말한다.
 * */
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

	/* 2. 신규 메뉴 등록 */
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







