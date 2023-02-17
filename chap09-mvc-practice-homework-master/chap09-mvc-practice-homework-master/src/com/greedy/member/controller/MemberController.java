package com.greedy.member.controller;

import java.util.Map;

import com.greedy.member.model.service.MemberService;
import com.greedy.member.views.MemberResultView;

public class MemberController {
	
	/* MemberResultView 클래스에 작성한 display 메소드로 출력할 것
	 * display 메소드로 출력할 필요 없는 내용은 간단히 콘솔에 직접 출력
	 * dml 수행 결과는 MemberResultView의 displayDmlResult()를 이용할 것
	 * */
	
	private MemberResultView memberResultView = new MemberResultView();
	private MemberService memberService = new MemberService();

	/* 신규 회원 등록용 메소드 */
	public void registNewMember(Map<String, String> requestMap) {
		
		/* Map으로 전달 된 데이터를 꺼내 MemberDTO에 담아 Service로 전달 */
		
	}
	
	/* 모든 회원 정보 조회용 메소드(List로 조회할 것) */
	public void selectAllMembers() {
		
	}
	
	/* 아이디를 이용한 회원 정보 검색(MemberDTO로 한 명 회원 정보 조회) */
	public void searchMemberById(String id) {
		
	}
	
	/* 성별을 이용한 회원 정보 검색 (List로 조회할 것)*/
	public void searchMemberByGender(String gender) {
		
	}
	
	/* 입력받은 아이디와 일치하는 회원의 비밀번호 변경 */
	public void modifyPassword(String memberId, String password) {
		
	}
	
	/* 입력받은 아이디와 일치하는 회원의 이메일 변경 */
	public void modifyEmail(String memberId, String email) {
		
	}
	
	/* 입력받은 아이디와 일치하는 회원의 전화번호 변경 */
	public void modifyPhone(String memberId, String phone) {
		
	}
	
	/* 입력받은 아이디와 일치하는 회원의 주소 변경 */
	public void modifyAddress(String memberId, String address) {
		
	}
	
	/* 회원 정보 삭제용 메소드 */
	public void deleteMember(String memberId) {
		
	}

}
