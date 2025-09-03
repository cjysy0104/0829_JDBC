package com.kh.board.controller;

import com.kh.board.model.dto.BoardDTO;
import com.kh.board.model.service.BoardService;

public class BoardController {

	public int insertBoard(BoardDTO bd) {
		
		int result = new BoardService().insertBoard(bd);
		return result;
	}

}
