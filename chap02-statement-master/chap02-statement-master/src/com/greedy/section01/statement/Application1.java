package com.greedy.section01.statement;

import static com.greedy.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application1 {

	public static void main(String[] args) {
		
		Connection conn = getConnection();
		
		/* 쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스 */
		Statement stmt = null;
		/* SELECT 결과 집합을 반환 받을 용도의 인터페이스 */
		ResultSet rset = null;
		
		try {
			/* Connection 인스턴스를 통해 Statement 인스턴스 생성 */
			stmt = conn.createStatement();
			
			/* 문자열로 전달한 sql 구문을 실행하고 실행 결과를 ResultSet 타입으로 반환한다. */
			rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
			
			/* 결과 집합에서 다음 행이 존재하는지를 반복문의 조건으로 작성한다. */
			while(rset.next()) {
				/* 커서가 가리키는 행에서 인자로 전달한 컬럼명의 데이터를 요청 메소드 타입의 값으로 반환한다. */
				System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* 사용했던 모든 자원을 반납한다. */
			close(rset);
			close(stmt);
			close(conn);
		}
	}
	
	
	
	
	
	
	
	
	
	

}
