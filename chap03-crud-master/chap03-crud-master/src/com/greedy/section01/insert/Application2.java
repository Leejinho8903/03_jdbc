package com.greedy.section01.insert;

import static com.greedy.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Application2 {

	public static void main(String[] args) {
		
		/* 사용자 입력 값으로 menu 추가 */
		
		Scanner sc = new Scanner(System.in);
		System.out.print("메뉴 이름 : ");
		String menuName = sc.nextLine();
		System.out.print("메뉴 가격 : ");
		int menuPrice = sc.nextInt();
		System.out.print("카테고리 코드 : ");
		int categoryCode = sc.nextInt();
		sc.nextLine();
		System.out.print("판매 여부(Y/N) : ");
		String orderableStatus = sc.nextLine().toUpperCase();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("insertMenu");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, menuPrice);
			pstmt.setInt(3, categoryCode);
			pstmt.setString(4, orderableStatus);
			
			result = pstmt.executeUpdate();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		System.out.println(result + "개의 행이 삽입 되었습니다.");
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
