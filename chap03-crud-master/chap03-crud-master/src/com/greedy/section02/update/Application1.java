package com.greedy.section02.update;

import static com.greedy.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.MenuDTO;

public class Application1 {

	public static void main(String[] args) {
		
		/* 메뉴 코드 하나 입력 받아 해당 데이터의 메뉴명과 메뉴 가격 변경하는 기능 */
		Scanner sc = new Scanner(System.in);
		System.out.print("변경할 메뉴 번호 : ");
		int menuCode = sc.nextInt();
		sc.nextLine();
		System.out.print("변경할 메뉴 이름 : ");
		String menuName = sc.nextLine();
		System.out.print("변경할 메뉴 가격 : ");
		int menuPrice = sc.nextInt();
		
		MenuDTO changedMenu = new MenuDTO();
		changedMenu.setMenuCode(menuCode);
		changedMenu.setMenuName(menuName);
		changedMenu.setMenuPrice(menuPrice);
		
		/* ------------------------------------------------------- */
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		Properties prop = new Properties();
		
		try {
			
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("updateMenu");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, changedMenu.getMenuName());
			pstmt.setInt(2, changedMenu.getMenuPrice());
			pstmt.setInt(3, changedMenu.getMenuCode());
			
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
			System.out.println("메뉴 변경이 완료 되었습니다.");
		} else {
			System.out.println("메뉴 변경에 실패 하였습니다.");
		}
		
		
		
		
		
	}

}






