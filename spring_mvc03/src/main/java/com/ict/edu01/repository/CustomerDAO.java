package com.ict.edu01.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu01.vo.CustomerVO;

@Repository
public class CustomerDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public CustomerVO getSearchDetail(CustomerVO cvo) {
		return sqlSessionTemplate.selectOne("customer.customerdetail", cvo);
	}
}
