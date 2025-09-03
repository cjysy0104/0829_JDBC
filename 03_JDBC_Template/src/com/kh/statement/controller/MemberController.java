package com.kh.statement.controller;

import java.util.List;

import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.service.MemberService;
import com.kh.statement.model.vo.Member;

/*
 * 컨트롤러의 역할
 * 
 * 클라이언트(View)의 요청을 수신
 * 적절한 서비스 메서드 호출
 * 뷰 선택 및 데이터 전달
 */
public class MemberController {
	
	public int save(String userId, String userPwd, String userName, String email) {
		Member member = new Member(userId, userPwd, userName, email);

		int result = new MemberService().save(member);
		return result;

	}

	public List<Member> findAll() {

		List<Member> members = new MemberService().findAll();

		return members;
	}

	/**
	 * 사용자에게 입력받은 아이디 값을 이용해서 검색 요청을 처리해주는 메서드
	 * 
	 * @param userId : 사용자가 입력한 검색하고자 하는 문자열
	 * @return
	 */
	public Member findById(String userId) {

		Member member = new MemberService().findById(userId);
		return member;
	}

	public List<Member> findByKeyword(String keyword) {
		List<Member> members = new MemberService().findByKeyword(keyword);
		return members;
	}

	public int update(String userId, String userPwd, String newPwd) {

		PasswordDTO pd = new PasswordDTO(userId, userPwd, newPwd);

		int result = new MemberService().update(pd);
		return result;
	}

	public int delete(String userId, String userPwd) {

		Member member = new Member(userId, userPwd);
		int result = new MemberService().delete(member);
		return result;
	}

}
