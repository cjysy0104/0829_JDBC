package com.kh.hw4.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.hw4.statement.model.vo.Player;

public class PlayerDao {

	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@115.90.212.20:10000:XE";
	private final String USERNAME = "CJY19";
	private final String PASSWORD = "CJY191234";

	
	public List<Player> findAll() {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rset = null;
		List<Player> players = new ArrayList<Player>();
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
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstat = conn.prepareStatement(sql);
			rset = pstat.executeQuery();
			while (rset.next()) {
				Player player = new Player();
				player.setPlayerId(rset.getInt("PLAYER_ID"));
				player.setFullName(rset.getString("FULL_NAME"));
				player.setPosition(rset.getString("POSITION"));
				player.setNation(rset.getString("NATION"));
				player.setHeight(rset.getDouble("HEIGHT"));
				player.setWeight(rset.getDouble("WEIGHT"));
				player.setCurTeam(rset.getString("CUR_TEAM"));
				player.setEnrollDate(rset.getDate("ENROLLDATE"));

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
		PreparedStatement pstat = null;
		ResultSet rset = null;
		Player player = new Player();
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
				 ORDER
				 	BY
				 	   ENROLLDATE DESC
				 """;

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, no);
			rset = pstat.executeQuery();

			if (rset.next()) {
				player.setPlayerId(rset.getInt("PLAYER_ID"));
				player.setFullName(rset.getString("FULL_NAME"));
				player.setPosition(rset.getString("POSITION"));
				player.setNation(rset.getString("NATION"));
				player.setHeight(rset.getDouble("HEIGHT"));
				player.setWeight(rset.getDouble("WEIGHT"));
				player.setCurTeam(rset.getString("CUR_TEAM"));
				player.setEnrollDate(rset.getDate("ENROLLDATE"));
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
		PreparedStatement pstat = null;
		ResultSet rset = null;
		List<Player> players = new ArrayList<Player>();
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
					 	   FULL_NAME LIKE ?""";
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, "%" + keyword + "%");
			rset = pstat.executeQuery();
			while (rset.next()) {
				Player player = new Player();
				player.setPlayerId(rset.getInt("PLAYER_ID"));
				player.setFullName(rset.getString("FULL_NAME"));
				player.setPosition(rset.getString("POSITION"));
				player.setNation(rset.getString("NATION"));
				player.setHeight(rset.getDouble("HEIGHT"));
				player.setWeight(rset.getDouble("WEIGHT"));
				player.setCurTeam(rset.getString("CUR_TEAM"));
				player.setEnrollDate(rset.getDate("ENROLLDATE"));
				players.add(player);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players;

	}

	public int insertPlayer(String fullName, String position, String nation, double height, double weight,
			String curTeam) {

		Connection conn = null;
		PreparedStatement pstat = null;
		int result = 0;
		String sql = """
				INSERT
				  INTO
				  	   FOOTBALL_PLAYER
				VALUES
					   (
					   SEQ_USERNO.NEXTVAL
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
			Class.forName(DRIVER);

			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, fullName);
			pstat.setString(2, position);
			pstat.setString(3, nation);
			pstat.setDouble(4, height);
			pstat.setDouble(5, weight);
			pstat.setString(6, curTeam);

			result = pstat.executeUpdate();

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

}
