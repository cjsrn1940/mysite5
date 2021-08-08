package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/gallery")

public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;

	//갤러리 리스트
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[GalleryController.list()]");
		
		List<GalleryVo> galleryList = galleryService.getGalleryList();
		model.addAttribute("gList", galleryList);
		
		return "/gallery/list";
	}
	
	
	//파일 업로드
	@RequestMapping(value="/uploadImg", method = {RequestMethod.GET, RequestMethod.POST})
	public String uploadImg(Model model, @RequestParam("file") MultipartFile file,
							@RequestParam("content") String content, HttpSession session) {
		System.out.println("[GalleryController.uploadImg()]");
		
		if(session.getAttribute("authUser") != null) {
			int userNo = ((UserVo)session.getAttribute("authUser")).getNo();
			String saveName = galleryService.restore(file, userNo, content);
			
			
			return "redirect:/gallery/list";
		} else {
			return "/user/loginForm";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
