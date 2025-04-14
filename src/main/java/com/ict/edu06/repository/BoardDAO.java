package com.ict.edu06.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu06.vo.BoardVO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int getTotalCount() {
		try {
			return sqlSessionTemplate.selectOne("board.count");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<BoardVO> getBoardList(int limit, int offset) {
		try {
			Map<String, Integer> map= new HashMap<String, Integer>();
			map.put("limit", limit);
			map.put("offset", offset);
			return sqlSessionTemplate.selectList("board.list", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getBoardInsert(BoardVO boardVO) {
		try {
			return sqlSessionTemplate.insert("board.insert", boardVO);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
