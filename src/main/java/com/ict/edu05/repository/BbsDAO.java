package com.ict.edu05.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu05.vo.BbsVO;
import com.ict.edu05.vo.CommVO;

@Repository
public class BbsDAO {

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	public List<BbsVO> getBbsList() {
		try {
			return sessionTemplate.selectList("bbs.list");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getBbsInsert(BbsVO bvo) {
		try {
			return sessionTemplate.insert("bbs.insert", bvo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public BbsVO getBbsDetail(BbsVO bvo) {
		try {
			return sessionTemplate.selectOne("bbs.detail",bvo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getBbsDelete(BbsVO bvo) {
		try {
			return sessionTemplate.update("bbs.delete",bvo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getBbsUpdate(BbsVO bvo) {
		try {
			return sessionTemplate.update("bbs.update",bvo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getBbsHitUpdate(BbsVO bvo) {
		try {
			return sessionTemplate.update("bbs.hitUp",bvo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<CommVO> getCommentList(CommVO cvo) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCommentInsert(CommVO cvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCommentUpdate(CommVO cvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCommentDelete(CommVO cvo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
