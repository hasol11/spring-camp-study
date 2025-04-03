package com.ict.edu02;

public class Service {
	private DAO dao;
	public Service() {}
	public Service(DAO dao) {
		this.dao=dao;
	}
	public DAO getDao() {
		return dao;
	}
	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	//사용하고 싶은 메서드
	public void biz() {
		dao.prn();
	}
}
