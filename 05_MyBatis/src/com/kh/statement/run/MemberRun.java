package com.kh.statement.run;

import com.kh.common.Template;
import com.kh.statement.view.MemberView;

public class MemberRun {

	public static void main(String[] args) {
		
//		JDBCTemplate.registDriver();
//		JDBCTemplate.getConnection();
		
		
		MemberView mv = new MemberView();
		mv.mainMenu();
	}

}