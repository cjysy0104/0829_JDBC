package com.kh.model.service;

import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kh.model.dao.EmployeeDao;
import com.kh.model.dto.EmployeeDto;
import com.kh.model.dto.EmployeeInfoDto;
import com.kh.model.vo.Employee;

public class EmployeeService {

	private Connection conn = null;

	public EmployeeService() {
		this.conn = getConnection();
	}

	public List<Employee> findAll() {
		List<Employee> emps = new EmployeeDao().findAll(conn);
		close(conn);
		return emps;
	}

	public List<Employee> findByDept(String dept) {
		List<Employee> emps = new EmployeeDao().findByDept(conn, dept);
		close(conn);
		return emps;
	}

	public List<Employee> findByJob(String job) {
		List<Employee> emps = new EmployeeDao().findByJob(conn, job);
		close(conn);
		return emps;
	}

	public EmployeeDto findById(String empId) {
		EmployeeDto empDto = new EmployeeDao().findById(conn, empId);
		close(conn);
		return empDto;
	}

	public List<Employee> findHighSalary() {
		List<Employee> emps = new EmployeeDao().findHighSalary(conn);
		close(conn);
		return emps;
	}

	public List<Employee> findLowSalary() {
		List<Employee> emps = new EmployeeDao().findLowSalary(conn);
		close(conn);
		return emps;
	}

	public int save(EmployeeDto empDto) {
		int result = new EmployeeDao().save(conn, empDto);
		if (result > 0) {
			try {
				if (conn != null) {
					conn.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateInfo(EmployeeInfoDto empInDto) {
		int result = new EmployeeDao().updateInfo(conn, empInDto);
		return result;
	}


}
