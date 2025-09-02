package com.kh.hw4.statement.view;

import java.util.List;
import java.util.Scanner;

import com.kh.hw4.statement.controller.PlayerController;
import com.kh.hw4.statement.model.vo.Player;

public class PlayerView {

	private Scanner sc = new Scanner(System.in);
	private PlayerController pc = new PlayerController();

	public void mainMenu() {

		while (true) {
			System.out.println("----- 축구선수 DB -----");
			System.out.println("1. 선수 정보 기입");
			System.out.println("2. 전체 선수 기록 조회");
			System.out.println("3. 선수 번호로 검색");
			System.out.println("4. 선수 키워드로 검색");

			int menuNo = sc.nextInt();
			sc.nextLine();

			switch (menuNo) {
			case 1:
				insertPlayer();
				break;
			case 2:
				findAll();
				break;
			case 3:
				findByNo();
				break;
			case 4:
				findByKeyword();
				break;
			default:
				System.out.println("잘못입력함");
				break;
			}

		}

	}

	private void findByKeyword() {
		System.out.println("키워드 검색");
		System.out.println("================================");
		String keyword = sc.nextLine();
		List<Player> players = pc.findByKeyword(keyword);
		if (players.isEmpty()) {
			System.out.println("없어");
		} else {
			for (Player player : players) {
				System.out.println(player.toString());
			}
		}
	}

	private void findByNo() {
		System.out.println("번호 검색");
		System.out.println("================================");
		int no = sc.nextInt();
		sc.nextLine();
		Player player = pc.findByNo(no);
		System.out.println(player.toString());
	}

	private void findAll() {
		System.out.println("전체 선수 조회");
		System.out.println("================================");
		List<Player> players = pc.findAll();

		if (players.isEmpty()) {
			System.out.println("없어");
		} else {
			for (Player player : players) {
				System.out.println(player.toString());
			}
		}

	}

	private void insertPlayer() {
		System.out.println("선수 데이터 기입메뉴");
		System.out.println("================================");

		System.out.println("풀네임?");
		String fullName = sc.nextLine();

		System.out.println("포지션?");
		String position = sc.nextLine();

		System.out.println("국적?");
		String nation = sc.nextLine();

		System.out.println("키?");
		double height = sc.nextDouble();
		sc.nextLine();

		System.out.println("몸무게?");
		double weight = sc.nextDouble();
		sc.nextLine();

		System.out.println("현재 소속팀?");
		String curTeam = sc.nextLine();

		int result = pc.insertPlayer(fullName, position, nation, height, weight, curTeam);

		if (result > 0) {
			System.out.println("기입 완료");
		} else {
			System.out.println("기입 실패");
		}
	}
}
