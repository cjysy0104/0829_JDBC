package com.kh.statement.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.statement.model.dto.PasswordDTO;
import com.kh.statement.model.vo.Member;

public class MemberDao {

	public int save(SqlSession session, Member member) {
		
		/*
		 * int result = 0;
		 * PreparedStatement stat = null;
		 * String sql = prop.getProperty("save");
		 * try{
		 * 
		 * 	pstat = conn.prepareStatement(sql);
		 * pstat.setString(1,member.getUserId());
		 * pstat.setString(2,member.getUserPwd());
		 * ...
		 * result = pstat.executeUpdate();
		 * } catch(IOException e){
		 * } finally{
		 * 		JDBCTemplate.close(pstat);
		 * }
		 * return result;
		 * 
		 * 
		 * Sqlsession이 제공하는 메서드를 통해 SQL문을 찾아서 실행하고 결과도 받아볼 수 있음
		 * sqlSession.Sql문메서드("메타페일의 namesapce.)
		 */
		
		return session.insert("memberMapper.save", member);
	}

	public List<Member> findAll(SqlSession session) {
		// 조회결과가 존재하지 않다면 빈 리스트 반환
		return session.selectList("memberMapper.findAll");
	}

	public Member findById(SqlSession session, String userId) {
		// 조회결과가 존재하지 않다면 null반환
		return session.selectOne("memberMapper.findById", userId);
	}

	public List<Member> findByKeyword(SqlSession session, String keyword) {
		
		return session.selectList("memberMapper.findByKeyword", keyword);
	}

	public int update(SqlSession session, PasswordDTO pd) {

		return session.update("memberMapper.update", pd);
	}

	public int delete(SqlSession session, Member member) {
		
		return session.delete("memberMapeer.delete", member);
	}

}