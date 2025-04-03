package com.ict.edu04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Service {
	//자동으로 연결하겠다 
	@Autowired
	
	//참조하는 클래스의 id 호출 
	//아이디가 없을 때는 클래스 이름의 첫 글자를 소문자로 만드는 것. 
	//@Qualifier("oracleDAO")
	
	//@Qualifier는 참조하는 클래스의 id와 변수 이름이 다를 때 oracleDAO != dao
	//@Autowired만 사용하고 싶다면 Qualifier는 참조하는 클래스의 id와 변수 이름이 같게 만들면 된다.
	//id가 없을 경우 클래스명이 id가 되므로, 같게 하고 싶을 때는 @Component("id명")에 설정해주면 된다.
	private DAO dao;
	
	public Service() {
		System.out.println("Service 기본생성자");
	}
	public Service(DAO dao) {
		this.dao = dao;
	}
	
	public void biz() {
		dao.prn();
	}
	
	public DAO getDao() {
		return dao;
	}
	public void setDao(DAO dao) {
		this.dao = dao;
	}	
}
