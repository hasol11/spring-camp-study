package com.ict.edu01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Start3Controller implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	// 일처리 => 서비스 에서

	// 일처리 후 결과 저장 하기
	request.setAttribute("name", "홍길동");

	ModelAndView mv = new ModelAndView();
	// 갈곳 지정 (결과을 보여줄 jsp 이름)
	mv.setViewName("result1");
	// request 처럼 데이터 저장
	mv.addObject("addr", "활빈당");

	return mv;
	}
}
