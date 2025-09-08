package com.kh.common;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {

	// Connection 객체의 역할을 대신할 SqlSession객체를 만들어서 반환해주는 메서드를 구현할 것
	
	public static SqlSession getSqlSession() {
		
		SqlSession sqlSession = null;
		
		// mybatis-config.xml  있는 내용을 읽어와서
		// 해당 DB와 접속된 SqlSessio갳체를 재견 반환
		
		String config = "mybatis-config.xml";
				
		// /는 bin(default output folder)여기 한이 보여줘?
		
		try {
			InputStream stream = Resources.getResourceAsStream(config);
			// 1단계 SqlpSessionFactoryBuilder만들기
			// 만드는 법: 기본생성자를 호출
			// new SqlSessionFactoryBiulder();  아까도 마나가 찼는데 호출
			// 2단계 : SqlSessionFactoryh 메션만들기 
			// 만드는방법: 메서드를 호출한다.
			// .build(접속내용을 담은 파열이나 읽어온 입력스트림 읽어온 부담감>
			// 3단계 SqlSession만들기;
			// 4단계 Sql방법이 대혼 나업지지 않치만 Sqlsesstion을 처리
			// .openSession();
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sqlSession;
	}
}
