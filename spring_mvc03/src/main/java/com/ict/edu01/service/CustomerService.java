package com.ict.edu01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu01.repository.CustomerDAO;
import com.ict.edu01.vo.CustomerVO;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	public CustomerVO getSearchDetail(CustomerVO cvo) {
		return customerDAO.getSearchDetail(cvo);
	}
}
