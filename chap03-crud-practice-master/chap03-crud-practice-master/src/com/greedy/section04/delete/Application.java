package com.greedy.section04.delete;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 카테고리 코드 : ");
		int categoryCode = sc.nextInt();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		try {
			
			prop.loadFromXML(new FileInputStream("mapper/category-query.xml"));
			String query = prop.getProperty("deleteCategory");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryCode);
			
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
			System.out.println("카테고리 삭제 완료 되었습니다.");
		} else {
			System.out.println("카테고리 삭제 실패 하였습니다.");
		}

	}

}
