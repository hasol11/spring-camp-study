package com.ict.edu02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu02.service.BookService;
import com.ict.edu02.service.CalcService;
import com.ict.edu02.vo.BookVO;
import com.ict.edu02.vo.CalVO;
import com.ict.edu02.vo.MembersVO;

@Controller
public class Day2Controller {
	
	//@Autowired(Spring)와 @Inject(java)
	
	@Autowired
	private CalcService calcService;
	@Autowired
	private BookService bookService;
	
	@GetMapping("/day02")
	public ModelAndView getForm() {
		return new ModelAndView("day02/input");
	}
	
/*	@PostMapping("/logIn01")
	public ModelAndView getLogIn01(HttpServletRequest request) {
		ModelAndView mv =new ModelAndView();
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		mv.addObject("userPw", userPw);
		mv.addObject("userId", userId);
		mv.setViewName("day02/result01");
		return mv;
	} 
	
	//근데 이거 값 보낼 때 view의 form에서의 name과 VO에서의 변수명이 같아야 하나?
	@PostMapping("/logIn01")
	public ModelAndView getLogIn01(MembersVO mvo) {
		ModelAndView mv =new ModelAndView();
		
		//원래는 DB 로그인 처리를 해야 하지만, 지금은 일단 값 던지는 것부터
		mv.addObject("userPw", mvo.getUserId());
		mv.addObject("userId", mvo.getUserPw());
		mv.setViewName("day02/result01");
		return mv;
	}*/
	
	@PostMapping("/logIn01")
	public ModelAndView getLogIn01(MembersVO mvo) {
		ModelAndView mv =new ModelAndView();
		
		mv.addObject("userId", mvo.getUserId());
		mv.addObject("userPw", mvo.getUserPw());
		
		//원래는 DB 로그인 처리를 해야 하지만, 지금은 일단 값 던지는 것부터
		//이런 식으로 보내고 view(jsp에서 mvo.변수명을 쓰면 꺼낼 수 있다.)
		mv.addObject("mvo", mvo);
		mv.setViewName("day02/result01");
		return mv;
	}
	/*
	@GetMapping("/cal")
	public ModelAndView getCal(CalVO vo) {
		ModelAndView mv =new ModelAndView();
		
		//여기서 해도 되지만, service에서 하자
		
		int su1= Integer.parseInt(vo.getFirstNum());
		int su2= Integer.parseInt(vo.getSecNum());
		int result=0;
		switch(vo.getCal()) {
		case "+" : result=su1+su2; break;
		case "-" : result=su1-su2; break;
		case "*" : result=su1*su2; break;
		case "/" : result=su1/su2; break;
		}
		
		
		//CalcService calcService = new CalcService();
		int result= calcService.getCalc(vo);
		
		mv.addObject("vo", vo);
		mv.addObject("result", result);
		
		mv.setViewName("day02/result02");
		return mv;
	} */
	
	@GetMapping("/cal")
	public ModelAndView getCal(@ModelAttribute("vo") CalVO vo) {
		ModelAndView mv =new ModelAndView();
		//@ModelAttribute("cvo") : 클라이언트 요청의 파라미터를 자바 객체에 자동 바인딩해주는 역할 
		
		//여기서 해도 되지만, service에서 하자
		/*
		int su1= Integer.parseInt(vo.getFirstNum());
		int su2= Integer.parseInt(vo.getSecNum());
		int result=0;
		switch(vo.getCal()) {
		case "+" : result=su1+su2; break;
		case "-" : result=su1-su2; break;
		case "*" : result=su1*su2; break;
		case "/" : result=su1/su2; break;
		}
		*/
		//@ModelAttribute("vo")
		//CalcService calcService = new CalcService();
		int result= calcService.getCalc(vo);
		
		//mv.addObject("vo", vo);
		mv.addObject("result", result);
		
		mv.setViewName("day02/result02");
		return mv;
	}
	
	@RequestMapping("/hobby")
	public ModelAndView getHobby(@ModelAttribute("cvo") CalVO cvo) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("day02/result03");
		return mv;
	}
	
	@RequestMapping("/bookList")
	public ModelAndView getBookList() {
		ModelAndView mv =new ModelAndView();
		List<BookVO> list= bookService.getBookList();
		mv.addObject("list",list);
		mv.setViewName("day02/result04");
		return mv;
	}
	
	@GetMapping("/bookdetail")
	public ModelAndView getBookDetail(BookVO bvo) {
		ModelAndView mv= new ModelAndView();
		BookVO bookvo=bookService.getBookDetail(bvo);
		mv.addObject("bvo", bookvo);
		mv.setViewName("day02/result05");
		return mv;
	}
	
	@GetMapping("/bookdelete")
	public ModelAndView getBookDelete(BookVO bvo) {
		ModelAndView mv = new ModelAndView();
		
		int result=bookService.getBookDelete(bvo);
		
		//servlet-context.xml 에 리턴 후 redirect 때문에 
		//servlet-context.xml 와서 /bookList를 찾아간다. 
		//redirect가 get방식이야..? 근데 redirect로 불러올 건데 그게 만약 post 방식이면 RequestMapping으로 받자
		mv.setViewName("redirect:/bookList"); //@RequestMapping("/bookList") 여기 부분으로 이동한다.
		return mv;
	}
	
	@GetMapping("/bookupdate")
	public ModelAndView getBookUpdate(BookVO bvo) {
		ModelAndView mv= new ModelAndView();
		BookVO bookvo=bookService.getBookDetail(bvo);
		mv.addObject("bvo", bookvo);
		mv.setViewName("day02/result06");
		return mv;
	}
	
	@PostMapping("/bookupdate_ok")
	public ModelAndView getBookUpdate_ok(BookVO bvo) {
		ModelAndView mv=new ModelAndView();
		int result= bookService.getBookUpdate_ok(bvo);
		mv.setViewName("redirect:/bookdetail?bookid="+bvo.getBookid());
		return mv;
	}
}
