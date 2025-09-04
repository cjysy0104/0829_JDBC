package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberDao {

	public int save(Connection conn, Member member) {

		// 0) 필요한 변수 세팅
		PreparedStatement pstat = null;
		int result = 0;
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
		// 1) Driver등록: Main에서 실행
		// 2) Connection: Template에서 받아옴
		try {
			// 3_1) PreparedStatement객체 생성
			pstat = conn.prepareStatement(sql);

			// 3_2) 미완성된 SQL문: 바인딩할 값 전달
			pstat.setString(1, member.getUserId());
			pstat.setString(2, member.getUserPwd());
			pstat.setString(3, member.getUserName());
			pstat.setString(4, member.getEmail());

			// 4, 5) DB에 완성된 SQL문 실행 및 결과 받기
			result = pstat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7_1) 할 일이 다 끝난 객체: PreparedStatement 객체만 반납
			JDBCTemplate.close(pstat);
		}
		// 8) 결과반환
		return result;
	}

	public List<Member> findAll(Connection conn) {

		// 0) 필요한 변수 선언
		List<Member> members = new ArrayList<Member>();
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
					 ORDER
						BY
				   		   ENROLLDATE ASC
				""";

		try {
			// 3_1) PreparedStatement 객체 생성(sql문 인자로 전달)
			pstat = conn.prepareStatement(sql);

			// 4, 5) SQL(SELECT)을 실행 후 결과(ResultSet)받기
			rset = pstat.executeQuery();

			// 6) 조회결과 여부 판단
			// => rset.next() 컬럼값을 객체필드에 매핑
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 자원반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstat);
		}

		// 8) 결과반환
		return members;
	}

	public Member findById(Connection conn, String userId) {
		
		Member member = null;
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
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, userId);
			rset = pstat.executeQuery();
			
			if(rset.next()) {
				member = new Member(rset.getInt("USERNO")
								  , rset.getString("USERID")
								  , rset.getString("USERPWD")
								  , rset.getString("USERNAME")
								  , rset.getString("EMAIL")
								  , rset.getDate("ENROLLDATE"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstat);
		}

		return member;
	}

	public List<Member> findByKeyword(Connection conn, String keyword) {

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
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, "%" + keyword + "%");
			rset = pstat.executeQuery();

			while (rset.next()) {

				members.add(new Member(rset.getInt("USERNO")
					 				 , rset.getString("USERID")
					 				 , rset.getString("USERPWD")
					 				 , rset.getString("USERNAME")
					 				 , rset.getString("EMAIL")
					 				 , rset.getDate("ENROLLDATE")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstat);
		}

		return members;
	}

	public int update(Connection conn, PasswordDTO pd) {

		PreparedStatement pstat = null;
		int result = 0;
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
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pd.getNewPwd());
			pstat.setString(2, pd.getUserId());
			pstat.setString(3, pd.getUserPwd());
			
			result = pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstat);
		}
		
		return result;
	}

	public int delete(Connection conn, Member member) {

		PreparedStatement pstat = null;
		int result = 0;
		String sql = """
						DELETE
					   		   MEMBER
				 		 WHERE
				 	   		   USERNO = (
				 	   		   			SELECT
				 	   						   USERNO
				 	   			   		  FROM
				 	   			   			   MEMBER
				 	   			  		 WHERE
				 	   			  			   USERID = ?
				 	   			    	   AND
				 	   			    		   USERPWD = ?
				 	   			    	 	   )
					 """;
		
		try {
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, member.getUserId());
			pstat.setString(2, member.getUserPwd());
			
			result = pstat.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstat);
		}
		
		return result;
	}

}