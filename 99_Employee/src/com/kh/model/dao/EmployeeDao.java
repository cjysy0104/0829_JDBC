package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import javax.naming.spi.DirStateFactory.Result;

import com.kh.common.JDBCTemplate;
import com.kh.model.dto.EmployeeDto;
import com.kh.model.dto.EmployeeInfoDto;
import com.kh.model.vo.Employee;

public class EmployeeDao {

	private Properties prop = new Properties();

	public EmployeeDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/employee-mapper.xml"));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> findAll(Connection conn) {
		PreparedStatement pstat = null;
		ResultSet rset = null;
		List<Employee> emps = new ArrayList();

		String sql = prop.getProperty("findAll");

		try {
			pstat = conn.prepareStatement(sql);
			rset = pstat.executeQuery();

			while (rset.next()) {
				Employee emp = new Employee(rset.getString("EMP_ID")
										  , rset.getString("EMP_NAME")
										  , rset.getInt("SALARY")
										  , rset.getString("DEPT_TITLE")
										  , rset.getString("JOB_NAME"));
				emps.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstat);
		}

		return emps;
	}

	public List<Employee> findByDept(Connection conn, String dept) {
		PreparedStatement pstat = null;
		ResultSet rset = null;
		List<Employee> emps = new ArrayList();

		String sql = prop.getProperty("findByDept");

		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dept);
			rset = pstat.executeQuery();
			
			while(rset.next()) {
				Employee emp = new Employee(rset.getString("EMP_ID")
						  				  , rset.getString("EMP_NAME")
						  				  , rset.getInt("SALARY")
						  				  , rset.getString("DEPT_TITLE")
						  				  , rset.getString("JOB_NAME"));
				emps.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstat);
		}
		return emps;
	}

	public List<Employee> findByJob(Connection conn, String job) {
		PreparedStatement pstat = null;
		ResultSet rset = null;
		List<Employee> emps = new ArrayList();

		String sql = prop.getProperty("findByJob");

		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, job);
			rset = pstat.executeQuery();

			while (rset.next()) {
				Employee emp = new Employee(rset.getString("EMP_ID")
						  				  , rset.getString("EMP_NAME")
						  				  , rset.getInt("SALARY")
						  				  , rset.getString("DEPT_TITLE")
						  				  , rset.getString("JOB_NAME"));
				emps.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstat);
		}
		return emps;
	}

	public EmployeeDto findById(Connection conn, String empId) {
		PreparedStatement pstat = null;
		ResultSet rset = null;
		EmployeeDto empDto = null;
		
		String sql = prop.getProperty("findById");
		
		try {
			pstat = conn.prepareStatement(sql);
			rset = pstat.executeQuery();
			
			if(rset.next()) {
				empDto = new EmployeeDto(rset.getString(1)
									   , rset.getString(2)
									   , rset.getString(3)
									   , rset.getString(4)
									   , rset.getString(5)
									   , rset.getString(6)
									   , rset.getString(7)
									   , rset.getString(8)
									   , rset.getInt(9)
									   , rset.getDouble(10)
									   , rset.getString(11)
									   , rset.getDate(12)
									   , rset.getDate(13)
									   , rset.getString(14)
									   );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstat);
		}
		return empDto;
	}

	public List<Employee> findHighSalary(Connection conn) {
		PreparedStatement pstat = null;
		ResultSet rset = null;
		List<Employee> emps = new ArrayList();
		
		String sql = prop.getProperty("findHighSalary");
		
		try {
			pstat = conn.prepareStatement(sql);
			rset = pstat.executeQuery();
			
			while(rset.next()) {
				Employee emp = new Employee(rset.getString("EMP_ID")
		  				  , rset.getString("EMP_NAME")
		  				  , rset.getInt("SALARY")
		  				  , rset.getString("DEPT_TITLE")
		  				  , rset.getString("JOB_NAME"));
				emps.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstat);
		}
		
		return emps;
	}

	public List<Employee> findLowSalary(Connection conn) {
		PreparedStatement pstat = null;
		ResultSet rset = null;
		List<Employee> emps = new ArrayList();
		
		String sql = prop.getProperty("findLowSalary");
		
		try {
			pstat = conn.prepareStatement(sql);
			rset = pstat.executeQuery();
			
			while(rset.next()) {
				Employee emp = new Employee(rset.getString("EMP_ID")
		  				  , rset.getString("EMP_NAME")
		  				  , rset.getInt("SALARY")
		  				  , rset.getString("DEPT_TITLE")
		  				  , rset.getString("JOB_NAME"));
				emps.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstat);
		}
		
		return emps;
	}

	public int save(Connection conn, EmployeeDto empDto) {
		PreparedStatement pstat = null;
		int result = 0;

		String sql = prop.getProperty("save");

		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, empDto.getEmpId());
			pstat.setString(2, empDto.getEmpName());
			pstat.setString(3, empDto.getEmpNo());
			pstat.setString(4, empDto.getEmail());
			pstat.setString(5, empDto.getPhone());
			pstat.setString(6, empDto.getDeptCode());
			pstat.setString(7, empDto.getJobCode());
			pstat.setString(8, empDto.getSalLevel());
			pstat.setInt(9, empDto.getSalary());
			pstat.setDouble(10, empDto.getBonus());
			pstat.setString(11, empDto.getManagerId());

			result = pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstat);
		}

		return result;
	}

	public int updateInfo(Connection conn, EmployeeInfoDto empInDto) {
		PreparedStatement pstat = null;
		int result = 0;
		
		String sql = prop.getProperty("updateInfo");
		
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, empInDto.getSalary());
			pstat.setString(2, empInDto.getDeptCode());
			pstat.setString(3, empInDto.getJobCode());
			pstat.setString(4, empInDto.getEmpId());
			result = pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstat);
		}
		
		return result;
	}

	

}
