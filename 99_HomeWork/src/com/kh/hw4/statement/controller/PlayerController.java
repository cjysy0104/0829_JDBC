package com.kh.hw4.statement.controller;

import java.util.List;

import com.kh.hw4.statement.model.dao.PlayerDao;
import com.kh.hw4.statement.model.vo.Player;

public class PlayerController {

	private PlayerDao pd = new PlayerDao();

	public List<Player> findAll() {

		List<Player> players =  pd.findAll();
		return players;
	}

	public Player findByNo(int no) {
		Player player = pd.findByNO(no);
		return player;
	}

	public List<Player> findByKeyword(String keyword) {
		
		List<Player> players = pd.findByKeyword(keyword);
		return players;
	}

	public int insertPlayer(String fullName, String position, String nation,
			double height, double weight, String curTeam) {
		int result = pd.insertPlayer(fullName, position, nation, height, weight, curTeam);
		return result;
	}
}
