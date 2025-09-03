package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	public static void registDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "CJY19", "CJY191234");
			conn.setAutoCommit(false);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void commit(Connection conn) {
		try {
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param conn
	 */
	public static void rollbback(Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//JDBC용 객체를 반납해주는 메서드 (각 개체별로) 
//Connection 객체를 전달받아서 반납해주는 메서드
	/**
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// Statement객체를 전달받아서 반납
// 다형성을 적용하여 반납가능
	public static void close(Statement stat) {
		try {
			if (stat != null) {
				stat.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
