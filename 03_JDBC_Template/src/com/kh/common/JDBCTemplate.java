package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	/*
	 * JDBC과정 중 반복적으로 쓰이는 구문들을(예:드라이버등록) 각각의 메서드로 정의해둘 클래스 중복된 코드들을 메서드로 분리하여 '재사용'
	 * '재사용' => 이 클래스의 모든 메서드는 전부 static으로 선언
	 */

	/** 
	 * JDBC Driver를 등록하는 메서드: 프로그램 실행 중 단 한 번만 실행되면 됨
	 */
	public static void registDriver() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * DB의 연결정보를 가지고 있는 Connection객체를 생성해서 반환해주는 메서드
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "CJY19", "CJY191234"); // PW
			conn.setAutoCommit(false);

			return conn;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

// 트랜잭션 처리 메서드 
	/**
	 * Connection 객체를 이용해서 commit 시켜주는 메서드
	 * 
	 * @param conn
	 */
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
