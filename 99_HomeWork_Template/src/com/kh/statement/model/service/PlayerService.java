package com.kh.statement.model.service;

import java.sql.Connection;
import java.util.List;

import static com.kh.common.JDBCTemplate.getConnection;

import com.kh.common.JDBCTemplate;
import com.kh.statement.model.dao.PlayerDAO;
import com.kh.statement.model.vo.Player;

/*
 * !!!!! 메서드마다 conn 반납해!!!!
 */
public class PlayerService {

	private Connection conn = null;
	
	public PlayerService() {
		this.conn = getConnection();
	}
	
	
	public List<Player> findByKeyword(String keyword) {
		List<Player> players = new PlayerDAO().findByKeyword(conn, keyword);
		JDBCTemplate.close(conn);
		return players;
	}

}
