package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//게시판 페이징 연습용 리스트
	public  Map<String, Object> getList2(int crtPage, String keyword) {
		System.out.println("[BoardService.getList2()]");
		System.out.println(crtPage);
		
		////////////////////////////////////////////////////
		//// 리스트 가져오기
		////////////////////////////////////////////////////
		
		int listCnt = 10; 
		
		
		//crtPage 계산(- 값일 때 --> 1page 처리)
		crtPage = (crtPage > 0) ? crtPage : (crtPage=1);
		
		
		//시작번호 계산하기
		int startRnum = (crtPage - 1)*listCnt + 1; 
				
		//끝번호 계산하기
		int endRnum = (startRnum + listCnt) - 1;
		
		List<BoardVo> boardList = boardDao.selectList2(startRnum, endRnum, keyword);
		
				
		////////////////////////////////////////////////////
		//// 페이징 계산
		////////////////////////////////////////////////////
		
		
		//전체글 갯수
		int totalCount = boardDao.selectTotalCnt(keyword);
		System.out.println(totalCount);
		
		//페이지당 버틍 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil((crtPage/(double)pageBtnCount))*pageBtnCount;
		
		/*
			1/5.0 --> 0.2 --> 올림			0.2*5 --> 1.0
			2/5.0 --> 0.4 --> 올림			0.4*5 --> 2.0
			3/5.0 --> 0.6 --> 올림			0.6*5 --> 3.0
			4/5.0 --> 0.8 --> 올림			0.8*5 --> 4.0
			5/5.0 --> 1.0 --> 올림			1.0*5 --> 5.0
			
			1/5.0 --> 0.2 --> 올림 1.0		1.0*5 --> 5.0		
			2/5.0 --> 0.4 --> 올림 1.0		1.0*5 --> 5.0
			3/5.0 --> 0.6 --> 올림 1.0		1.0*5 --> 5.0
			4/5.0 --> 0.8 --> 올림 1.0		1.0*5 --> 5.0
			5/5.0 --> 1.0 --> 올림 1.0		1.0*5 --> 5.0
		*/
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		//다음 화살표 표현 유무
		boolean next = false;
		if((endPageBtnNo * listCnt) < totalCount) {
			next = true;
		} else {
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		}
		
		//이전 화살표 표현 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
				
		////////////////////////////////////////////////////
		//// 맵으로 매핑하기
		////////////////////////////////////////////////////

		//리턴하기
		Map<String, Object> listMap = new HashMap<String, Object>();
		
		listMap.put("boardList", boardList);
		listMap.put("prev", prev);
		listMap.put("startPageBtnNo", startPageBtnNo);
		listMap.put("endPageBtnNo", endPageBtnNo);
		listMap.put("next", next);
		
		
		return listMap;
	}
	
	
	public BoardVo getBoard(int no) {
		System.out.println("[BoardService.getBoard()]");
		
		//조회수 올리기
		boardDao.updateHit(no);
		return boardDao.selectBoard(no);
	}
	
	public List<BoardVo> boardList(String keyword) {
		System.out.println("[BoardService.boarList()]");
		
		return boardDao.boardList(keyword);
		
	}
	
	public int boardWrite(BoardVo boardVo) {
		System.out.println("[BoardService.boardWrite()]");
		
		int count = boardDao.boardWrite(boardVo);
		
		return count;
	}

	public int boardDelete(int no) {
		System.out.println("[BoardService.boardDelete()]");
		
		return boardDao.boardDelete(no);
	}
	
	public BoardVo getModifyForm(int no) {
		System.out.println("[BoardService.getModifyForm()]");
		
		return boardDao.selectBoard(no);
	}
	
	//글수정
	public int boardModify(BoardVo baordVo) {
		System.out.println("[BoardService.boardModify()]");
	
		return boardDao.boardModify(baordVo);
	}
}



















