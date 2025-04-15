package com.ict.edu07.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.edu07.service.EmailService;

@RestController
public class AjaxEmailController {
	@Autowired 
	private EmailService emailService; 
	@PostMapping("/sendCode")
	@ResponseBody
	public String sendCode(@RequestParam("email") String email , 
			HttpServletRequest request) {
	   try {
		   String code = emailService.sendAuthMail(email);
		   // 코드를 세션에 저장하자 왜? 다른 화면으로 이동해도 정보를 가지고 있자 
		   request.getSession().setAttribute("authCode", code);
		   // 인증 시간 기록
		   request.getSession().setAttribute("authTime", System.currentTimeMillis()); 
		   return "success";
	   } catch (Exception e) {
		   return  "fail";
	   }	
	}
	@PostMapping("/verifyCode")
	@ResponseBody
	public String verifyCode(@RequestParam("code") String code , HttpServletRequest request) {
	  // 세션에 저장되어있는  authCode와 authTime를 호출해서 
      // 파라미터의 code 비교하고 시간도 비교해서  5분을 초과하면 오류
		String saveCode = (String) request.getSession().getAttribute("authCode");
		long saveTime = (Long) request.getSession().getAttribute("authTime");
		
		long curTime = System.currentTimeMillis();
		if(saveCode == null || saveTime == 0) return "no_code";
		if((curTime - saveTime) > 1000 * 60 * 5) return "expired";
		if(saveCode.equals(code)) {
			return "match"; 
		}else {
			return "mismatch";
		}
	}
}
