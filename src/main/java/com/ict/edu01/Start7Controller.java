package com.ict.edu01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Start7Controller {
	@PostMapping("/start7")
	public ModelAndView play() {
		ModelAndView mv = new ModelAndView();
		String[] carName = {"제너시스","k9", "그렌저","k7"};
		mv.addObject("carName", carName);
		
		mv.setViewName("day01/result2");
		return mv;
	}
}
