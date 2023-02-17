package com.greedy.section01.model.service;

import static com.greedy.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.greedy.section01.model.dao.OrderDAO;
import com.greedy.section01.model.dto.CategoryDTO;
import com.greedy.section01.model.dto.MenuDTO;
import com.greedy.section01.model.dto.OrderDTO;
import com.greedy.section01.model.dto.OrderMenuDTO;

public class OrderService {
	
	private OrderDAO orderDAO = new OrderDAO();

	public List<CategoryDTO> selectAllCategory() {
		
		/* 1. Connection 생성 */
		Connection conn = getConnection();
		/* 2. DAO의 메소드 호출하여 결과 리턴 받기 */
		List<CategoryDTO> categoryList = orderDAO.selectAllCategory(conn);
		/* 3. 트랜젝션 관리(select의 경우 commit, rollback이 필요 없음) */
		/* 4. Connection 반납 */
		close(conn);
		
		return categoryList;
		
	}

	public List<MenuDTO> selectMenuByCategory(int categoryCode) {
		
		/* 1. Connection 생성 */
		Connection conn = getConnection();
		/* 2. DAO의 메소드 호출하여 결과 리턴 받기 */
		List<MenuDTO> menuList = orderDAO.selectMenuByCategory(conn, categoryCode);
		/* 3. 트랜젝션 관리(select의 경우 commit, rollback이 필요 없음) */
		/* 4. Connection 반납 */
		close(conn);
		
		return menuList;
	}

	public int registOrder(OrderDTO order) {
		
		/* 1. Connection 생성 */
		Connection conn = getConnection();
		
		/* 2. DAO의 메소드 호출하여 결과 리턴 받기 */
		/* 2-1. ORDER TABLE INSERT */
		int orderResult = orderDAO.insertOrder(conn, order);
		/* 2-2. ORDER MENU TABLE INSERT */
		List<OrderMenuDTO> orderMenuList = order.getOrderMenuList();
		int orderMenuResult = 0;
		for(OrderMenuDTO orderMenu : orderMenuList) {
			orderMenuResult += orderDAO.insertOrderMenu(conn, orderMenu);
		}
		
		int result = 0; 	//전체 프로세스 성공, 실패를 나타내는 변수
		
		/* 3. 트랜잭션 관리 */
		if(orderResult > 0 && orderMenuResult == orderMenuList.size()) {
			commit(conn);
			result = 1;
		} else {
			rollback(conn);
		}
		
		/* 4. Connection 반납 */
		close(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
