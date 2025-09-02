package com.kh.hw2.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.hw2.statement.model.vo.Player;

public class PlayerDao {

	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@115.90.212.20:10000:XE";
	private final String USERNAME = "CJY19";
	private final String PASSWORD = "CJY191234";
	
	public int insertPlayer(int playerNo, String playerName, String teamCode,
							 int gamesPlayed, double average) {

		// 0
		Connection conn = null;
		Statement stat = null;
		int result = 0;
		
		String sql = "INSERT INTO TB_PLAYER_BATTER"
				+ "(PLAYER_NO, PLAYER_NAME, TEAM_CODE, GAMES_PLAYED, AVERAGE) VALUES ('"
				+ playerNo + "', '" + playerName + "', '" + teamCode + "', '" 
				+ gamesPlayed + "', '" + average + "')";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE",
											   "CJY19",
											   "CJY191234");
			
			stat = conn.createStatement();

			result = stat.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null) {

					stat.close();
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

	public List<Player> findAll() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rset = null;
		List<Player> players = new ArrayList<Player>();
		String sql = 
			"""
				SELECT 
				 	   PLAYER_NO
				 	 , PLAYER_NAME
				 	 , TEAM_CODE
				 	 , GAMES_PLAYED
				 	 , AVERAGE
				 	 , DEBUT_DATE
				 	 , RETIRE_DATE
				  FROM 
				  	   TB_PLAYER_BATTER
				 ORDER
				 	BY
				 	   DEBUT_DATE DESC
			  """;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stat = conn.createStatement();
			rset = stat.executeQuery(sql);
			while(rset.next()) {
				Player player = new Player();
				player.setPlayerNo(rset.getInt("PLAYER_NO"));
				player.setPlayerName(rset.getString("PLAYER_NAME"));
				player.setTeamCode(rset.getString("TEAM_CODE"));
				player.setGamesPlayed(rset.getInt("GAMES_PLAYED"));
				player.setAverage(rset.getDouble("AVERAGE"));
				player.setDebutDate(rset.getDate("DEBUT_DATE"));
				player.setRetireDate(rset.getDate("RETIRE_DATE"));
				
				players.add(player);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players;
	}

	public Player findByNO(int no) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rset = null;
		Player player = null;
		String sql =
				"""
				SELECT 
				 	   PLAYER_NO
				 	 , PLAYER_NAME
				 	 , TEAM_CODE
				 	 , GAMES_PLAYED
				 	 , AVERAGE
				 	 , DEBUT_DATE
				 	 , RETIRE_DATE
				  FROM 
				  	   TB_PLAYER_BATTER
				 WHERE 
				  	   PLAYER_NO = '""" + no +"'";
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stat = conn.createStatement();
			rset = stat.executeQuery(sql);
			
			if(rset.next()) {
				 player = new Player(rset.getInt("PLAYER_NO")
										, rset.getString("PLAYER_NAME")
										, rset.getString("TEAM_CODE")
										, rset.getInt("GAMES_PLAYED")
										, rset.getDouble("AVERAGE")
										, rset.getDate("DEBUT_DATE")
										, rset.getDate("RETIRE_DATE"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		
		return player;
	}

	public List<Player> findByKeyword(String keyword) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rset = null;
		List<Player> players = new ArrayList<Player>();
		String sql = 
				"""
					SELECT 
						   PLAYER_NO
				 	 	 , PLAYER_NAME
				 	 	 , TEAM_CODE
				 	 	 , GAMES_PLAYED
				 	 	 , AVERAGE
				 	 	 , DEBUT_DATE
				 	 	 , RETIRE_DATE
				 	  FROM 
				 	  	   TB_PLAYER_BATTER
				 	 WHERE 
				 	 	   PLAYER_NAME LIKE '%""" + keyword + "%'";
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stat = conn.createStatement();
			rset = stat.executeQuery(sql);
			while(rset.next()) {
				Player player = new Player();
				player.setPlayerNo(rset.getInt("PLAYER_NO"));
				player.setPlayerName(rset.getString("PLAYER_NAME"));
				player.setTeamCode(rset.getString("TEAM_CODE"));
				player.setGamesPlayed(rset.getInt("GAMES_PLAYED"));
				player.setAverage(rset.getDouble("AVERAGE"));
				player.setDebutDate(rset.getDate("DEBUT_DATE"));
				player.setRetireDate(rset.getDate("RETIRE_DATE"));
				
				players.add(player);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players;		
		
	}

}
