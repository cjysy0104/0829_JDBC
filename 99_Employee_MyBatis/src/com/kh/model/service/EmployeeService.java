package com.kh.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.Template;
import com.kh.model.dao.EmployeeDao;
import com.kh.model.dto.EmployeeDto;
import com.kh.model.dto.EmployeeInfoDto;
import com.kh.model.vo.Employee;

public class EmployeeService {

	private EmployeeDao employeeDao = new EmployeeDao();
	
	public List<Employee> findAll() {
		SqlSession session = Template.getSqlSession();
		
		List<Employee> employees = employeeDao.findAll(session);
		
		session.close();
	
		return employees;
	}

	public List<Employee> findByDept(String dept) {
	}

	public List<Employee> findByJob(String job) {
	}

	public EmployeeDto findById(String empId) {
	}

	public List<Employee> findHighSalary() {
	}

	public List<Employee> findLowSalary() {
	}

	public int save(EmployeeDto empDto) {
	}

	public int updateInfo(EmployeeInfoDto empInDto) {
	}


}
