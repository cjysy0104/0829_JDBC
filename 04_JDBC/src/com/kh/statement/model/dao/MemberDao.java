package com.kh.statement.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberDao {

	private Properties prop = new Properties();
	
	public MemberDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/member-mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int save(Connection conn, Member member) {

		PreparedStatement pstat = null;
		int result = 0;
		
//		private Properties prop = new Properties();
//		try {
//			prop.loadFromXML(new FileInputStream("resources/member-mapper.xml"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		String sql = prop.getProperty("save");
		
		
		try {
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, member.getUserId());
			pstat.setString(2, member.getUserPwd());
			pstat.setString(3, member.getUserName());
			pstat.setString(4, member.getEmail());

			result = pstat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstat);
		}
		return result;
	}

	public List<Member> findAll(Connection conn) {

		// 0) 필요한 변수 선언
		List<Member> members = new ArrayList<Member>();
		PreparedStatement pstat = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("findAll");

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
		
		String sql = prop.getProperty("findById");
		
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
		String sql = prop.getProperty("findByKeyword");
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
		String sql = prop.getProperty("update");
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
		String sql = prop.getProperty("delete");
		
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