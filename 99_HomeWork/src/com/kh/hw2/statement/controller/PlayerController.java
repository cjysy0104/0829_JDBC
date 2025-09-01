package com.kh.hw2.statement.controller;

import com.kh.hw2.statement.model.dao.PlayerDao;

public class PlayerController {

	private PlayerDao pd = new PlayerDao();

	public int insertPlayer(int playerNo, String playerName, String teamCode, int gamesPlayed, double average, String debutDate) {

		int result = pd.insertPlayer(playerNo, playerName, teamCode, gamesPlayed, average, debutDate);
		return result;
	}
}
