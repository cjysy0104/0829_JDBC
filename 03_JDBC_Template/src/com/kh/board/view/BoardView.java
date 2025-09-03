package com.kh.board.view;

import java.util.Scanner;

import com.kh.board.controller.BoardController;
import com.kh.board.model.dto.BoardDTO;

public class BoardView {
	/*
	 * SELECT / INSERT / UPDATE / DELETE
	 * 
	 *  예시) BOARD 관련
	 *  
	 *  - Style 1.
	 *  INSERT: insertBoard()
	 *  UPDATE: updateBoard()
	 *  DELETE: deleteBoard()
	 *  SELECT(다수행): selectBoardList()
	 *  SELECT(단일행): selectBoard()
	 * ---------------------------------
	 * 	- Style 2.
	 *  INSERT: save()
	 *  UPDATE: update()
	 *  DELETE: deleteByXXX()
	 *  SELECT(다수행): findAll()
	 *  SELECT(단일행): findByXXX()
	 * ---------------------------------
	 */

	private Scanner sc = new Scanner(System.in);
	private BoardController bc = new BoardController();

	public void mainMenu() {

		System.out.println("""
				⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄
				⠄⠄⠄⠄⠄⠄⢰⣷⡄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄
				⠄⠄⠄⠄⣠⣾⣿⣿⣷⣦⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄
				⠄⠄⠄⣠⣿⣿⣿⣿⣿⣿⣇⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄
				⠄⠄⠄⠄⠛⠿⣿⣿⣿⣿⣿⣆⠄⠄⠄⠄⠄⣴⣿⣿⣆⠄⠄⠄
				⠄⠄⠄⠄⠄⣰⣿⣿⣿⣿⣿⣿⣷⣄⠄⠄⠄⣿⣿⠛⠉⠄⠄⠄
				⠄⠄⠄⠄⠄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠄⠘⣿⡆⠄⠄⠄⠄
				⠄⠄⠄⠄⠄⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠸⣿⡀⠄⠄⠄
				⠄⠄⠄⠄⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠄⣿⡇⠄⠄⠄
				⠄⠄⠄⠄⠄⠄⢸⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⡇⢀⣿⠇⠄⠄⠄
				⠄⠄⠄⠄⠄⢀⣸⡿⢁⣘⣿⣿⣿⣿⣿⣿⣿⣇⣼⠋⠄⠄⠄⠄
				⠄⠄⠄⠄⠄⠻⠿⠓⠿⠿⠿⠿⠿⠿⠿⠿⠿⠛⠁⠄⠄⠄⠄⠄
				⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄
										""");

		while (true) {
			System.out.println("게시판 서비스입니다!");
			// 전체 게시글 목록 조회

			System.out.println("================================");
			System.out.println("1. 게시글 상세조회");
			System.out.println("2. 게시글 작성하기");
			System.out.println("3. 게시글 삭제하기");
			System.out.println("9. 회원 메뉴로 돌아가기");
			System.out.println("메뉴를 선택해주세요 > ");
			int menuNo = sc.nextInt();
			sc.nextLine();

			switch(menuNo) {
			case 1 : selectBoard(); break;
			case 2 : insertBoard(); break;
			case 3 : break;
			case 9 : System.out.println("안녕히 가세요"); return;
			default : System.out.println("잘못된 번호입력");
			}

		}
	}

	private void selectBoard() {
		System.out.println("게시글 상세 조회");
		
		
	}

	private void insertBoard() {

		System.out.println("게시글 작성 서비스입니다.");

		System.out.println("아이디를 입력해주세요 > ");
		String userId = sc.nextLine();

		System.out.println("제목을 입력해주세요 > ");
		String boardTitle = sc.nextLine();

		System.out.println("본문을 입력해주세요 > ");
		String boardContent = sc.nextLine();

		// 뷰 1절 끝!
		int result = bc.insertBoard(new BoardDTO(boardTitle, boardContent, userId));

		if (result > 0) {
			System.out.println("작성 완료");
		} else {
			System.out.println("작성 실패");
		}
		System.out.println();
		System.out.println();
	}
}
