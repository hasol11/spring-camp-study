package com.ict.edu02.service;

import org.springframework.stereotype.Service;

import com.ict.edu02.vo.CalVO;

@Service
public class CalcService {
	public CalcService() {
		System.out.println("CalcSevice 생성자");
	}
	
	public int getCalc(CalVO cvo) {
		int result=0;
		int su1=Integer.parseInt(cvo.getFirstNum());
		int su2=Integer.parseInt(cvo.getSecNum());
		
		switch(cvo.getCal()) {
		case "+" : result=su1+su2; break;
		case "-" : result=su1-su2; break;
		case "*" : result=su1*su2; break;
		case "/" : result=su1/su2; break;
		}
		return result;
	}
}
