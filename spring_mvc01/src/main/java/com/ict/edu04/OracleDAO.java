package com.ict.edu04;

import org.springframework.stereotype.Component;

@Component("dao")
public class OracleDAO implements DAO{

	public OracleDAO() {
		System.out.println("OracleDAO 생성자");
}
	
	@Override
	public void prn() {
		System.out.println("OracleDAO의 prn() 메서드");
	}

}
