package com.ict.edu06.service;

import java.util.List;
import java.util.Map;

import com.ict.edu06.vo.BoardVO;

public interface BoardSerivce {
	
	public int getTotalCount();

	public List<BoardVO> getBoardList(int limit, int offset);

	public int getBoardInsert(BoardVO boardVO);
	public int getBoardHit(String bo_idx);
	public BoardVO getBoardDetail(String bo_idx);
	public int getLevUp(Map<String, Integer> map);
	public int getBoardAnsInsert(BoardVO boardVO);
	public int getBoardDelete(String bo_idx);

	public int getBoardUpdate(BoardVO boardVO);
}
