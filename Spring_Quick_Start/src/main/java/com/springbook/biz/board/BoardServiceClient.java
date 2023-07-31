package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.board.impl.BoardDAOMybatis;
import com.springbook.biz.board.impl.BoardDAOMybatis2;

public class BoardServiceClient {

	public static void main(String[] args) {
		/*
		 * // 1. Spring 컨테이너 구동 AbstractApplicationContext container = new
		 * GenericXmlApplicationContext("applicationContext.xml");
		 * 
		 * // 2. Spring 컨테이너로부터 BoardServiceImpl 객체를 Lookup BoardService boardService =
		 * (BoardService) container.getBean("boardService");
		 * 
		 * // 3. 글 동록 테스트 BoardVO vo = new BoardVO(); vo.setSeq(103);
		 * vo.setTitle("간다8"); vo.setWriter("sjacob8"); vo.setContent("달려가보자8");
		 * //boardService.insertBoard(vo);
		 * 
		 * // 4. 글 목록 검색 기능 테스트 List<BoardVO> boardList = boardService.getBoardList(vo);
		 * for (BoardVO board : boardList) { System.out.println("---> " +
		 * board.toString()); }
		 * 
		 * // 5. Spring 컨데이너 종료 container.close();
		 */
		
		BoardDAOMybatis2 boardDAO = new BoardDAOMybatis2();
		
		BoardVO vo = new BoardVO();
		vo.setTitle("mybatis1");
		vo.setWriter("mybatis1");
		vo.setContent("mybatis1");
		boardDAO.insertBoard(vo);
		
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		for (BoardVO board : boardList) {
			System.out.println("--> " + board.toString());
		}
	}

}