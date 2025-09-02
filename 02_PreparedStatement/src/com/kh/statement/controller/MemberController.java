package com.kh.statement.controller;

import java.util.List;

import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

/**
 * view에서 넘어온 요청을 처리하주는 클래스입니다. 1. 메서드로 전달된 데이터를 가공처리 한 뒤 DAO로 전달합니다. 2. DAO로부터
 * 반환받은 결과에 따라서 VIEW로부터 응답받음
 */
public class MemberController {

	/**
	 * VIEW로 부터 전달받은 문자열 값 다섯 개를 DAO로 전달하는 메서드
	 * 
	 * @param userId   : 사용자에게 입력받은 아이디값
	 * @param userPwd  : 사용자에게 입력받은 전화
	 * @param userName : 사용자에게 입력받은 이름 값
	 * @param email    : 사용자에게 입력받은 이메일 값
	 * @return 
	 * 
	 * @return : 변환 어떻게 할건지
	 * @thorow : IllegalAccessException : 잘못도니 인자가 전달하면 발생핳ㄹ 수 있음
	 */
	public int save(String userId, String userPwd, String userName, String email) {

		// 1. 데이터 가공 => 가공 넘어온 인자값이 두 개 이상일 시
		// 어딘가에 담을 것(VO, Map, DTO)
		// 전달된 인자값들을 Member객체의 필드에 담기
		// 1) 매개변수 생성자를 호출하여 객체 생성과 동시에 필드값을 대입하는 방법
		// 2) 기본 생성자로 객체를 생성한 뒤 setter메서드를 호출하는 방법
		Member member = new Member(userId, userPwd, userName, email);

		// 컨트롤러 1절 끝
		int result = new MemberDao().save(member);
		return result;

	}

	public List<Member> findAll() {

		// 1. 데이터 가공 == 할 게 없음
		// 2. DAO 호출
		List<Member> members = new MemberDao().findAll();
		
		// 3. 결과값 반환
		return members;
	}

	/**
	 * 사용자에게 입력받은 아이디 값을 이용해서 검색 요청을 처리해주는 메서드
	 * 
	 * @param userId : 사용자가 입력한 검색하고자 하는 문자열
	 * @return 
	 */
	public Member findById(String userId) {

		// 1. 데이터가공 == 없음
		// 2. 요청처리 => DAO객체 생성 후 메서드 호출
		Member member = new MemberDao().findById(userId);
		return member;
	}

	public List<Member> findByKeyword(String keyword) {
		// 결과값이 어떻게 오나
		// SELECT -> ResultSet -> Member -> List<Member>
		List<Member> members = new MemberDao().findByKeyword(keyword);
		return members;
	}

	public int update(String userId, String userPwd, String newPwd) {

		// 1. 데이터가공
		// DTO에 새로운 값들을 담아주자
		PasswordDTO pd = new PasswordDTO(userId, userPwd, newPwd);
		
		// 2. 요청처리
		// 아이디 비밀번호 바꿀비밀번호 전달
		// 아이디와 비밀번호가 맞는지 확인하고, 바꿀비밀번호로 비밀번호컬럼값 변경
		// DAO로 전달
		int result = new MemberDao().update(pd);
		return result;
	}

	public int delete(String userId, String userPwd) {

		int result = new MemberDao().delete(userId, userPwd);
		return result;
	}

}
