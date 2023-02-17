package com.greedy.section01.insert;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.MenuDTO;

public class Application3 {

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
		
		MenuDTO newMenu = new MenuDTO();
		newMenu.setMenuName(menuName);
		newMenu.setMenuPrice(menuPrice);
		newMenu.setCategoryCode(categoryCode);
		newMenu.setOrderableStatus(orderableStatus);
		
		/* -------------------------------------------------- */
		/* 값을 뭉쳐서 전송하기 위해 MenuDTO에 값을 담고 전송한다고 가정 */
		/* -------------------------------------------------- */
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("insertMenu");
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newMenu.getMenuName());
			pstmt.setInt(2, newMenu.getMenuPrice());
			pstmt.setInt(3, newMenu.getCategoryCode());
			pstmt.setString(4, newMenu.getOrderableStatus());
			
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
			System.out.println("메뉴 등록이 완료 되었습니다.");
		} else {
			System.out.println("메뉴 등록에 실패 하였습니다.");
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
