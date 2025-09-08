package com.kh.controller;

import java.util.List;

import com.kh.model.dto.EmployeeDto;
import com.kh.model.dto.EmployeeInfoDto;
import com.kh.model.service.EmployeeService;
import com.kh.model.vo.Employee;

public class EmployeeController {

	public List<Employee> findAll() {

		List<Employee> emps = new EmployeeService().findAll();
		return emps;
	}

	public List<Employee> findByDept(String dept) {
		
		List<Employee> emps = new EmployeeService().findByDept(dept);
		return emps;
	}

	public List<Employee> findByJob(String job) {
		List<Employee> emps = new EmployeeService().findByJob(job);
		return emps;
	}

	public EmployeeDto findById(String empId) {
		EmployeeDto empDto = new EmployeeService().findById(empId);
		return empDto;
	}

	public List<Employee> findHighSalary() {
		List<Employee> emps = new EmployeeService().findHighSalary();
		return emps;
	}

	public List<Employee> findLowSalary() {
		List<Employee> emps = new EmployeeService().findLowSalary();
		return emps;
	}

	public int save(String empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, String salLevel, int salary, double bonus, String managerCode) {
		
		EmployeeDto empDto = new EmployeeDto(empId, empName, empNo, email, phone, deptCode,
											 jobCode, salLevel, salary, bonus, managerCode,
											 null, null, "N");
		
		int result = new EmployeeService().save(empDto);
		
		return result;
	}

	public int updateInfo(String empId, int salary, String deptCode, String jobCode) {
		if(findById(empId) == null) {
			return 0;
		}
		EmployeeInfoDto empInDto = new EmployeeInfoDto(empId, salary, deptCode, jobCode);
		int result = new EmployeeService().updateInfo(empInDto);
		return result;
	}

}
