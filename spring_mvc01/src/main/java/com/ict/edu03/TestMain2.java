package com.ict.edu03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain2 {
	public static void main(String[] args) {
		ApplicationContext context=
				new GenericXmlApplicationContext("com/ict/edu03/config.xml");
		MyProcess myProcess = (MyProcess) context.getBean("process");
		myProcess.prn();
		
		System.out.println("------");
		MyProcess myProcess2 = (MyProcess) context.getBean("process2");
		myProcess2.prn();
	}
	
}
