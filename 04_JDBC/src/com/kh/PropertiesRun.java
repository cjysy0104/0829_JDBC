package com.kh;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/*
 * <comment> , /comment> =
 * 여는태그, 닫는태그 -> entry 요소
 * <commint Num_"A">B -> 영역 
 * </properties> -> <entry>~/<properties> 의 부모 / 요소
 */
public class PropertiesRun {

	public static void main(String[] args) {

		Properties prop = new Properties();
		prop.setProperty("A", "B");
		
		try {
			//prop.store(new FileOutputStream("driver.properties"), "setting for DBMS");
			prop.storeToXML(new FileOutputStream("member-mapper.xml"), "MEMBER SQL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
