package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestDao;
	
	public List<GuestbookVo> getGuestList() {
		System.out.println("[GuestbookService.getGuestList()]");
		
		return guestDao.guestList();
	}
	
	public int guestAdd(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookService.guestAdd()]");
		
		return guestDao.guestAdd(guestbookVo);
	}
	
	public int guestDelete(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookService.guestDelete()]");
		
		return guestDao.guestDelete(guestbookVo);
	}
	
	//방명록 글 저장_게시글 가져오기
	public GuestbookVo writeResultVo(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookService.writeResultVo()]");
		
		//글 저장
		System.out.println(guestbookVo); //no가 없다
		int count = guestDao.insertGuestbookKey(guestbookVo);
		System.out.println(guestbookVo); //no가 있다
		
		int no = guestbookVo.getNo();
		
		//글 가져오기(방금등록한 번호)
		GuestbookVo resultVo = guestDao.selectGuestbook(no);
		
		return resultVo;
	}
}
