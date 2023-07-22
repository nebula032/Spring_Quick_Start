package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.Log4jAdvice;
import com.springbook.biz.common.LogAdvice;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAOSpring boardDAO;
	// AOP 설정 전
//	private LogAdvice log;
//	private Log4jAdvice log;
		
	public BoardServiceImpl() {
//		log = new LogAdvice();
//		log = new Log4jAdvice();
	}

	@Override
	public void insertBoard(BoardVO vo) {
//		if (vo.getSeq() == 0) {
//			throw new IllegalArgumentException("0번 글은 등록할 수 없습니다.");
//		}
//		log.printLog();
//		log.printLogging();
		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
//		log.printLog();
//		log.printLogging();
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
//		log.printLog();
//		log.printLogging();
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
//		log.printLog();
//		log.printLogging();
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
//		log.printLog();
//		log.printLogging();
		return boardDAO.getBoardList(vo);
	}

}
