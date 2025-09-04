package com.kh.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.dto.BoardDTO;
import com.kh.board.model.vo.Board;
import com.kh.common.JDBCTemplate;
import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.vo.Member;

public class BoardService {
	
	private Connection conn = null;
	
	public BoardService() {
		conn = JDBCTemplate.getConnection();
	}

	public int insertBoard(BoardDTO bd) {
		// 내가 입력한 값을 가지고
		// BOARD테이블에 한 행 INSERT 하기
		int result = 0;
		
		// 1. 값의 유효성 검증
		if("".equals(bd.getBoardTitle().trim())) {
			return result;
		}
		// 예)
		// 제목: 안녕하세요, 내용: 반갑습니다, 아이디: admin >> 아이디가 admin이 아닌 1(userNo)이 들어가야함
		// INSERT 하기전에 인증을 위해 SELECT해야함(DAO)
		// 2. 인증 / 인가
		Member member = new MemberDao().findById(conn, bd.getBoardWriter()); 
		
		if(member != null) {
			int userNo = member.getUserNo();
			Board board = new Board(0
								  , bd.getBoardTitle()
								  , bd.getBoardContent()
								  , String.valueOf(userNo)
								  , null
								  , null);
			result = new BoardDAO().insertBoard(conn, board);
		}
		
		// 3. 트랜잭션
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		
		// 4. 자원반납
		JDBCTemplate.close(conn);

		// 5. 값 반환
		return result;
	}

	public List<Board> selectBoardList() {
		
		List<Board> boards = new BoardDAO().selectBoardList(conn);
		
		new BoardDAO().outputHTML(conn);
		
		JDBCTemplate.close(conn);

		return boards;
	}

	public Board selectBoard(int boardNo) {

		Board board = null;
		
		// BOARDNO == 넘버시퀀스로 만듬
		// 0 이하 숫자(==유효하지않은값) 들어오면 DB접근할 필요 X
		if(boardNo > 0) {
			board =	new BoardDAO().selectBoard(conn, boardNo);
		}
		
		JDBCTemplate.close(conn);
		
		return board;
	}

	public int deleteBoard(int boardNo) {

		int result = 0;
		
		if(boardNo > 0) {
			result = new BoardDAO().deleteBoard(conn, boardNo);
		}
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
