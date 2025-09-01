package com.kh.hw2.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerDao {

	public int insertPlayer(int playerNo, String playerName, String teamCode,
							 int gamesPlayed, double average, String debutDate) {

		// 0
		Connection conn = null;
		Statement stat = null;
		int result = 0;
		
		String sql = "INSERT INTO TB_PLAYER_BATTER VALUES ("
				+ playerNo + ", " + playerName + ", " + teamCode + ", " 
				+ gamesPlayed + ", " + average + ", " + debutDate + ")";
		
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

}
