package com.greedy.section01.select;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.CategoryDTO;

public class Application2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("조회 카테고리 코드 입력 : ");
		int categoryCode = sc.nextInt();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		CategoryDTO category = null;
		
		Properties prop = new Properties();
		
		try {
			
			prop.loadFromXML(new FileInputStream("mapper/category-query.xml"));
			String query = prop.getProperty("selectCategory");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				category = new CategoryDTO();
				category.setCategoryCode(rset.getInt("CATEGORY_CODE"));
				category.setCategoryName(rset.getString("CATEGORY_NAME"));
				category.setRefCategoryCode(rset.getInt("REF_CATEGORY_CODE"));
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
		
		if(category != null) {
			System.out.println(category);			
		} else {
			System.out.println("해당하는 번호의 카테고리가 없습니다.");
		}
		

	}

}
