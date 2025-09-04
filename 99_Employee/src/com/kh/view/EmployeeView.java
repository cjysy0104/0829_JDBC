package com.kh.view;

import java.util.List;
import java.util.Scanner;

import com.kh.controller.EmployeeController;
import com.kh.model.dto.EmployeeDto;
import com.kh.model.vo.Employee;

public class EmployeeView {

	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();

	public void mainMenu() {
		System.out.println("EMPLOYEE 검색 시스템");

		while (true) {
			System.out.println("1. 회원 전체 조회"); // (사번, 사원명, 급여, 부서명, 직급명)
			System.out.println("2. 부서별 회원 조회"); // 부서명을 입력받아 부서가 동일한 사원 조회
			System.out.println("3. 직급별 회원 조회"); // 급명을 입력받아 직급이 동일한 사원 조회
			System.out.println("4. 사원 상세 조회"); // 사번을 입력받아서 모든 컬럼 값 조회
			System.out.println("5. 현재 최고 급여 5명 조회");
			System.out.println("6. 현재 최저 급여 5명 조회");
			System.out.println("7. 사원 추가 기능");
			System.out.println("8. 사원 정보 수정");
			System.out.println("9. 사원 퇴사 기능");
			System.out.println("10. 프로그램 종료");

			System.out.println("선택할 메뉴 번호 입력");
			int menuNo = sc.nextInt();
			sc.nextLine();

			switch(menuNo) {
			case 1 : findAll(); break;
			case 2 : findByDept(); break;
			case 3 : findByJob(); break;
			case 4 : findById(); break;
			case 5 : findHighSalary(); break;
			case 6 : findLowSalary(); break;
			case 7 : save(); break;
			case 8 : updateInfo(); break;
			case 9 : break;
			case 10 : System.out.println("Bye~"); return;
			default : System.out.println("입력 잘못했는디"); break;
			}
		}
	}

	private void updateInfo() {
		System.out.println("사원 정보 수정 메뉴");
		System.out.println("================================");
		System.out.println("사번?");
		String empId =sc.nextLine();
		
		System.out.println("변경할 연봉?");
		int salary = sc.nextInt();
		sc.nextLine();
		System.out.println("변경할 부서코드?");
		String deptCode =sc.nextLine();
		System.out.println("변경할 직급코드?");
		String jobCode =sc.nextLine();
		
		int result = ec.updateInfo(empId, salary, deptCode, jobCode);
		
		if(result > 0) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
	}

	private void save() {
		System.out.println("사원 추가 메뉴");
		System.out.println("================================");
		System.out.println("사번?");
		String empId =sc.nextLine();
		System.out.println("이름?");
		String empName =sc.nextLine();
		System.out.println("주민등록번호?");
		String empNo =sc.nextLine();
		System.out.println("이메일?");
		String email =sc.nextLine();
		System.out.println("휴대폰?");
		String phone =sc.nextLine();
		System.out.println("부서코드?");
		String deptCode =sc.nextLine();
		System.out.println("직급코드?");
		String jobCode =sc.nextLine();
		System.out.println("연봉등급?");
		String salLevel =sc.nextLine();
		System.out.println("연봉?");
		int salary = sc.nextInt();
		sc.nextLine();
		System.out.println("보너스?");
		double bonus = sc.nextDouble();
		sc.nextLine();
		System.out.println("매니저코드?");
		String managerCode =sc.nextLine();
		
		
		int result = ec.save(empId, empName, empNo, email, phone, deptCode,
							 jobCode, salLevel, salary, bonus, managerCode);
		
		if(result > 0) {
			System.out.println("추가 성공");
		} else {
			System.out.println("추가 실패");
		}
	}

	private void findLowSalary() {
		System.out.println("최저 연봉 수령순 사원 5명 조회");
		List<Employee> emps = ec.findLowSalary();
		for (Employee employee : emps) {
			System.out.println(employee.toString());
		}
	}

	private void findHighSalary() {
		System.out.println("최고 연봉 수령순 사원 5명 조회");
		List<Employee> emps = ec.findHighSalary();
		for (Employee employee : emps) {
			System.out.println(employee.toString());
		}
	}

	private void findById() {
		System.out.println("사원 상세 조회");
		System.out.println("검색할 사원의 사번 입력 > ");
		String empId = sc.nextLine();
		EmployeeDto empDto = ec.findById(empId);
		System.out.println(empDto.toString());
		
	}

	private void findByJob() {
		System.out.println("직급별 회원 조회");
		System.out.println("검색할 직급 입력 > ");
		String job = sc.nextLine();
		List<Employee> emps = ec.findByJob(job);
		for (Employee employee : emps) {
			System.out.println(employee.toString());
		}
	}

	private void findByDept() {
		System.out.println("부서별 회원 조회");
		System.out.println("검색할 부서 입력 > ");
		String dept = sc.nextLine();
		List<Employee> emps = ec.findByDept(dept);
		for (Employee employee : emps) {
			System.out.println(employee.toString());
		}

	}

	private void findAll() {
		System.out.println("회원 전체 조회");
		List<Employee> emps = ec.findAll();
		for (Employee employee : emps) {
			System.out.println(employee.toString());
		}
	}
}
