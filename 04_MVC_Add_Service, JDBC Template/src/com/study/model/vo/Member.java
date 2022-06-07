package com.study.model.vo;

import java.sql.Date; // 조회된 결과를 담기위해서는 java.sql로 해야함

public class Member {

	private String userId;
	private String userPwd;
	private String userName;
	private String gender;
	private int age;
	private String phone;
	private Date enrollDate;
	
	public Member() {}

	public Member(String userId, String userPwd, String userName, String gender, int age, String phone,
			Date enrollDate) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.enrollDate = enrollDate;
	}
	
	public Member(String userId, String userPwd, String userName, String gender, int age, String phone) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [아이디 = " + userId + ", 비밀번호 = " + userPwd + ", 이름 = " + userName
				+ ", 성별 = " + gender + ", 나이 = " + age + ", 전화번호 = " + phone + ", 회원가입일 = " + enrollDate + "]";
	}

}
