package com.kh.run;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesRun {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		
		try {
			prop.storeToXML(new FileOutputStream("driver.xml"), "settingForDBMS");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
