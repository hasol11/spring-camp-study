package com.ict.edu03.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu03.service.GuestBookService;
import com.ict.edu03.vo.GuestBookVO;

@Controller
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	@GetMapping("/guestBookList")
	public ModelAndView getGuestBookList() {
		ModelAndView mv =new ModelAndView();
		List<GuestBookVO> list =guestBookService.getGuestBookList();
		mv.addObject("list",list);
		mv.setViewName("day03/list");
		return mv;
	}
	
	@GetMapping("/guestBookWrite")
	public ModelAndView guestBookWrite() {
		return new ModelAndView("day03/write");
	}
	
	@PostMapping("/guestBookWriteOK")
	public ModelAndView guestBookWriteOK(GuestBookVO gbvo,
			HttpServletRequest request) {
		try {
			ModelAndView mv=new ModelAndView();
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/");
			MultipartFile file = gbvo.getGb_file_name();
			if(file.isEmpty()) {
				gbvo.setGb_f_name("");
			}else {
				UUID uuid=UUID.randomUUID();
				String f_name=uuid.toString()+"_"+file.getOriginalFilename();
				gbvo.setGb_f_name(f_name);
				//실제 파일 업로드
				file.transferTo(new File(path,f_name));
			}
			//비밀번호 암호화(다음에)
			int result=guestBookService.getGuestBookInsert(gbvo);
			if(result>0) {
				mv.setViewName("redirect:/guestBookList");
				return mv;
			}
			
			return new ModelAndView("day03/error");
		} catch (Exception e) {
			return new ModelAndView("day03/error");
		}
	}
	
	@GetMapping("/guestBookDetail")
	public ModelAndView guestBookDetail(GuestBookVO gbvo) {
		try {
			ModelAndView mv= new ModelAndView();
			GuestBookVO gvo=guestBookService.getGuestBookDetail(gbvo);
			if(gvo!=null) {
				mv.addObject("gvo", gvo);
				mv.setViewName("day03/onelist");
				return mv;
			}
			return new ModelAndView("day03/error");
		} catch (Exception e) {
			return new ModelAndView("day03/error");
		}
	}
}
