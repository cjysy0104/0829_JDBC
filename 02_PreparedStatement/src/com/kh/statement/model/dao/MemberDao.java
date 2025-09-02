package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberDao {
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@115.90.212.20:10000:XE";
	private final String USERNAME = "CJY19";
	private final String PASSWORD = "CJY191234";
	/*
	 * DAO(Data Access Object)
	 * 
	 * 지금 시점 DAO에서는 DataBase 관련된 작업(CRUD)를 전문적으로 담당하는 객체 DAO안에 있는 모든 메서드를 데이터베이스 관련된
	 * 작업으로 구성할 것
	 * 
	 * SQL SELECT / INSERT / UPDATE / DELETE
	 * 
	 * 하나의 메서드는 하나의 SQL문만 샐행할 것!
	 * 
	 * Controller를 통해 호출된 기능을 수행! -> 오늘까지만 (09/20) DB에 직접 접근한 후 해당 SQL문을 실행한 후 결과
	 * 받아오기(JDBC)
	 */
	/*
	 * JDBC용 객체
	 * 
	 * -Connection : DB와의 연결정보를 담는 객체(IP주소, Port번호, 사용자이름, 비밀번호) -Statement :
	 * Connection에 담겨있는 연결정보 DB에 DQL문을 보내서 실행하고 결과도 받아오는 객체 -ResultSet : 실행한 SQL문이
	 * SELECT문일 경우 조회된 결과가 처음 담겨있는 객체 -PreparedStatement : SQL문을 미리 준비하는 개념 미완성된
	 * SQL문을 미리 전달하고 실행하기 전 완성형태로 만든 뒤, SQL문을 실행함 미완성된 SQL문에 사용자가 입력한 값들이 들어갈 수 있도록
	 * 공간을 확보해놓음 ==> ?(placeholder/위치홀더)
	 * 
	 * - Statement와 PreparedStatement는 부모자식 관계
	 * 
	 * - 차이점
	 * 
	 * 1) Statement는 완성된 SQP문, PreparedStatement는 미완성된 SQL문
	 * 
	 * 2) 객체 생성 방법 Statement == 커넥션객체.createStatement() PreparedStatement ==
	 * 커넥션객체.prepareStatement(sql); <==핵심!
	 * 
	 * 3) SQL문 실행 Statement == stat.executeXXX(sql); prepareStatement =
	 * pstat.executeXXX();
	 * 
	 * ? 위치폴더에 실제 값을 Binding해준 뒤 실행한다. pstat.setString() pstat.setInt()
	 * 
	 * --JDBC 절차
	 * 
	 * 0) 필요한 변수들 세팅 1) JDBC Driver 등록 : 해당 DBMS에서 제공하는 클래스를 String으로 동적 교회 2)
	 * Connection 객체 생성 : DB와의 세션연결 연결할 때 필요한 정보를 인자로 전달(URL, 사용자이름, 비밀번호) 3_1)
	 * PreparedStatement 객체 생성 : Connection 객체 생성(미완성된 SQL문을 생성과 동시에 꼭 전달) 3_2) 현재
	 * 미완성된 SQL문을 완성형태로 만들어주기 => 미완성일 경우에만 해당 / 완성된 경우에는 생략 4) SQL 실행 : executeXXX()
	 * => SQL을 절대로 인자로 전달하지 않음!! > SELECT : executeQuery() > INSERT / UPDATE /
	 * DELETE : executeUpdate() 5) 결과받기 : > SELECT : ResultSet(조회된 데이터들이 담겨있음) >
	 * INSERT / UPDATE / DELETE : int(처리된 행의 개수) 6) SELECT : Result에 담겨있는 컬람값들을 커서를
	 * 옮겨가며 한 행씩 접근해서 하나하나 뽑아서 VO객체의 필드에 매핑(옮겨담기) -? VO객체가 여러 개일 경우 -> VO를 List의 요소로
	 * 관리 7) 사용이 다 끝난 JDBC용 객체들을 생성의 역순으로 ㅇ자원반납 -> close() 9) 결과 반환 SELECT-> 6에서 만든것
	 * INSERT / UPDATE / DELETE -> 처리된 행의 개수
	 */

	public int save(Member member) {

		// 0) 필요한 변수들을 선언
		Connection conn = null; // DB와의 세션
		PreparedStatement pstat = null; // SQL문 실행 후 결과받기
		int result = 0;

		// SQL문
		String sql = """
				INSERT
				  INTO
				  	   MEMBER
				VALUES
				 	   (
				 	   SEQ_USERNO.NEXTVAL
				 	 , ?
				 	 , ?
				 	 , ?
				 	 , ?
				 	 , SYSDATE
				 	   )
				""";

		try {
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "CJY19", "CJY191234");
			conn.setAutoCommit(false);

			// 3_1) PreparedStatement 객체 생성(SQL문 미리 전달해야함)
			pstat = conn.prepareStatement(sql);

			// ID, PWD, NAME, EMAIL

			// 3_2) 미완성된 SQL문일 경우 완성시켜주기
			// 위치홀더에 값 바인딩
			// pstat.setXXX(?의 위치, 실제값);
			pstat.setString(1, member.getUserId());
			pstat.setString(2, member.getUserPwd());
			pstat.setString(3, member.getUserName());
			pstat.setString(4, member.getEmail());

			// pstat.setString(홀더순번, 값)
			// => '값' (양옆에 홀따옴표 알아서 Bind)
			// pstat.setInt(홀더순번, 값)
			// => 값 (알아서 잘 들어감, 따옴표안붙임)

			// 4,5) DB에 전달된 SQL문 실행하고 결과(처리되 행의 경기수)
			result = pstat.executeUpdate();

			// 6) 트랜잭션 처리
			if (result > 0) {
				conn.commit();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 자원반납
			try {
				if (pstat != null) {
					pstat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		// 8) 자원반납
		return result;
	}

	public List<Member> findAll() {

		// 0)
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rset = null;
		// 멤버 객체만 담을 수 있는 리스트
		List<Member> members = new ArrayList<Member>();
		String sql = """
				SELECT
					   USERNO
					 , USERID
					 , USERPWD
					 , USERNAME
					 , EMAIL
					 , ENROLLDATE
				  FROM
				  	   MEMBER
				 ORDER
				 	BY
				 	   ENROLLDATE DESC
				""";

		try {
			// 1)
			Class.forName(DRIVER);
			// 2)
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3)
			pstat = conn.prepareStatement(sql);
			// 4, 5)
			rset = pstat.executeQuery();
			// 6)
			// 조회결과가 존재하는가
			// 존재할 경우 한 명씩 접근해서 컬럼의 값을 뽑아서 VO필드에 매핑
			while (rset.next()) {
				Member member = new Member();

				member.setUserNo(rset.getInt("USERNO"));
				member.setUserId(rset.getString("USERID"));
				member.setUserPwd(rset.getString("USERPWD"));
				member.setUserName(rset.getString("USERNAME"));
				member.setEmail(rset.getString("EMAIL"));
				member.setEnrollDate(rset.getDate("ENROLLDATE"));

				members.add(member);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7)
			try {
				if (rset != null) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstat != null) {
					pstat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 8)
		return members;
	}
	/*
	 * PreparedStatement가 Statement보다 좋음
	 * 
	 * 1. 구문분석 및 컴파일 최적화
	 * 
	 * stmt.executeUpdate(sql); pstmt.executeUpdate();
	 * 
	 * Statement는 매 번 SQL문을 파싱하고 실행하지만 PreparedStatement는 SQL쿼리를 최초 1회만 파싱하고 실행계획을
	 * 캐싱(메모리에 올림)
	 * 
	 * 2. DB서버에 대한 트래픽 감소
	 * 
	 * 쿼리 자체는 한 번만 전송하고 이후에는 바인딩할 값만 정송하기 때문에 효율적
	 * 
	 * 동일쿼리를 반복 실행할 때, 높은 트래픽이 몰리는 애플리케이션일 때 더욱 더 효율적이다.
	 * 
	 * DB작업 -> 계획 짤 때 리소스를 많이 잡아먹음
	 * 
	 * 3. Statement는 SQL Injection을 막을 수 없음
	 * 
	 * PreparedStaatement는 Injection 방지가 됨 ==> 보안적인 측면에서도 훨씬 좋다.
	 * 
	 */

	public Member findById(String userId) {
		Member member = null;
		// 0)
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rset = null;
		String sql = """
				SELECT
					   USERNO
					 , USERID
					 , USERPWD
					 , USERNAME
					 , EMAIL
					 , ENROLLDATE
				 	  FROM
				 	  	   MEMBER
				 	 WHERE
				 	 	   USERID = ?
				             	   """;

		try {
			Class.forName(DRIVER);

			// 2) Connection
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// 3)
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, userId);

			// 4, 5)
			rset = pstat.executeQuery();

			// 6)
			if (rset.next()) {
				member = new Member(rset.getInt("USERNO"), rset.getString("USERID"), rset.getString("USERPWD"),
						rset.getString("USERNAME"), rset.getString("EMAIL"), rset.getDate("ENROLLDATE"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {

					rset.close();
				}
			} catch (SQLException e) {
				// TODO Auto-gnerated catch block
				e.printStackTrace();
			}
			try {
				if (pstat != null) {
					pstat.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 8)
		return member;
	}

	public List<Member> findByKeyword(String keyword) {
		// 0
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rset = null;
		List<Member> members = new ArrayList<Member>();
		String sql = """
				SELECT
					   USERNO
					 , USERID
					 , USERPWD
					 , USERNAME
					 , EMAIL
					 , ENROLLDATE
				  FROM
				  	   MEMBER
				 WHERE
				 	   USERNAME LIKE ?

				 ORDER
				 	BY
				 	   ENROLLDATE DESC
				""";

		//
		try {
			Class.forName(DRIVER);

			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, "%" + keyword + "%");

			rset = pstat.executeQuery();

			while (rset.next()) {
				members.add(new Member(rset.getInt("USERNO"), rset.getString("USERID"), rset.getString("USERPWD"),
						rset.getString("USERNAME"), rset.getString("EMAIL"), rset.getDate("ENROLLDATE")));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstat != null) {
					pstat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return members;
	}

	public int update(PasswordDTO pd) {
		// update 할 일 : 전달받은 값을 가지고 값이 존재하는 행을 찾아서 정보를 갱신
		// 해당 메서드 역할 : SQL문 실행하고 결과받아오기

		// 0
		int result = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		String sql = """
				UPDATE
					   MEMBER
				   SET
				   	   USERPWD = ?
				 WHERE
				 	   USERID = ?
				   AND
				   	   USERPWD = ?
				""";
		try {
			// 1
			Class.forName(DRIVER);

			// 2
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
			// 3
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pd.getNewPwd());
			pstat.setString(2, pd.getUserId());
			pstat.setString(3, pd.getUserPwd());

			//
			result = pstat.executeUpdate();
			if (result > 0) {
				conn.commit();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstat != null) {
					pstat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int delete(String userId, String userPwd) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		String sql = """
				DELETE
					   MEMBER
				 WHERE
				 	   USERNO = (SELECT
				 	   				    USERNO
				 	   			   FROM
				 	   			   		MEMBER
				 	   			  WHERE
				 	   			  		USERID = ?
				 	   			    AND
				 	   			    	USERPWD = ?)
				""";

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, userId);
			pstat.setString(2, userPwd);
			result = pstat.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstat != null) {
					
					pstat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) {
					
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
