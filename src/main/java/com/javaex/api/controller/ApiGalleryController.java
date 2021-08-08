package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value = "/api/gallery")
public class ApiGalleryController {

	@Autowired
	private GalleryService galleryService;
	
	//ajax방식으로 이미지모달창 띄우기
	@ResponseBody
	@RequestMapping(value="/showImg", method = {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo showImg(@RequestParam("no") int no) {
		System.out.println("[ApiGalleryController.showImg()]");
		
		System.out.println(no);
		GalleryVo imgInfoVo = galleryService.getImgInfo(no);
		
		return imgInfoVo;
		
	}
	
	//ajax방식으로 이미지모달창 띄우기
		@ResponseBody
		@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
		public int delete(@RequestParam("no") int no) {
			System.out.println("[ApiGalleryController.delete()]");
			
			System.out.println(no);
			int count = galleryService.imgDelete(no);
			
			
			return count;
			
		}
}












