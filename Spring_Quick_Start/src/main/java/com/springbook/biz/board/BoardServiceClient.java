package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {

	public static void main(String[] args) {
		// 1. Spring 컨테이너 구동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. Spring 컨테이너로부터 BoardServiceImpl 객체를 Lookup
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		// 3. 글 동록 테스트
		BoardVO vo = new BoardVO();
		vo.setTitle("간다6");
		vo.setWriter("sjacob6");
		vo.setContent("달려가보자6");
		boardService.insertBoard(vo);
		
		// 4. 글 목록 검색 기능 테스트
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for (BoardVO board : boardList) {
			System.out.println("---> " + board.toString());
		}
		
		// 5. Spring 컨데이너 종료
		container.close();
	}

}