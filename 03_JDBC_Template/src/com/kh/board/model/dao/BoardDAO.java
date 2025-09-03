package com.kh.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.board.model.vo.Board;
import com.kh.common.JDBCTemplate;

public class BoardDAO {

	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstat = null;
		int result = 0;
		String sql = """
				INSERT
				  INTO
				  	   BOARD
				  	   (
				  	   BOARD_NO
				  	 , BOARD_TITLE
				  	 , BOARD_CONTENT
				  	 , BOARD_WRITER
				  	   )
				VALUES
					   (
					   SEQ_BNO.NEXTVAL
					 , ?
					 , ?
					 , ?
					   )
				""";

		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, board.getBoardTitle());
			pstat.setString(2, board.getBoardContent());
			pstat.setInt(3, Integer.parseInt(board.getBoardWriter()));
			result = pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstat);
		}
		return result;
	}

}
