package com.greedy.section03.layertests.model.dao;

import static com.greedy.common.JDBCTemplate.getConnection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greedy.section03.layertests.model.dto.CategoryDTO;
import com.greedy.section03.layertests.model.dto.OrderDTO;

public class OrderDAOTests {
	
	private OrderDAO orderDAO;
	private Connection conn;
	private OrderDTO order;
	
	@BeforeEach
	public void setup() {
		orderDAO = new OrderDAO();
		conn = getConnection();
		
		order = new OrderDTO();
		order.setDate("23/01/25");
		order.setTime("11:35:20");
		order.setTotalOrderPrice(30000);
	}
	
	@Test
	public void testSelectAllCategory() {
		List<CategoryDTO> categoryList = orderDAO.selectAllCategory(conn);
		
		assertNotNull(categoryList);
		for(CategoryDTO category : categoryList) {
			System.out.println(category);
		}
	}
	
	@Test
	public void testInsertOrder() {
		int result = orderDAO.insertOrder(conn, order);
		
		assertEquals(1, result);
	}

}









