package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	public SqlSession sqlSession;
	
	public List<GuestbookVo> guestList() {
		System.out.println("[GuestbookDao.guestList()]");
		
		return sqlSession.selectList("guestbook.selectGuestList");
	}
	
	public int guestAdd(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookDao.guestAdd()]");
		
		return sqlSession.insert("guestbook.guestInsert", guestbookVo);
	}
	
	public int guestDelete(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookDao.guestDelete()]");
		
		return sqlSession.delete("guestbook.guestDelete", guestbookVo);
	}
	
	//방명록 글 저장
	public int insertGuestbookKey(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookDao.insertGuestbookKey()]");
		
		return sqlSession.insert("guestbook.insertGuestbookKey", guestbookVo);
	}
	
	//방명록 글 가져오기 - ajax
	public GuestbookVo selectGuestbook(int no) {
		System.out.println("[GuestbookDao.selectGuestbook()]");
		
		return sqlSession.selectOne("guestbook.selectGuestbook", no);
	}


}
