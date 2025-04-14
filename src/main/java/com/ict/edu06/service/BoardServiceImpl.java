package com.ict.edu06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu06.repository.BoardDAO;
import com.ict.edu06.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardSerivce{

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public int getTotalCount() {
		return boardDAO.getTotalCount();
	}

	@Override
	public List<BoardVO> getBoardList(int limit, int offset) {
		return boardDAO.getBoardList(limit, offset);
	}

	@Override
	public int getBoardInsert(BoardVO boardVO) {
		return boardDAO.getBoardInsert(boardVO);
	}

}
