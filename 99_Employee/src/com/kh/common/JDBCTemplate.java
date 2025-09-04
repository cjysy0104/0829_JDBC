package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
	
	// DRIVER
	public static void registDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// CONNECTION
	public static Connection getConnection() {
		
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
			
			conn = DriverManager.getConnection(prop.getProperty("URL")
					, prop.getProperty("USERID")
					, prop.getProperty("PASSWORD"));
			
			conn.setAutoCommit(false);
			
			return conn;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// COMMIT
	public static void commit(Connection conn) {
		try {
			if(conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// ROLLBACK
	public static void rollback(Connection conn) {
		try {
			if(conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// CLOSE
	public static void close(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstat) {
		try {
			if(pstat != null) {
				pstat.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
