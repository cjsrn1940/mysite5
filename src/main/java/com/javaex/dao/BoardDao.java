package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//전체 게시물 갯수 구하기
	public int selectTotalCnt(String keyword) {
		System.out.println("[BoardDao.selectTotalCnt()]");
		
		return sqlSession.selectOne("board.selectTotalCnt", keyword);
	}
	
	
	
	//게시판 페이징 연습용 리스트
	public List<BoardVo> selectList2(int startRnum, int endRnum, String keyword) {
		System.out.println("[BoardDao.selectList2()]");
		
		//System.out.println(startRnum);
		//System.out.println(endRnum);
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("startRnum", startRnum);
		pMap.put("endRnum", endRnum);
		pMap.put("keyword", keyword);
		
		System.out.println(pMap);
		
		return sqlSession.selectList("board.selectList2", pMap);
	}
	
	
	
	public int updateHit(int no) {
		System.out.println("[BoardDao.updateHit()]");
		
		return sqlSession.update("board.updateHit",no);
	}
	
	//글1개 정보 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("[BoardDao.selectBoard()]");
		
		return sqlSession.selectOne("board.selectBoard", no);
	}
	
	//리스트
	public List<BoardVo> boardList(String keyword) {
		System.out.println("[BoardDao.boardList()]");
		
		return sqlSession.selectList("board.selectBoardList", keyword);
	}
	
	//등록
	public int boardWrite(BoardVo baordVo) {
		System.out.println("[BoardDao.boardWrite()]");
		System.out.println(baordVo);
		int count = sqlSession.insert("board.boardWrite", baordVo);
		return count;
	}
	
	//삭제
	public int boardDelete(int no) {
		System.out.println("[BoardDao.boardDelete()]");
		
		int count = sqlSession.delete("board.boardDelete", no);
		
		return count;
	}
	
	//수정
	public int boardModify(BoardVo boardVo) {
		System.out.println("[BoardDao.boardModify()]");
		
		return sqlSession.update("board.boardModify", boardVo);
	}
 }
























