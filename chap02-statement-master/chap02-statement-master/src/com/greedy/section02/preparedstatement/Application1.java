package com.greedy.section02.preparedstatement;

import static com.greedy.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application1 {

	public static void main(String[] args) {
		
		Connection conn = getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			/* PreparedStatement 객체는 생성 시에 sql 구문을 미리 전달한다. */
			pstmt = conn.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
			/* 쿼리 실행을 요청할 때는 sql 구문을 전달하지 않는다. */
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
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
