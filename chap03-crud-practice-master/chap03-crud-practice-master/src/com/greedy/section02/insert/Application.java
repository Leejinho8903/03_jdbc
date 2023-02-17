package com.greedy.section02.insert;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.CategoryDTO;

public class Application {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("카테고리 이름 : ");
		String categoryName = sc.nextLine();
		System.out.print("상위 카테고리 코드 : ");
		int refCategoryCode = sc.nextInt();
		
		CategoryDTO newCategory = new CategoryDTO();
		newCategory.setCategoryName(categoryName);
		newCategory.setRefCategoryCode(refCategoryCode);
		
		/* ------------------------------------------- */
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			
			prop.loadFromXML(new FileInputStream("mapper/category-query.xml"));
			String query = prop.getProperty("insertCategory");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newCategory.getCategoryName());
			pstmt.setInt(2, newCategory.getRefCategoryCode());
			
			result = pstmt.executeUpdate();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		if(result > 0) {
			System.out.println("신규 카테고리 등록이 완료 되었습니다.");
		} else {
			System.out.println("신규 카테고리 등록에 실패 하였습니다.");
		}
		
		

	}

}
