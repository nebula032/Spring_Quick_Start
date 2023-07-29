package com.springbook.view.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board")
public class BoardController {
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("����", "TITLE");
		conditionMap.put("����", "CONTENT");
		return conditionMap;
	}
	
	// �� ���
	@RequestMapping(value = "model2(MVC)/insertBoard.do")
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println("===> InsertBoardController(������̼�) �� ��� ó��");
		
		boardDAO.insertBoard(vo);
		return "redirect:getBoardList.do";

	}
	
	// �� ����
	@RequestMapping("SpringMVC/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo, BoardDAO boardDAO) {
		System.out.println("===> UpdateBoardController(������̼�) �� ���� ó��");
		System.out.println("��ȣ : " + vo.getSeq());
		System.out.println("����: " + vo.getTitle());
		System.out.println("�ۼ��� : " + vo.getWriter());
		System.out.println("���� : " + vo.getContent());
		System.out.println("����� : " + vo.getRegDate());
		System.out.println("��ȸ�� : " + vo.getCnt());
		
		boardDAO.updateBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	// �� ����
	@RequestMapping("model2(MVC)/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println("===> DeleteBoardController(������̼�) �� ���� ó��");
		
		boardDAO.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	// �� ���
	@RequestMapping("SpringMVC/getBoardList.do")
	public String getBoardList(BoardVO vo, BoardDAO boardDAO, Model model, 
			@RequestParam(value="searchCondition", defaultValue = "TITLE", required = false) String condition,
			@RequestParam(value="searchKeyword", defaultValue = "", required = false) String keyword) {
		System.out.println("===> GetBoardListController(������̼�) �� ��� �˻� ó��");
		System.out.println("�˻� ���� : " + condition);
		System.out.println("�˻� �ܾ� : " + keyword);
		
		model.addAttribute("boardList", boardDAO.getBoardList(vo));
		return "getBoardList.jsp";
	}
	
	// �� �� ���
	@RequestMapping("SpringMVC/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
		System.out.println("===> GetBoardController(������̼�) �� ��� �˻� ó��");
		
		model.addAttribute("board", boardDAO.getBoard(vo));
		return "getBoard.jsp";
	}
}
