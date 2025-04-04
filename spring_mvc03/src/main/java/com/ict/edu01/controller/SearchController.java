package com.ict.edu01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu01.service.CustomerService;
import com.ict.edu01.vo.CustomerVO;

@Controller
public class SearchController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/searchPage")
	public ModelAndView getSearch() {
		return new ModelAndView("day01/input");
	}
	
	@GetMapping("/search")
	public ModelAndView getSearchDetail(@ModelAttribute("cvo") CustomerVO cvo) {
		ModelAndView mv = new ModelAndView();
		CustomerVO custom= customerService.getSearchDetail(cvo);
		mv.addObject("cus", custom);
		mv.setViewName("day01/result00");
		return mv;
	}
}
