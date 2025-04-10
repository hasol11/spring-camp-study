package com.ict.edu02.vo;

public class CalVO {
	private String firstNum, secNum, cal;
	
	private String[] hobby;
	
	

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public CalVO(String firstNum, String secNum, String cal) {
		this.firstNum = firstNum;
		this.secNum = secNum;
		this.cal = cal;
	}

	public String getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(String firstNum) {
		this.firstNum = firstNum;
	}

	public String getSecNum() {
		return secNum;
	}

	public void setSecNum(String secNum) {
		this.secNum = secNum;
	}

	public String getCal() {
		return cal;
	}

	public void setCal(String cal) {
		this.cal = cal;
	}
	
	
}
