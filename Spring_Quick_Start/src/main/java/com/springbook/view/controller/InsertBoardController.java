package com.springbook.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

public class InsertBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===> InsertBoardController 글 등록 처리");
		
		// 1. 사용자 입력 정보 추출 
		String title = request.getParameter("title"); 
		String writer = request.getParameter("writer"); 
		String content = request.getParameter("content");
		
		// 2. DB 연동 처리
		BoardVO vo = new BoardVO(); 
		vo.setTitle(title);
		vo.setWriter(writer); 
		vo.setContent(content);
		 
		BoardDAO boardDAO = new BoardDAO(); 
		boardDAO.insertBoard(vo);
		
		// 3. 화면 네비게이션 
		return "getBoardList.do";
	}
}
