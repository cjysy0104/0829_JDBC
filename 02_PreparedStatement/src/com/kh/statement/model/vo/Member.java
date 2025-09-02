package com.kh.statement.model.vo;

import java.sql.Date;
import java.util.Objects;

/*
 * VO(VValue Object) => 값 개체
 * => 테이블의 한 행에 대한 데이터를 기록할 저장용 객체
 * 
 * VO가 가져야할 특성 == 불변성 : 원래는 setter를 가질 수 없음
 * 
 * VO클래스의 필드 구성 자체를 MEMBER테이블의 칼럼들과 비슷하게
 * SEQUENCE 및 DEFAUL값 조사런으로 사용하는 경우
 * 해당 필드를 제외한 나머지 필드값을 초기화 할 수 있는 생산자를 추가로 구매할 것
 */
public class Member {

	private int userNo;
	private String userId; // SUWERID
	private String userPwd;
	private String userName;
	private String email;
	private Date enrollDate;

	public Member() {
		super();
	}

	

	public Member(String userId, String userPwd, String userName, String email) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
	}



	public Member(int userNo, String userId, String userPwd, String userName, String email, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.enrollDate = enrollDate;
	}


	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "번호 : " + userNo + "\n아이디 : " + userId + "\n비밀 번호 : " + userPwd + "\n이름 : " + userName
				+ "\n이메일 : " + email + "\n등록일 : " + enrollDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, enrollDate, userId, userName, userNo, userPwd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(email, other.email) && Objects.equals(enrollDate, other.enrollDate)
				&& Objects.equals(userId, other.userId) && Objects.equals(userName, other.userName)
				&& userNo == other.userNo && Objects.equals(userPwd, other.userPwd);
	}

}