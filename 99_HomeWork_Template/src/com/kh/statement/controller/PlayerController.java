package com.kh.statement.controller;

import java.util.List;

import com.kh.statement.model.service.PlayerService;
import com.kh.statement.model.vo.Player;

public class PlayerController {

	public List<Player> findByKeyword(String keyword) {
		List<Player> players = new PlayerService().findByKeyword(keyword);
		return players;
	}

}
