package com.greedy.section02.service.model.service;

import static com.greedy.common.JDBCTemplate.*;

import java.sql.Connection;

import com.greedy.section02.service.model.dao.MenuDAO;
import com.greedy.section02.service.model.dto.CategoryDTO;
import com.greedy.section02.service.model.dto.MenuDTO;

/* Service의 역할
 * 1. Connection 생성
 * 2. DAO의 메소드 호출 (논리적인 기능 단위로 묶인 메소드들을 모두 호출하므로 여러번 호출 할 수도 있음) 
 * 3. 트랜잭션 제어
 * 4. Connection 닫기
 * */
public class MenuService {

	/* 신규 메뉴 등록용 서비스 메소드 */
	public void registNewMenu() {
		
		/* 1. Connection 생성 */
		Connection conn = getConnection();
	
		/* 2. DAO 메소드 호출 */
		MenuDAO menuDAO = new MenuDAO();
		
		/* 2-1. 신규 카테고리 등록 */
		CategoryDTO newCategory = new CategoryDTO();
		newCategory.setCategoryName("기타");
		newCategory.setRefCategoryCode(null);
		
		int result1 = menuDAO.insertNewCategory(conn, newCategory);
		
		/* 2-2. 신규 카테고리 등록 시 발생한 카테고리 코드 번호 조회 */
		int newCategoryCode = menuDAO.selectLastCategoryCode(conn);
		
		/* 2-3. 등록 된 신규 카테고리로 메뉴 등록 */
		MenuDTO newMenu = new MenuDTO();
		newMenu.setMenuName("메롱메롱스튜");
		newMenu.setMenuPrice(40000);
		newMenu.setCategoryCode(newCategoryCode);
		newMenu.setOrderableStatus("Y");
		
		int result2 = menuDAO.insertNewMenu(conn, newMenu);
		
		/* 3. 트랜잭션 제어 */
		if(result1 > 0 && result2 > 0) {
			System.out.println("신규 카테고리와 해당 카테고리의 신규 메뉴가 추가 되었습니다.");
			commit(conn);
		} else {
			System.out.println("신규 카테고리와 해당 카테고리의 신규 메뉴 추가에 실패하였습니다.");
			rollback(conn);
		}
		
		/* 4. Connection 반납 */
		close(conn);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
