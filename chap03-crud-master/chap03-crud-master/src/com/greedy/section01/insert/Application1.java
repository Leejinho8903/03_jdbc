package com.greedy.section01.insert;

import static com.greedy.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Application1 {

	public static void main(String[] args) {
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		/* SELECT시에는 수행 결과가 ResultSet으로 반환되지만
		 * INSERT/UPDATE/DELETE시에는 수행결과가 삽입/수정/삭제 된 행의 개수(int)로 반환된다. */
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("insertMenu");
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "봉골레청국장");	//menuName
			pstmt.setInt(2, 50000);				//menuPrice
			pstmt.setInt(3, 4);					//categoryCode
			pstmt.setString(4, "Y"); 			//orderableStatus
			
			/* SELECT시에는 executeQuery() : ResultSet
			 * INSERT/UPDATE/DELETE시에는 executeUpdate() : int */
			result = pstmt.executeUpdate();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		System.out.println(result + "개의 행이 삽입되었습니다.");
		
		
	}

}
