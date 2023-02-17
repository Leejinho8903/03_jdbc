package com.greedy.member.model.service;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.commit;
import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.greedy.member.model.dao.MemberDAO;
import com.greedy.member.model.dto.MemberDTO;

public class MemberService {
	
	private MemberDAO memberDAO = new MemberDAO();
	
	public int insertMember(MemberDTO member) {
		
		Connection con = getConnection();
		
		int result = 0;
		
		result = memberDAO.insertMember(con, member);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public List<MemberDTO> selectAllMembers() {

		Connection con = getConnection();
		
		List<MemberDTO> memberList = memberDAO.selectAllMembers(con);
		
		close(con);
		
		return memberList;
	}

	public MemberDTO selectById(String id) {
		
		Connection con = getConnection();
		
		MemberDTO member = memberDAO.selectById(con, id);
		
		close(con);
		
		return member;
	}

	public List<MemberDTO> selectByGender(String gender) {

		Connection con = getConnection();
		
		List<MemberDTO> memberList = memberDAO.selectByGender(con, gender);
		
		close(con);
				
		return memberList;
	}

	public int updatePassword(String memberId, String password) {

		Connection con = getConnection();
		
		int result = 0;
		
		result = memberDAO.updatePassword(con, memberId, password);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
				
		return result;
	}

	public int updateEmail(String memberId, String email) {

		Connection con = getConnection();
		
		int result = 0;
		
		result = memberDAO.updateEmail(con, memberId, email);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
				
		return result;
	}

	public int updatePhone(String memberId, String phone) {

		Connection con = getConnection();
		
		int result = 0;
		
		result = memberDAO.updatePhone(con, memberId, phone);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
						
		return result;
	}

	public int updateAddress(String memberId, String address) {

		Connection con = getConnection();
		
		int result = 0;
		
		result = memberDAO.updateAddress(con, memberId, address);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
						
		return result;
	}

	public int deleteMember(String memberId) {
		
		Connection con = getConnection();
		
		int result = 0;
		
		result = memberDAO.deleteMember(con, memberId);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
						
		return result;
	}

}
