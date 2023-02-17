package com.greedy.section01.select;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.greedy.model.dto.CategoryDTO;

public class Application1 {

	public static void main(String[] args) {
		/* TBL_CATEGORY 테이블을 대상으로 한 CRUD 기능 만들기
		 * 
		 * 1. section01.select.Application1 : 테이블 전체 데이터 조회
		 *    List<CategoryDTO> 타입으로 처리
		 *    
		 * 2. section01.select.Application2 
		 *    : Scanner로 입력 받은 categoryCode에 해당하는 행 데이터 조회
		 *    CategoryDTO 타입으로 처리
		 *    
		 * 3. section02.insert.Application
		 * 
		 * 4. section03.update.Application
		 * 
		 * 5. section04.delete.Application
		 * */
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		CategoryDTO category = null;
		List<CategoryDTO> categoryList = null;
		
		Properties prop = new Properties();
		
		try {
			
			prop.loadFromXML(new FileInputStream("mapper/category-query.xml"));
			String query = prop.getProperty("selectCategoryList");
			
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			categoryList = new ArrayList<>();
			
			while(rset.next()) {
				category = new CategoryDTO();
				category.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				category.setCategoryName(rset.getString("CATEGORY_NAME"));
				category.setRefCategoryCode(rset.getInt("REF_CATEGORY_CODE"));
				
				categoryList.add(category);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		
		for(CategoryDTO cate : categoryList) {
			System.out.println(cate);
		}
		
	}

}
