package com.ict.edu04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		ApplicationContext context=
				new GenericXmlApplicationContext("com/ict/edu04/config.xml");
		
		Service service= (Service) context.getBean("service");
		service.biz();
		
		/*
		 * Service service2=(Service) context.getBean("service2"); service2.biz();
		 */
	}
}
