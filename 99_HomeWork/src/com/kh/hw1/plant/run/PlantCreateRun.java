package com.kh.hw1.plant.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PlantCreateRun {

	public static void main(String[] args) {

		// 0.
		Connection conn = null;
		Statement stat = null;
		
		// SQL Write
		// PLANT_NO, PLANT_NAME, TYPE, AGE, ENROLL_DATE
		String sql = null;
		sql = """
				CREATE TABLE Plant(
					PlANT_NO NUMBER CONSTRAINT PK_PLANT PRIMARY KEY,
					PLANT_NAME VARCHAR2(20) CONSTRAINT NAME_NOT_NULL NOT NULL,
					TYPE VARCHAR2(10) CHECK(TYPE IN ('TREE', 'FLOWER', 'VEGETABLE', 'ETC')),
					AGE NUMBER,
					ENROLL_DATE DATE DEFAULT SYSDATE NOT NULL
					)
				""";
		
		try {
			// Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver OK");
			
			
			// Connection
			conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.20:10000:XE", "CJY19", "CJY191234");
			System.out.println("Connect OK");
			
			// Statement
			stat = conn.createStatement();
			System.out.println("Statement Obj OK");
			
			// SQL Active
			stat.executeQuery(sql);
			System.out.println("SQL OK");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}


	}

}
