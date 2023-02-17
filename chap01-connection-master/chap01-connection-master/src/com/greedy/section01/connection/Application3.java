package com.greedy.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application3 {

	public static void main(String[] args) {
		
		/* .properties 파일에서 설정 정보 읽어와 connection 생성하는 코드로 개선한다. */
		Properties prop = new Properties();
		Connection conn = null;
		
		try {
			prop.load(new FileReader("src/com/greedy/section01/connection/jdbc-config.properties"));
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);
			
			System.out.println(conn);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
