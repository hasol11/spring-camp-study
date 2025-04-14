package com.ict.edu06.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.Paging;
import com.ict.edu06.service.BoardSerivce;
import com.ict.edu06.vo.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private BoardSerivce boardService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private Paging paging;
	
	@RequestMapping("/day06")
	public ModelAndView boardList(HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView();
			
			// 1. 게시물의 수
			int count = boardService.getTotalCount();
			paging.setTotalRecord(count);
			
			// 2. 전체 페이지의 수를 구한다.
			if(paging.getTotalRecord() <= paging.getNumPerPage()) {
				paging.setTotalPage(1);
			}else {
				paging.setTotalPage(paging.getTotalRecord() / paging.getNowPage());
				if(paging.getTotalRecord() % paging.getNowPage()  != 0) {
					paging.setTotalPage(paging.getTotalPage() + 1);
				}
			}
			
			// 3. 파라미터로 넘어온 cPage 구하기 
			String cPage = request.getParameter("cPage");
			// cPage가 파라미터로 넘어오지 않았으면 null 무조건 1 page 이다.
			if(cPage == null) {
				paging.setNowPage(1);
			}else {
				paging.setNowPage(Integer.parseInt(cPage));
			}
			
			// 4. nowPage를 기준으로 필요한 게시물 계산 (offset 구하기) 
			paging.setOffset(paging.getNumPerPage() * (paging.getNowPage()-1));
			
			// 5. 시작블록, 끝블록
			paging.setBeginBlock(
					(int)(((paging.getNowPage()-1)/paging.getPagePerBlock()) * paging.getPagePerBlock()+1));
			
			paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock()-1);
			
			// 주의 사항(endBlock(3,6,9,...)나온다.
			// 그런데 실제 가지고 있는 총 페이지는 3,6,9.. 로 나오지 않을 수 있다.
			if(paging.getEndBlock() > paging.getTotalPage()) {
				paging.setEndBlock(paging.getTotalPage());
			}
			
			// 6. 필요한 게시물만큼 DB 에서 가져오기 
			List<BoardVO> boardList = boardService.getBoardList(paging.getNumPerPage(), paging.getOffset());
			if(boardList != null) {
				mv.addObject("boardList", boardList);
				mv.addObject("paging", paging);
				mv.setViewName("day06/board_list");
				return mv;
			}else {
				return new ModelAndView("day06/error");
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("day06/error");
		}
	}
	
	@GetMapping("/board_write")
	public ModelAndView boradWrite(@ModelAttribute("cPage") String cPage) {
		return new ModelAndView("day06/write");
	}
	
	@PostMapping("/board_write_ok")
	public ModelAndView boardWriteOk(BoardVO boardVO,
			@ModelAttribute("cPage") String cPage,
			HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView();
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/");
			
			MultipartFile file = boardVO.getFile_name();
			if(file.isEmpty()) {
				boardVO.setF_name("");
			}else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString()+"_"+file.getOriginalFilename();
				boardVO.setF_name(f_name);
				file.transferTo(new File(path, f_name));
			}
			
			//비밀번호 암호화
			String pwd=passwordEncoder.encode(boardVO.getPwd());
			boardVO.setPwd(pwd);
			
			int result=boardService.getBoardInsert(boardVO);
			if(result>0) {
				mv.setViewName("redirect:/day06");
			}else {
				mv.setViewName("day06/error");
			}
			
			mv.setViewName("redirect:/day06");
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day06/error");
		}
	}
}








