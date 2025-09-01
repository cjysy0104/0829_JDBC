package com.kh.hw2.view;

import java.sql.Date;
import java.util.Scanner;

import com.kh.hw2.statement.controller.PlayerController;

public class PlayerView {

	private Scanner sc = new Scanner(System.in);
	private PlayerController pc = new PlayerController();

	public void mainMenu() {

		while (true) {
			System.out.println("---야구선수 기록실 ---");
			System.out.println("1. 선수 정보 기입");
			System.out.println("2. 전체 선수 기록 조회");
			System.out.println("3. 선수 번호로 검색");
			System.out.println("4. 선수 키워드로 검색");

			int menuNo = sc.nextInt();
			sc.nextLine();

			switch (menuNo) {
			case 1: insertPlayer();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				System.out.println("잘못입력함");
				break;
			}

		}

	}

	private void insertPlayer() {
		System.out.println("선수 데이터 기입메뉴");
		System.out.println("================================");
		System.out.println("번호?");
		int playerNo = sc.nextInt();
		sc.nextLine();
		System.out.println("이름?");
		String playerName = sc.nextLine();
		System.out.println("팀코드?");
		String teamCode = sc.nextLine();
		System.out.println("경기수?");
		int gamesPlayed = sc.nextInt();
		sc.nextLine();
		System.out.println("타율?");
		double average = sc.nextDouble();
		sc.nextLine();
		System.out.println("데뷔일?");
		String debutDate = sc.nextLine();
		
		int result = pc.insertPlayer(playerNo, playerName, teamCode, gamesPlayed, average, debutDate);
		if(result > 0) {
			System.out.println("기입 완료");
		} else {
			System.out.println("기입 실패");
		}
	}
}
