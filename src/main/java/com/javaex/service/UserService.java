package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//로그인 사용자정보 가져오기
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserService.getUser()]");
		
		UserVo authUser = userDao.selectUser(userVo);
		
		return authUser;
	}
	
	//joinForm 에서 얻어온 회원가입 정보 확인하기
	public int joinUser(UserVo userVo) {
		System.out.println("[UserService.joinUser()]");
		
		int count = userDao.addUser(userVo);
		return count;
	}
	
	//회원정보수정폼에 no을 받아와서 기존정보 출력
	public UserVo getUser(int no) {
		System.out.println("[UserService.getUser()]");
		
		UserVo authUser = userDao.modifyUser(no);
		return authUser;
	}

	//회원정보수정
	public int modifyUser(UserVo userVo) {
		System.out.println("[UserService.modifyUser()]");
		
		int count = userDao.modify(userVo);
		return count;
		
	}
	
	//회원가입폼에서 아이디 중복 체크
	public boolean getUser(String id) {
		System.out.println("[UserService.getUser()]");
		
		UserVo userVo = userDao.selectUser(id);
		
		if(userVo == null) {
			return true;
		} else {
			return false;
		}
		
		
	}
}





















