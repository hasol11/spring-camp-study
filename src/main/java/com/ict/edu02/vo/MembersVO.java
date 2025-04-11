package com.ict.edu02.vo;

public class MembersVO {
	private String user_Idx, userId, userPw, userName, userAge, userReg;

	public MembersVO() {}
	
	
	//이건 사실상 필요 없다 왜냐하면 user_Idx auto로 받을 거고, userReg는 생성 시각이기에 자동으로 생성
	public MembersVO(String user_Idx, String userId, String userPw, String userName, String userAge, String userReg) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userAge = userAge;
	}



	public String getUser_Idx() {
		return user_Idx;
	}

	public void setUser_Idx(String user_Idx) {
		this.user_Idx = user_Idx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserReg() {
		return userReg;
	}

	public void setUserReg(String userReg) {
		this.userReg = userReg;
	}
	
	
}
