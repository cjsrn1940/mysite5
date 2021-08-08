package com.javaex.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileupService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/fileup")
public class FileupController {
	
	@Autowired
	private FileupService fileupService;
	
	//업로드 화면
	@RequestMapping(value="/form", method = {RequestMethod.GET, RequestMethod.POST})
	public String form() {
		System.out.println("[FileupController.form()]");
		
		return "/fileup/form";
	}
	
	//파일 업로드
	@RequestMapping(value="/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(Model model ,@RequestParam("file") MultipartFile file, HttpSession session) { //주의 --> MultipartFile은 오는 게 없어도 무조건 나옴 System.out.println(file); 시 뭐든지 나오긴 함
		System.out.println("[FileupController.upload()]");
			
		if(session.getAttribute("authUser") != null) {
			int userNo = ((UserVo)session.getAttribute("authUser")).getNo();
			String saveName = fileupService.restore(file, userNo);
			model.addAttribute("saveName",saveName);
			
			return "/fileup/result";
		} else {
			return "/user/loginForm";
		}
		
		
	}
}
