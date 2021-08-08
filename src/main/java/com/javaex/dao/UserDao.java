package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	//필드
	@Autowired
	private SqlSession sqlSession;
	
	//생성자
	//메소드gs
	//메소드일반
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");
		
		return sqlSession.selectOne("user.selectUser", userVo);
	}
	
	public int addUser(UserVo userVo) {
		System.out.println("[UserDao.addUser()]");
		
		int count = sqlSession.insert("user.addUser", userVo);
		
		return count;
	}
	
	public UserVo modifyUser(int no) {
		System.out.println("[UserDao.selectUser()]");
		
		return sqlSession.selectOne("user.modifyUser", no);
	}
	
	public int modify(UserVo userVo) {
		System.out.println("[UserDao.modify()]");
		
		int count = sqlSession.insert("user.modify", userVo);
		return count;
	}
}
