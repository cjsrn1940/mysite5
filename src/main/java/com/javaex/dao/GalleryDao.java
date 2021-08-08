package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	public SqlSession sqlSession;
	
	//리스트
	public List<GalleryVo> getGalleryList() {
		System.out.println("[GalleryDao.getGalleryList()]");
		
		return sqlSession.selectList("gallery.selectGalleryList");
	}
	
	//이미지 저장
	public int saveImg(GalleryVo galleryVo) {
		System.out.println("[GalleryDao.saveImg()]");
		
		int count = sqlSession.insert("gallery.saveImgInfo", galleryVo);
		
		return count;
	}
	
	//이미지 하나 정보 가져오기
	public GalleryVo getImgInfo(int no) {
		System.out.println("[GalleryDao.getImgInfo()]");
		
		GalleryVo imgInfoVo = sqlSession.selectOne("gallery.selectImgInfo", no);
		System.out.println(imgInfoVo);
		
		return imgInfoVo;
	}
	
	//이미지 하나 지우기
	public int imgDelete(int no) {
		System.out.println("[GalleryDao.imgDelete()]");
		
		int count = sqlSession.delete("gallery.deleteImg", no);
		
		return count;
	}
}
