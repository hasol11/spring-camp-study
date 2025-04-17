package com.ict.edu08.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu08.service.ShopService;
import com.ict.edu08.vo.CartVO;
import com.ict.edu08.vo.ShopVO;
import com.ict.edu09.vo.MembersVO;

@Controller
public class ShopController {
	@Autowired
	private ShopService shopService;
	
	@GetMapping("/day08")
	public ModelAndView getShopList(String category) {
		try {
			ModelAndView mv = new ModelAndView();
			if(category == null) {
				category = "ele002";
			}
			
			List<ShopVO> shop_list = shopService.getShopList(category);
			
			if(shop_list != null) {
				mv.addObject("shop_list", shop_list);
				mv.setViewName("day08/product_list");
				return mv;
			}else {
				mv.setViewName("day08/error");
				return mv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day08/error");
		}
	}
	
	@GetMapping("/shop_detail")
	public ModelAndView getShopDetail(@RequestParam("shop_idx") String shop_idx) {
		try {
			ModelAndView mv = new ModelAndView();
			ShopVO svo = shopService.getShopDetail(shop_idx);
			if( svo != null ) {
				mv.addObject("svo", svo );
				mv.setViewName("day08/product_content");
				return mv;
			}else {
				mv.setViewName("day08/error");
				return mv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day08/error");
		}
	}
	
	@GetMapping("/shop_show_cart")
	public ModelAndView getShopShowCart(HttpSession session) {
		try {
			String loginchk  =  (String) session.getAttribute("loginchk");
			if(loginchk.equals("ok")) {
				ModelAndView mv = new ModelAndView();
				
				// 로그인한 사람의 정보를 가져와서 cart 테이블에서 검색 후 cartList에 넘기자 
				MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");
				List<CartVO> cart_list = shopService.getCartList(membersVO.getM_id());
				
				if(cart_list != null) {
					mv.addObject("cart_list", cart_list);
				}
				
				mv.setViewName("day08/cartList");
				return mv;
			}else {
				return new ModelAndView("redirect:/members_loginForm");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/members_loginForm");
		}
	}
	
	@GetMapping("/shop_add_cart")
	public ModelAndView shopAddCart(@ModelAttribute("shop_idx") String shop_idx,
			HttpSession session) {
		try {
			String loginchk  =  (String) session.getAttribute("loginchk");
			if(loginchk.equals("ok")) {
				ModelAndView mv = new ModelAndView();
				
				// 사용자 정보 
				MembersVO mvo = (MembersVO)session.getAttribute("membersVO");
			
				// 제품정보가져오기
				ShopVO svo = shopService.getShopDetail(shop_idx);
				
				// cart 테이블에 사용자ID 에 속하는 제품번호가 있는지  검색
				int result = shopService.getCartChk(mvo.getM_id(), svo.getP_num());
				
				if(result == 0) {
					// 카트 테이블에 추가 (insert)
					CartVO cvo = new CartVO();
					cvo.setP_num(svo.getP_num());
					cvo.setP_name(svo.getP_name());
					cvo.setP_price(String.valueOf(svo.getP_price()));
					cvo.setP_saleprice(String.valueOf(svo.getP_saleprice()));
					cvo.setM_id(mvo.getM_id());
					shopService.getCartInsert(cvo);
				}else{
					// 카트 테이블에 수량 만 증가 (update)
					shopService.getCartUpdate(mvo.getM_id(), svo.getP_num());
				}
				
				mv.setViewName("redirect:/shop_detail");
				return mv;
			}else {
				return new ModelAndView("redirect:/members_LoginForm");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/members_LoginForm");
		}
	}
	
	@PostMapping("/cart_edit")
	public ModelAndView getCartEdit(CartVO cartVO) {
		try {
			ModelAndView mv = new ModelAndView();
			
			shopService.getCartEdit(cartVO);
			
			mv.setViewName("redirect:/shop_show_cart");
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day08/error");
		}
	}
	@GetMapping("/cart_delete")
	public ModelAndView getCartDelete(@RequestParam("cart_idx") String cart_idx) {
		try {
			ModelAndView mv = new ModelAndView();
			
			shopService.getCartDelete(cart_idx);
			
			mv.setViewName("redirect:/shop_show_cart");
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day08/error");
		}
	}
}






