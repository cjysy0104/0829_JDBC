package com.kh.hw3.statement.controller;

import java.util.List;

import com.kh.hw2.statement.model.dao.PlayerDao;
import com.kh.hw2.statement.model.vo.Player;

public class PlayerController {

	private PlayerDao pd = new PlayerDao();

	public int insertPlayer(int playerNo, String playerName, String teamCode, int gamesPlayed, double average) {

		int result = pd.insertPlayer(playerNo, playerName, teamCode, gamesPlayed, average);
		return result;
	}

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
}
