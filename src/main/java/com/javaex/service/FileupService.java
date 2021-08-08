package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileupDao;
import com.javaex.vo.GalleryPracticeVo;
import com.javaex.vo.UserVo;

@Service
public class FileupService {
		
	@Autowired
	private FileupDao fileupDao;
	
	//파일 업로드 처리
	public String restore(MultipartFile file, int userNo) {
		System.out.println("[FileupService.restore()]");
		
		String saveDir = "C:\\javaStudy\\upload";
		
		//파일 서버하드디스크에 저장
		//파일 정보를 db에 저장
		
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
		
		//2.파일정보를 db에 저장 -- 과제
		//vo만들어서 저장
		GalleryPracticeVo gallerypracticeVo = new GalleryPracticeVo(userNo, filePath, orgName, saveName, fileSize);
		int count = fileupDao.saveImg(gallerypracticeVo);
		System.out.println(count);
		
		
		return saveName;
		
		
		
	}
}
