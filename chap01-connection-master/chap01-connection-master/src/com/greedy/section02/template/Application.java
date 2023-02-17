package com.greedy.section02.template;

import java.sql.Connection;
//import static com.greedy.section02.template.JDBCTemplate.getConnection;
//import static com.greedy.section02.template.JDBCTemplate.close;
import static com.greedy.section02.template.JDBCTemplate.*;
public class Application {

	public static void main(String[] args) {
		
		Connection conn = /*JDBCTemplate.*/getConnection();
		System.out.println(conn);
		
		close(conn);
		
	}

}
