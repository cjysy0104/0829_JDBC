package com.kh.statement.controller;

import java.util.List;

import com.kh.statement.model.dto.PlayerDTO;
import com.kh.statement.model.service.PlayerService;
import com.kh.statement.model.vo.Player;

public class PlayerController {

	public List<Player> findByKeyword(String keyword) {
		List<Player> players = new PlayerService().findByKeyword(keyword);
		return players;
	}

	public Player findByNo(int no) {
		Player player = new PlayerService().findByNo(no);
		return player;
	}

	public List<Player> findAll() {
		List<Player> players = new PlayerService().findAll();
		return players;
	}

	public int insertPlayer(String fullName, String position, String nation, double height, double weight,
			String curTeam) {
		PlayerDTO player = new PlayerDTO(fullName, position, nation, height, weight, curTeam);
		int result = new PlayerService().insertPlayer(player);
		return result;
	}

}
