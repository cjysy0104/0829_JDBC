package com.kh.statement.model.service;

import java.sql.Connection;
import java.util.List;

// 원래라면 import static com.kh.common.JDBCTemplate.getConnection; 기입해주기 >> 편의상 생략
import static com.kh.common.JDBCTemplate.*;

import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

// 클라이언트의 요청처리
// 제어흐름 --> 컨트롤러
// 핵심로직 실행 --> 서비스

/*
 * 비즈니스 로직 실행(의사결정코드) -> 데이터 가공, 중복 체크, 연산 처리, 암호화
 * 트랜잭션 관리
 * 여러 DAO를 조합
 * 예외 처리 및 변환
 * 보안 및 권한 검사
 */
public class MemberService {
	/*
	 * Service
	 * : 비즈니스로직 / 의사결정코드를 작성하는 부분
	 * 
	 * Controller -> Service의 메서드를 호출
	 * Service에서 Connection을 생성해서 DAO로 전달
	 * 만약 SQL문을 수행해야하는데 필요한 값이 있다면 Controller로부터 전달받아서
	 * Connection과 같이 넘겨줄 것
	 * DAO에서 DB작업이 끝나면 Service단에서 결과에 따른 트랜잭션 처리도 진행
	 * 
	 * => Service를 추가함으로 DAO는 순수하게 SQL문을 처리하는 부분만 남겨둘 것
	 */
	
	// : 1 Connection 처리
	private Connection conn = null;
	
	public MemberService() {
		this.conn = getConnection();
	}
	
	public int save(Member member) {

		// Connection 객체 생성 > 생성자
		//Connection conn = getConnection();

		// DAO 호출 시 Connection 객체 전달
		// +
		// Controller가 넘겨준 사용자가 입력한 값이 필드에 담겨있는
		// Member 참조변수를 함께 넘겨줌
		int result = new MemberDao().save(conn, member);

		// 트랜잭션 처리
		if (result > 0) {
			commit(conn);
		}
		
		// 자원반납
		close(conn);

		return result;
	}

	public List<Member> findAll() {
		
		// 1) Connection 객체 생성: 생성자로 처리완료
//		Connection conn = getConnection();
		
		// 2) DAO호출해서 반환받기
		List<Member> members = new MemberDao().findAll(conn);
		
		// 3) Connection 반납
		close(conn);
		
		// 4) 결과 반환
		return members;
	}

	public Member findById(String userId) {
		
		// -1) Connection 객체 생성: 생성자로 처리
//		Connection conn = getConnection();
		
		// 1) DAO호출해서 반환받기
		Member member = new MemberDao().findById(conn, userId);
		
		// 2) Connection 반납
		close(conn);
		
		// 3) 결과 반환
		return member;
	}

	public List<Member> findByKeyword(String keyword) {

		List<Member> members = new MemberDao().findByKeyword(conn, keyword);
		
		close(conn);
		
		return members;
	}

	public int update(PasswordDTO pd) {
		// 회원의 비밀번호 수정 == member 테이블에서 한 행 UPDATE
		// 비밀번호 수정
		if(pd.getNewPwd().length() > 20) {
			//throw new RuntimeException("너무 긴 비밀번호");
			return 0;
		}
		
		Member member = new MemberDao().findById(conn, pd.getUserId());
		if(member == null) {
			//throw new RuntimeException("존재하지 않는 아이디");
			return 0;
		}
		
		int result = new MemberDao().update(conn, pd);
		
		if (result > 0) {
			commit(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int delete(Member member) {
		
		int result = new MemberDao().delete(conn, member);
		
		if (result > 0) {
			commit(conn);
		}
		
		close(conn);
		
		return result;
	}

}
