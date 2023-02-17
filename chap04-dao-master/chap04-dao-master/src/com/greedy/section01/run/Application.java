package com.greedy.section01.run;

import static com.greedy.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.greedy.section01.dao.MenuDAO;
import com.greedy.section01.dto.CategoryDTO;
import com.greedy.section01.dto.MenuDTO;

public class Application {

	public static void main(String[] args) {
		/* 신규 메뉴 등록 전 카테고리 목록을 조회해서 보여주고 신규 메뉴 등록을 진행하는 흐름 */
		
		MenuDAO menuDAO = new MenuDAO();
		
		Connection conn = getConnection();
		
		/* 1. 카테고리 목록 조회 */
		List<CategoryDTO> categoryList = menuDAO.selectAllCategory(conn);
		for(CategoryDTO category : categoryList) {
			System.out.println(category);
		}
		
		/* 2. 신규 메뉴 등록 */
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
		
		int result = menuDAO.insertNewMenu(conn, newMenu);
		
		if(result > 0) {
			System.out.println("신규 메뉴 등록이 완료 되었습니다.");
		} else {
			System.out.println("신규 메뉴 등록에 실패 하였습니다.");
		}
		
		close(conn);
	}

	
	
	
	
	
	
	
	
	
}
