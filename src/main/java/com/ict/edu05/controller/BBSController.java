package com.ict.edu05.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu05.service.BbsService;
import com.ict.edu05.vo.BbsVO;

@Controller
public class BBSController {
	@Autowired
	private BbsService bbsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/day05")
	public ModelAndView getBbsList() {
		try {
			ModelAndView mv =new ModelAndView();
			mv.addObject("list", bbsService.getBbsList());
			mv.setViewName("day05/list");
			
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("day05/error");
		}
	}
	
	@GetMapping("/bbs_write")
	public ModelAndView getWrite() {
		return new ModelAndView("day05/write");
		}
	
	@PostMapping("/bbs_write_ok")
	public ModelAndView getBbsWriteOk(BbsVO bvo, HttpServletRequest request) {
		try {
			ModelAndView mv= new ModelAndView();
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/");
			MultipartFile file =bvo.getFile_name();
			if(file.isEmpty()) {
				bvo.setF_name("");
			}else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString()+"_"+ file.getOriginalFilename();
				//DB에 저장
				bvo.setF_name(f_name);
				//실제 업로드
				file.transferTo(new File(path, f_name));
			}
			//비밀번호 암호화 : 복호화가 안됨. 
			String pwd = passwordEncoder.encode(bvo.getPwd());
			bvo.setPwd(pwd);
			
			int result =bbsService.getBbsInsert(bvo);
			if(result>0) {
				mv.setViewName("redirect:/day05");
			}else {
				return new ModelAndView("day05/error");
			}
			
			return mv;
		} catch (Exception e) {
			return new ModelAndView("day05/error");
		}
	}
	
	@GetMapping("/bbs_detail")
	public ModelAndView getBbsDetail(BbsVO bvo) {
		try {
			ModelAndView mv = new ModelAndView();
			//조회수 증가
			bbsService.getBbsHitUpdate(bvo);
			
			//상세 보기 
			mv.addObject("bvo", bbsService.getBbsDetail(bvo));
			
			//댓글 리스트 가져오기(원글과 관련된 댓글만)
			
			mv.setViewName("day05/detail");
			
			return mv;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day05/error");
		}
	}
	
	@GetMapping("/bbs_down")
	public void getBbsDown(HttpServletRequest request, HttpServletResponse response) {
		try {
			String f_name = request.getParameter("f_name");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/"+f_name);
			String r_path = URLEncoder.encode(f_name, "UTF-8");
			
			//브라우저 설정
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + r_path);
			
			//실제 입출력
			File file =new File(new String(path.getBytes(),"utf-8"));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			
			FileCopyUtils.copy(in, out);
			response.getOutputStream().flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//(@ModelAttribute("b_idx") String b_idx
	//파라미터 b_idx를 받아서 다음 delete.jsp에 b_idx로 넘긴다.
	@PostMapping("/bbs_delete")
	public ModelAndView getBbsDelete(@ModelAttribute("b_idx") String b_idx) {
		return new ModelAndView("day05/delete");
	}
	
	/*
	@PostMapping("/bbs_delete")
	public ModelAndView getBbsDelete2(BbsVO bvo) {
		ModelAndView mv =new ModelAndView();
		String b_idx= bvo.getB_idx();
		mv.addObject("b_idx", b_idx);
		mv.setViewName("day05/delete");
		return new ModelAndView("day05/delete");
	}*/
	
	@PostMapping("/bbs_delete_ok")
	public ModelAndView getBbsDeleteOk(BbsVO bvo, @ModelAttribute("b_idx") String b_idx) {
		try {
			ModelAndView mv =new ModelAndView();
			
			//비밀번호 검사
			BbsVO bbsvo = bbsService.getBbsDetail(bvo);
			String dbpwd = bbsvo.getPwd();
			if(passwordEncoder.matches(bvo.getPwd(), dbpwd)) {
				//원글 삭제
				//active 컬럼을 만들어서 0 -> 1 변경하자 
				int result= bbsService.getBbsDelete(bvo);
				if(result>0) {
					mv.setViewName("redirect:/day05");
					return mv;
				}else {
					return new ModelAndView("day05/error");
				}
			}else {
				//틀렸을 때 
				mv.setViewName("day05/delete");
				mv.addObject("pwdchk","fail");
				return mv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day05/error");
		}
	}
	
	@PostMapping("/bbs_update")
	public ModelAndView getBbsUpdate(BbsVO bvo) {
		try {
			ModelAndView mv =new ModelAndView();
			mv.addObject("bvo", bbsService.getBbsDetail(bvo));
			mv.setViewName("day05/update");
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day05/error");
		}
	}
	
	@PostMapping("/bbs_update_ok")
	public ModelAndView getBbsUpdateOk(BbsVO bvo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView();
			BbsVO bbsvo = bbsService.getBbsDetail(bvo);
			String dbpwd = bbsvo.getPwd();
					
			if(passwordEncoder.matches(bvo.getPwd(), dbpwd)) {
				//원글 수정
				try {
					String path = request.getSession().getServletContext().getRealPath("/resources/upload");
					MultipartFile file = bvo.getFile_name();
					String old_f_name = bvo.getOld_f_name();
					if(file.isEmpty()) {
						bvo.setF_name(old_f_name);
					}else {
						UUID uuid = UUID.randomUUID();
						String f_name =uuid.toString()+"_"+file.getOriginalFilename();
						bvo.setF_name(f_name);
						
						//실제 업로드
						file.transferTo(new File(path,f_name));
					}
					int result=bbsService.getBbsUpdate(bvo);
					if(result>0) {
						mv.setViewName("redirect:/bbs_detail");
					}else {
						return new ModelAndView("day05/error");
					}
				} catch (Exception e) {
					return new ModelAndView("day05/error");
				}
				
				mv.setViewName("redirect:/bbs_detail?b_idx="+bvo.getB_idx());
				return mv;
			}else {
				//틀렸을 때 
				mv.addObject("pwdchk","fail");
				
				
				
				//수정된 정보가 넘어간다.(다음주까지 처리)
				//bvo.setF_name(bvo.getOld_f_name());
				//mv.addObject("bvo",bvo);
				
				//원래 DB 정보 보내기
				mv.addObject("bvo", bbsvo);
				mv.setViewName("day05/update");
				return mv;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day05/error");
		}
	}
	
}
