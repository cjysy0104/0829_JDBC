package com.kh.board.view;

import java.util.List;
import java.util.Scanner;

import com.kh.board.controller.BoardController;
import com.kh.board.model.dto.BoardDTO;
import com.kh.board.model.vo.Board;

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
			selectBoardList();
			System.out.println();
			
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
			case 3 : deleteBoard(); break;
			case 9 : System.out.println("안녕히 가세요"); return;
			default : System.out.println("잘못된 번호입력");
			}

		}
	}

	private void deleteBoard() {
		System.out.println("게시글 삭제 서비스입니다.");
		System.out.println("삭제할 게시글 번호 입력 > ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		int result = bc.deleteBoard(boardNo);
		if(result > 0) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
	}

	private void selectBoard() {

		System.out.println("게시글 상세조회 서비스입니다.");
		System.out.println("조회할 게시글 번호 입력 > ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		Board board = bc.selectBoard(boardNo);
		
		if(board != null) {
			System.out.print("NO: " + board.getBoardNo());
			System.out.print(" | TITLE: " + board.getBoardTitle());
			System.out.print(" | USER: " + board.getBoardWriter());
			System.out.println("| CREATE_DATE: " + board.getCreateDate());
			System.out.println("================================================================");
			System.out.println(board.getBoardContent());
		} else {
			System.out.println("존재하지 않는 게시글 번호입니다.");
		}
		
		while(true) {
			System.out.println("목록으로 돌아가시려면 exit을 입력해주세요.");
			String exit = sc.nextLine();
			if("exit".equals(exit)) {
				return;
			}
		}
	}

	private void selectBoardList() {

		System.out.println("전체 글 보기");
		System.out.println("================================");
		List<Board> boards =  bc.selectBoardList();
		
		if(!boards.isEmpty()) {
			boards.stream().map(b -> "\n게시글 번호: " + b.getBoardNo()
									+"\t게시글 제목: " + b.getBoardTitle()
									+"\t게시글 작성자: "+ b.getBoardWriter()).forEach(System.out::print);
		} else {
			System.out.println("게시글이 존재하지 않습니다.");
		}
		
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
