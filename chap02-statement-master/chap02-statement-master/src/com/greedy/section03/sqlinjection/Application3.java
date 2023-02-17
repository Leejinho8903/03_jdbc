package com.greedy.section03.sqlinjection;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application3 {
	
	private static String empId = "200";
	private static String empName = "' OR 1=1 AND EMP_ID = '200";

	public static void main(String[] args) {
		
		/* 아이디와 비밀번호(이름)를 입력 받아 일치하는 행을 조회하고 로그인하는 상황을 가정한다. */
		/* SQLInjection : 값을 입력해야 하는 상황에 SQL 구문을 입력해서 데이터가 없어도 조회가 동작하도록 하는 공격
		 * Statement 객체는 SQLInjection에 대한 처리가 없지만 PreparedStatment는 SQLInjection이 동작하지 못하도록
		 * 체크하므로 기본적으로는 PreparedStatment 객체를 사용하도록 한다. 
		 * */
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ? AND EMP_NAME = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			pstmt.setString(2, empName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				System.out.println(rset.getString("EMP_NAME") + "님 환영합니다.");
			} else {
				System.out.println("회원 정보가 없습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
