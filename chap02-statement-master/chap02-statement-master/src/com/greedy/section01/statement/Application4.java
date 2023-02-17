package com.greedy.section01.statement;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.greedy.model.dto.EmployeeDTO;

public class Application4 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("사번 입력 받아 해당 사원 조회하기");
		System.out.print("조회하려는 사번을 입력해주세요 : ");
		String empId = sc.nextLine();
		
		/* 1. Connection 생성 */
		Connection conn = getConnection();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		EmployeeDTO selectedEmp = null;
		
		try {
			/* 2. Statement 생성 */
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";
			System.out.println(query);
			
			/* 3. executeQuery()로 쿼리문 실행하고 결과를 ResultSet으로 반환 */
			rset = stmt.executeQuery(query);
			
			/* 4. ResultSet에 담긴 결과 값을 컬럼 이름을 이용해서 꺼내오기 */
			if(rset.next()) {
				selectedEmp = new EmployeeDTO();
				
				selectedEmp.setEmpId(rset.getString("EMP_ID"));
				selectedEmp.setEmpName(rset.getString("EMP_NAME"));
				selectedEmp.setEmpNo(rset.getString("EMP_NO"));
				selectedEmp.setEmail(rset.getString("EMAIL"));
				selectedEmp.setPhone(rset.getString("PHONE"));
				selectedEmp.setDeptCode(rset.getString("DEPT_CODE"));
				selectedEmp.setJobCode(rset.getString("JOB_CODE"));
				selectedEmp.setSalLevel(rset.getString("SAL_LEVEL"));
				selectedEmp.setSalary(rset.getInt("SALARY"));
				selectedEmp.setBonus(rset.getDouble("BONUS"));
				selectedEmp.setManagerId(rset.getString("MANAGER_ID"));
				selectedEmp.setHireDate(rset.getDate("HIRE_DATE"));
				selectedEmp.setEntDate(rset.getDate("ENT_DATE"));
				selectedEmp.setEntYn(rset.getString("ENT_YN"));
				
			} else {
				System.out.println("해당 사번으로 조회되는 사원이 없습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* 5. 사용한 자원 반납 */
			close(rset);
			close(stmt);
			close(conn);
		}
		
		System.out.println("selectedEmp : " + selectedEmp);
		
	}

}
