package com.ict.edu06.service;

import java.util.List;

import com.ict.edu06.vo.BoardVO;

public interface BoardSerivce {
	
	public int getTotalCount();

	public List<BoardVO> getBoardList(int limit, int offset);

	public int getBoardInsert(BoardVO boardVO);
}
