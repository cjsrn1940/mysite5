package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	//이미지 하나 지우기
	public int imgDelete(int no) {
		System.out.println("[GalleryService.imgDelete()]");
		
		return galleryDao.imgDelete(no);
	}
	
	//이미지 하나의 정보 가져오기
	public GalleryVo getImgInfo(int no) {
		System.out.println("[GalleryService.getImgInfo()]");
		
		return galleryDao.getImgInfo(no);
	}
	
	//리스트 가져오기
	public List<GalleryVo> getGalleryList() {
		System.out.println("[GalleryService.getGalleryList()]");
		
		return galleryDao.getGalleryList();
	}
	
	//파일 업로드 처리
	public String restore(MultipartFile file, int userNo, String content ) {
		System.out.println("[GalleryService.restore()]");
		
		String saveDir = "C:\\javaStudy\\upload";
		
		//원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName:" + orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exName:" + exName);
		
		
		//저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName:" + saveName);
		
		//파일패스
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath:" + filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize:" + fileSize);
		
		//1.파일을 서버의 하드디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//2.파일정보를 db에 저장
		GalleryVo galleryVo = new GalleryVo(userNo, content, filePath, orgName, saveName, fileSize);
		int count = galleryDao.saveImg(galleryVo);
		System.out.println(count);
		
		return saveName;
	}

}














