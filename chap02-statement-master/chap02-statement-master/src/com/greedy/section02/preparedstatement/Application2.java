package com.greedy.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.greedy.common.JDBCTemplate.*;

public class Application2 {

	public static void main(String[] args) {
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String empId = "200";
		/* PreparedStatment 객체에 전달할 query문의 조건절은 들어가야 하는 리터럴 값 자리를 ? : 위치홀더로 표시한다. */
		String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			/* PreparedStatement 객체 생성 후 ? : 위치 홀더에 들어가야 하는 값을 설정한다. 
			 * 만약 위치 홀더의 개수와 일치하지 않으면 SQLException: 인덱스에서 누락된 IN 또는 OUT 매개변수:: 1
			 * 와 같은 exception이 발생할 수 있다. */
			pstmt.setString(1, empId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
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
