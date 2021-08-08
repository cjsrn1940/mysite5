package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//게시판 페이징 연습용 리스트
	@RequestMapping(value="/board/list2", method = {RequestMethod.GET, RequestMethod.POST})
	public String list2(Model model, 
						@RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage,
						@RequestParam(value= "keyword", required = false, defaultValue = "") String keyword) {
		System.out.println("[BoardController.list2()]");
		
 		Map<String, Object> listMap = boardService.getList2(crtPage, keyword);
 		
 		
 		System.out.println(listMap);
		
		
		model.addAttribute("listMap", listMap);
		
		return "/board/list2";
	}
	
	
	
	//읽기
	@RequestMapping(value="/board/read", method = {RequestMethod.GET, RequestMethod.POST}) // method get, post를 둘 다 쓰는 경우에는 생략가능, @RequestMapping("/board/read")와 같은 기능
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("[BoardController.read()]");
		
		BoardVo boardVo = boardService.getBoard(no);
		System.out.println(boardVo);
		
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}
	
	
	//리스트
	@RequestMapping("/board/list")
	public String list(Model model, @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
		System.out.println("[BoardController.list()]");
		System.out.println(keyword);
		
		List<BoardVo> boardList = boardService.boardList(keyword);
		
		model.addAttribute("bList", boardList);
		
		return "board/list";
	}
	
	/*
	//삭제
	@RequestMapping(value="/board/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete() {
		System.out.println("[BoardController.delete()]");
	}
	*/
	
	//글등록폼
	@RequestMapping(value="/board/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(HttpSession session) {
		System.out.println("[BoardController.writeForm()]");
		
		if(session.getAttribute("authUser") != null) {
			return "board/writeForm";
		} else {
			return "redirect:/user/loginForm";
		}
		
	}
	
	//글등록
	@RequestMapping(value="/board/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(HttpSession session, @ModelAttribute BoardVo boardVo) {
		System.out.println("[BoardController.write()]");
		
		int no = ((UserVo)session.getAttribute("authUser")).getNo();
		boardVo.setUserNo(no);
		
		boardService.boardWrite(boardVo);
		
		return "redirect:/board/list";
	}
	
	//삭제
	@RequestMapping(value="/board/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("[BoardController.delete()]");
		
		boardService.boardDelete(no);
		
		return "redirect:/board/list";
	}
	
	
	//수정폼
	@RequestMapping(value="/board/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, @RequestParam("no") int no, HttpSession session) {
		System.out.println("[BoardController.modifyForm()]");
		
		//현재글의 작성자 번호 == 세션에 있는 번호 ==> 정상인경우 리스트 보내기
		BoardVo boardVo = boardService.getModifyForm(no);
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//로그인 안한경우 --> 메인
		if(authUser == null) {
			System.out.println("로그인 안한경우");
			return "redirect:/user/loginForm";
		}
		
		if(authUser.getNo() == boardVo.getUserNo()) { //로그인한 사용자 == 글작성자
			System.out.println("자신의 글인 경우 --> 수정폼 포워드");
			
			model.addAttribute("boardVo", boardVo);
			return "board/modifyForm";
		} else {
			System.out.println("다른사람 글인 경우");
			
			return "redirect:/board/list";
		}
		
	}
	
	//수정
	@RequestMapping(value="/board/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("[BoardController.modify()]");
			
		boardService.boardModify(boardVo);
		
		return "redirect:/board/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
