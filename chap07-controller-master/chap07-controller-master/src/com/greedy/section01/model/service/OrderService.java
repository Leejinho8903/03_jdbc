package com.greedy.section01.model.service;

import java.sql.Connection;
import java.util.List;

import com.greedy.section01.model.dao.OrderDAO;
import com.greedy.section01.model.dto.CategoryDTO;
import com.greedy.section01.model.dto.MenuDTO;
import com.greedy.section01.model.dto.OrderDTO;
import com.greedy.section01.model.dto.OrderMenuDTO;

import static com.greedy.common.JDBCTemplate.*;

public class OrderService {
	
	private OrderDAO orderDAO = new OrderDAO();

	/* 카테고리 전체 조회용 메소드 */
	public List<CategoryDTO> selectAllCategory() {
		
		/* 1. Connection 생성 */
		Connection con = getConnection();
		
		/* 2. DAO의 모든 카테고리 조회용 메소드를 호출하여 결과 리턴 받기 */
		List<CategoryDTO> categoryList = orderDAO.selectAllCategory(con);
		
		/* 3. 커넥션 닫기 (select는 commit, rollback X) */
		close(con);
		
		/* 4. 반환 받은 값 리턴하기 */
		return categoryList;
	}

	/* 카테고리별 메뉴 조회용 메소드 */
	public List<MenuDTO> selectMenuByCategory(int categoryCode) {
		/* 1. Connection 생성 */
		Connection con = getConnection();
		
		/* 2. DAO의 해당 카테고리 메뉴를 조회하는 메소드로 categoryCode 전달하며 조회 */
		List<MenuDTO> menuList = orderDAO.selectMenuByCategory(con, categoryCode);
		
		/* 3. Connection 닫기 */
		close(con);
		
		/* 4. 반환받은 값 리턴하기 */
		return menuList;
	}

	/* 주문 정보 등록용 메소드 */
	public int registOrder(OrderDTO order) {
		/* 1. Connection 생성 */
		Connection con = getConnection();
		
		/* 2. 리턴할 값 초기화 */
		int result = 0;
		
		/* 3. DAO 메소드로 전달 받은 값 넘겨서 insert */
		/* 3-1. Order table insert */
		int orderResult = orderDAO.insertOrder(con, order);
		
		/* 3-2. Order Menu table insert */
		List<OrderMenuDTO> orderMenuList = order.getOrderMenuList();
		int orderMenuResult = 0;
		for(OrderMenuDTO orderMenu : orderMenuList) {
			orderMenuResult += orderDAO.insertOrderMenu(con, orderMenu);
		}
		
		/* 4. 성공 여부 판단 후 트랜잭션 처리 */
		if(orderResult > 0 && orderMenuResult == orderMenuList.size()) {
			commit(con);
			result = 1;
		} else {
			rollback(con);
		}
		
		/* 5. Connection 닫기 */
		close(con);
		
		/* 6. 결과 값 반환 */
		return result;
	}

	
	
	
	
	
	
	
	
	
	
}
