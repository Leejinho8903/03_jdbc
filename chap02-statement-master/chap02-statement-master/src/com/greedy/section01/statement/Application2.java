package com.greedy.section01.statement;

import static com.greedy.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application2 {

	public static void main(String[] args) {
		
		/* 1. Connection 생성 */
		Connection conn = getConnection();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			/* 2. Statement 생성 */
			stmt = conn.createStatement();
			
			String empId = "207";
			String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";
			System.out.println(query);
			
			/* 3. executeQuery()로 쿼리문 실행하고 결과를 ResultSet으로 반환 */
			rset = stmt.executeQuery(query);
			
			/* 4. ResultSet에 담긴 결과 값을 컬럼 이름을 이용해서 꺼내오기 */
			if(rset.next()) {
				System.out.println(rset.getString(1) + ", " + rset.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* 5. 사용한 자원 반납 */
			close(rset);
			close(stmt);
			close(conn);
		}
		
		
		
		
		
		
		
		
		
		
		
	}

}
