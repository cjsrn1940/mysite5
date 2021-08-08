package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryPracticeVo;

@Repository
public class FileupDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int  saveImg(GalleryPracticeVo gallerypracticeVo) {
		System.out.println("[FileupDao.saveImg()]");
		
		
		int count = sqlSession.insert("galleryPractice.saveImgInfo", gallerypracticeVo);
		
		return count; 
	}
}
