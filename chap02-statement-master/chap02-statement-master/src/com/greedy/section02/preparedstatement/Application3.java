package com.greedy.section02.preparedstatement;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Application3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("사번 입력 받아 해당 사원 조회하기");
		System.out.print("조회하려는 사번을 입력해주세요 : ");
		String empId = sc.nextLine();
		
		/* 1. Connection 생성 */
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {

			String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?";
			System.out.println(query);
			
			/* 2. PreparedStatement 생성 */
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			
			/* 3. executeQuery()로 쿼리문 실행하고 결과를 ResultSet으로 반환 */
			rset = pstmt.executeQuery();
			
			/* 4. ResultSet에 담긴 결과 값을 컬럼 이름을 이용해서 꺼내오기 */
			if(rset.next()) {
				System.out.println(rset.getString(1) + ", " + rset.getString(2));
			} else {
				System.out.println("해당 사번으로 조회되는 사원이 없습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* 5. 사용한 자원 반납 */
			close(rset);
			close(pstmt);
			close(conn);
		}
		
	}

}
