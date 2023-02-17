package com.greedy.section02.preparedstatement;

import static com.greedy.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.greedy.model.dto.EmployeeDTO;

public class Application5 {

	public static void main(String[] args) {
		
		/* EMPLOYEE 테이블에서 조회할 사원의 이름의 성을 입력 받아 해당 성씨를 가진 사원 정보를 모두 출력한다. */
		Scanner sc = new Scanner(System.in);
		System.out.print("조회할 이름의 성을 입력하세요 : ");
		String empName = sc.nextLine();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		EmployeeDTO employee = null;
		List<EmployeeDTO> empList = null;
		
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE ? || '%'";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empName);
			
			rset = pstmt.executeQuery();
			
			empList = new ArrayList<>();
			
			while(rset.next()) {
				employee = new EmployeeDTO();
				
				employee.setEmpId(rset.getString("EMP_ID"));
				employee.setEmpName(rset.getString("EMP_NAME"));
				employee.setEmpNo(rset.getString("EMP_NO"));
				employee.setEmail(rset.getString("EMAIL"));
				employee.setPhone(rset.getString("PHONE"));
				employee.setDeptCode(rset.getString("DEPT_CODE"));
				employee.setJobCode(rset.getString("JOB_CODE"));
				employee.setSalLevel(rset.getString("SAL_LEVEL"));
				employee.setSalary(rset.getInt("SALARY"));
				employee.setBonus(rset.getDouble("BONUS"));
				employee.setManagerId(rset.getString("MANAGER_ID"));
				employee.setHireDate(rset.getDate("HIRE_DATE"));
				employee.setEntDate(rset.getDate("ENT_DATE"));
				employee.setEntYn(rset.getString("ENT_YN"));
				
				empList.add(employee);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		
		for(EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
		
	}

}
