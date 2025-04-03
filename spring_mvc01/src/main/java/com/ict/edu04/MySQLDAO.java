package com.ict.edu04;

import org.springframework.stereotype.Component;

@Component
public class MySQLDAO implements DAO{
	
	public MySQLDAO() {
		System.out.println("MySQLDAO 기본 생성자");
	}

	@Override
	public void prn() {
		System.out.println("MySQLDAO의 prn() 메서드");
	}

}
