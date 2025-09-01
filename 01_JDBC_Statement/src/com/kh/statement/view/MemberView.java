package com.kh.statement.view;

import java.util.List;
import java.util.Scanner;

import com.kh.statement.controller.MemberController;
import com.kh.statement.model.vo.Member;


/**
 * MemberView 클래스는 JDBC 실습을 위해 생성하였으며, 사용자에게 입력 및 출력을 수행하는 메서드를 제공합니다. 주석은 alt +
 * shift + j
 * 
 * @author 최준영
 * @version 0.1
 * @date 2025-09-01
 */
public class MemberView {

	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();

	/**
	 * 프로그램 구동 시 사용자에게 메인화면을 출력해주는 메서드입니다.
	 */
	public void mainMenu() {
		while (true) {
			System.out.println("----회원 관리 프로그램----");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디로 조회");
			System.out.println("4. 회원 이름 키워드로 조회");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("메뉴를 선택해주세요 > ");
			int menuNo = sc.nextInt();
			sc.nextLine();
			
			switch (menuNo) {
			case 1 : save(); break;
			case 2 : findAll(); break;
			case 3 : findById(); break;
			case 4 : findByKeyword(); break;
			case 5 :break;
			case 6 :break;
			case 9 : System.out.println("프로그램을 종료합니다.");break;
			default:System.out.println("잘못된 메뉴 선택입니다");;
			}
		}
	}
	/**
	 * Member 테이블에 INSERT할 값을 사용자가 입력받는 화면을 출력해주는 메서드
	 * 
	 * 컬럼에 INSERT할 값들을 모두 입력받은 후 입력받은 값 컨트롤러로 전달
	 */
	private void save() {
		System.out.println(" ---회원 추가---");
		
		System.out.println("ID?");
		String userId = sc.nextLine();
		System.out.println("PW?");
		String userPwd = sc.nextLine();
		System.out.println("Name?");
		String userName = sc.nextLine();
		System.out.println("Email?");
		String email = sc.nextLine();
		
		// view 할 일 일단 끗
		int result = mc.save(userId, userPwd, userName, email);
		if(result > 0) {
			System.out.println("회원가입에 성공했습니다.");
		} else {
			System.out.println("회원가입에 실패했습니다.");
		}
	}
	/**
	 * 회원 전체 조회 요청 시 Member 테이블에 존재하는 모든 회원의 정보를 출력하는 메서드
	 */
	private void findAll() {

		System.out.println("\n회원 전체 조회");
		// Controller에게 회원들의 데이터값 요청
		List<Member> members = mc.findAll();
		
		// 뷰 2절
		System.out.println("\n조회된 총 회원수는 " + members.size() +"명입니다.");
		if(members.isEmpty()) {
			System.out.println("조회결과가 존재하지않습니다.");
		} else {
			for(Member member :members) {
				System.out.println(member.toString());
			}
			
		}
	}
	
	/**
	 * 사용자로부터 회원의 아이디를 입력받아서
	 * Member테이블로부터 아이디값을 비교해서 조회한 뒤
	 * 동일한 아이디값을 가진 행의 데이터를 출력해주는 메서드
	 */
	private void findById() {
		
		System.out.println("\n아이디로 검색 서비스입니다.");
		System.out.println("\n아이디를 입력해주세요. > ");
		String userId = sc.nextLine();
		
		Member member = mc.findById(userId);
		if(member != null) {
			System.out.println(userId+"님의 검색결과");
			System.out.println("================================");
			System.out.println(member.toString());
		} else {
			System.out.println("업어");
		}
	}
	/**
	 * 사용자로부터 회원의 이름 키워드를 입력받아서
	 * Member테이블로부터 키워드가 포함된 이름을 조회한 뒤
	 * 키워드가 포함된 이름을 가진 행의 데이터들을 출력해주는 메서드
	 */
	private void findByKeyword() {
		
		System.out.println("\n회원 이름 키워드로 검색");
		System.out.println("검색하고자 하는 키워드를 입력해주세요 > ");
		String keyword = sc.nextLine();
		
		List<Member> members = mc.findByKeyword(keyword);
		if(members.isEmpty()) {
			System.out.println("업는디");
		} else {
			System.out.println("검색된 결과는 총 " + members.size() + "건 입니다.");
			System.out.println("================================");
			for (Member member : members) {
				System.out.println(member.getUserName() + "의 정보");
				System.out.println(member.toString());
				System.out.println("================================");
			}
		}
	}
}
