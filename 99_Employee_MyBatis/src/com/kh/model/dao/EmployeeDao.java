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

import org.apache.ibatis.session.SqlSession;

import com.kh.common.JDBCTemplate;
import com.kh.model.dto.EmployeeDto;
import com.kh.model.dto.EmployeeInfoDto;
import com.kh.model.vo.Employee;

public class EmployeeDao {

	public List<Employee> findAll(SqlSession session) {

		return session.selectList("findAll");
	}

}
