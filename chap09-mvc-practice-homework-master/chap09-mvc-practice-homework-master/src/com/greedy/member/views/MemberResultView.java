package com.greedy.member.views;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.greedy.member.model.dto.MemberDTO;

public class MemberResultView {
	
	public void displayDmlResult(String code) {
		
		switch(code) {
			case "insertFailed" : System.out.println("회원 가입 실패!"); break;
			case "updateFailed" : System.out.println("회원 정보 수정 실패!"); break;
			case "deleteFailed" : System.out.println("회원 탈퇴 실패!"); break;
			case "selectFailed" : System.out.println("회원 조회 실패!"); break;
			case "insertSuccess" : System.out.println("insert 성공!"); break;
			case "updateSuccess" : System.out.println("insert 성공!"); break;
			case "deleteSuccess" : System.out.println("insert 성공!"); break;
			default : System.out.println("알 수 없는 에러 발생!"); break;
		}
		
	}
	
	public void display(List<MemberDTO> list) {
		
		for(MemberDTO m : list) {
			System.out.println(m);
		}
		
	}
	
	public void display(MemberDTO m) {
		
		System.out.println(m);
	}
	
}





















