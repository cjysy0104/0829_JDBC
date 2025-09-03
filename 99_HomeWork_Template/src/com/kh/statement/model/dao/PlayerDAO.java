package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.kh.statement.model.vo.Player;

public class PlayerDAO {

	public List<Player> findByKeyword(Connection conn, String keyword) {
		PreparedStatement pstat = null;
		List<Player> players = new ArrayList();
		Player player = null;
		// TODO
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
					 """;
		

		return null;
	}

}
