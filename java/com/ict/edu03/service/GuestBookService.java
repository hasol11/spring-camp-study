package com.ict.edu03.service;

import java.util.List;

import com.ict.edu03.vo.GuestBookVO;

public interface GuestBookService {
	//list
	public List<GuestBookVO> getGuestBookList();
	
	//write
	public int getGuestBookInsert(GuestBookVO guestBookVO);
	
	//detail
	public GuestBookVO getGuestBookDetail(GuestBookVO guestBookVO);
	
	//update
	public int getGuestBookUpdate(GuestBookVO guestBookVO);
	
	//delete
	public int getGuestBookDelete(GuestBookVO guestBookVO);
	
}
