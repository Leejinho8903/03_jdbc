package com.greedy.member.model.dao;

import static com.greedy.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.greedy.member.model.dto.MemberDTO;

public class MemberDAO {

	private Properties prop = new Properties();
	
	public MemberDAO() {
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/member-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMember(Connection con, MemberDTO member) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setInt(8, member.getAge());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<MemberDTO> selectAllMembers(Connection con) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<MemberDTO> memberList = null;
		
		String query = prop.getProperty("selectAllMembers");
		
		try {
			pstmt = con.prepareStatement(query);
						
			rset = pstmt.executeQuery();
			
			memberList = new ArrayList<>();
			
			while(rset.next()) {
				MemberDTO member = new MemberDTO();
				member.setMemberNo(rset.getInt("MEMBER_NO"));
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setAge(rset.getInt("AGE"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return memberList;
	}

	public MemberDTO selectById(Connection con, String id) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		MemberDTO member = null;
		
		String query = prop.getProperty("selectById");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new MemberDTO();
				
				member.setMemberNo(rset.getInt("MEMBER_NO"));
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setAge(rset.getInt("AGE"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public List<MemberDTO> selectByGender(Connection con, String gender) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<MemberDTO> memberList = null;
		
		String query = prop.getProperty("selectByGender");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, gender);
						
			rset = pstmt.executeQuery();
			
			memberList = new ArrayList<>();
			
			while(rset.next()) {
				MemberDTO member = new MemberDTO();
				member.setMemberNo(rset.getInt("MEMBER_NO"));
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setAge(rset.getInt("AGE"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return memberList;
	}

	public int updatePassword(Connection con, String memberId, String password) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePassword");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, password);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateEmail(Connection con, String memberId, String email) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateEmail");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updatePhone(Connection con, String memberId, String phone) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePhone");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, phone);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAddress(Connection con, String memberId, String address) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateAddress");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, address);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection con, String memberId) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
