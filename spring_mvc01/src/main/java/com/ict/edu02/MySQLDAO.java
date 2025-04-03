package com.ict.edu02;

public class MySQLDAO implements DAO{

	public MySQLDAO() {
		System.out.println("MySQLDAO 생성자");
	}
	@Override
	public void prn() {
		System.out.println("MySQLDAO prn()");
	}

}
