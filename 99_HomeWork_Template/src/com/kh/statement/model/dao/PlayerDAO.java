package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.dto.PlayerDTO;
import com.kh.statement.model.vo.Player;

public class PlayerDAO {

	public List<Player> findByKeyword(Connection conn, String keyword) {
		PreparedStatement pstat = null;
		ResultSet rset = null;
		List<Player> players = new ArrayList();

		String sql = """
						SELECT 
							   PLAYER_ID
							 , FULL_NAME
							 , POSITION
							 , NATION
							 , HEIGHT
							 , WEIGHT
							 , CUR_TEAM
							 , ENROLLDATE
						  FROM 
						  	   FOOTBALL_PLAYER
 						 WHERE 
						 	   FULL_NAME LIKE ?
						 ORDER
						 	BY 
						 	   ENROLLDATE DESC
				""";

		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, "%" + keyword + "%");
			rset = pstat.executeQuery();

			while (rset.next()) {
				players.add(new Player(rset.getInt("PLAYER_ID")
									 , rset.getString("FULL_NAME")
									 , rset.getString("POSITION")
									 , rset.getString("NATION")
									 , rset.getDouble("HEIGHT")
									 , rset.getDouble("WEIGHT")
									 , rset.getString("CUR_TEAM")
									 , rset.getDate("ENROLLDATE")
				));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstat);
		}

		return players;
	}

	public Player findByNo(Connection conn, int no) {
		PreparedStatement pstat = null;
		ResultSet rset = null;
		Player player = null;

		String sql = """
						SELECT 
							   PLAYER_ID
							 , FULL_NAME
							 , POSITION
							 , NATION
							 , HEIGHT
							 , WEIGHT
							 , CUR_TEAM
							 , ENROLLDATE
						  FROM 
						  	   FOOTBALL_PLAYER
						 WHERE 
						 	   PLAYER_ID = ?
					 """;
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, no);
			rset = pstat.executeQuery();

			if (rset.next()) {
				player = new Player(rset.getInt("PLAYER_ID")
						 				 , rset.getString("FULL_NAME")
						 				 , rset.getString("POSITION")
						 				 , rset.getString("NATION")
						 				 , rset.getDouble("HEIGHT")
						 				 , rset.getDouble("WEIGHT")
						 				 , rset.getString("CUR_TEAM")
						 				 , rset.getDate("ENROLLDATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstat);
		}
		
		
		return player;
	}

	public List<Player> findAll(Connection conn) {
		PreparedStatement pstat = null;
		ResultSet rset = null;
		List<Player> players = new ArrayList();
		String sql = """
						SELECT 
							   PLAYER_ID
							 , FULL_NAME
							 , POSITION
							 , NATION
							 , HEIGHT
							 , WEIGHT
							 , CUR_TEAM
							 , ENROLLDATE
						  FROM 
						  	   FOOTBALL_PLAYER
						 ORDER
						 	BY 
						 	   ENROLLDATE DESC
					 """;
		try {
			pstat = conn.prepareStatement(sql);
			rset = pstat.executeQuery();

			while (rset.next()) {
				players.add(new Player(rset.getInt("PLAYER_ID")
									 , rset.getString("FULL_NAME")
									 , rset.getString("POSITION")
									 , rset.getString("NATION")
									 , rset.getDouble("HEIGHT")
									 , rset.getDouble("WEIGHT")
									 , rset.getString("CUR_TEAM")
									 , rset.getDate("ENROLLDATE")
				));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstat);
		}

		return players;
	}

	public int insertPlayer(Connection conn, PlayerDTO player) {
		PreparedStatement pstat = null;
		int result = 0;
		
		String sql = """
						INSERT 
						  INTO 
						  	   FOOTBALL_PLAYER
						VALUES 
							   (
							   SEQ_PID.NEXTVAL
							 , ?
							 , ?
							 , ?
							 , ?
							 , ?
							 , ?
							 , SYSDATE
							   )
					 """;
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, player.getFullName());
			pstat.setString(2, player.getPosition());
			pstat.setString(3, player.getNation());
			pstat.setDouble(4, player.getHeight());
			pstat.setDouble(5, player.getWeight());
			pstat.setString(6, player.getCurTeam());
			
			result = pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstat);
		}
		
		return result;
	}
	
	

}
