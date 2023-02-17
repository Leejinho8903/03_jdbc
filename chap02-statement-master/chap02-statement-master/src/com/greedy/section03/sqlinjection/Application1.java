package com.greedy.section03.sqlinjection;

import static com.greedy.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application1 {
	
	private static String empId = "200";
	private static String empName = "홍길동";

	public static void main(String[] args) {
		
		/* 아이디와 비밀번호(이름)를 입력 받아 일치하는 행을 조회하고 로그인하는 상황을 가정한다. */
		
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "' AND EMP_NAME = '" + empName + "'";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				System.out.println(rset.getString("EMP_NAME") + "님 환영합니다.");
			} else {
				System.out.println("회원 정보가 없습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			close(conn);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
