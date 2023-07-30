package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Controller
@SessionAttributes("board")
public class BoardController {
	
	@Autowired
	private BoardService boardServie;
	
	@RequestMapping("SpringMVC/dataTransform.do")
	@ResponseBody
	public BoardListVO dataTransform(BoardVO vo) {
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardServie.getBoardList(vo);
		BoardListVO boardListVo = new BoardListVO();
		boardListVo.setBoardList(boardList);
		return boardListVo;
	}
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("����", "TITLE");
		conditionMap.put("����", "CONTENT");
		return conditionMap;
	}
	
	// �� ���
	@RequestMapping(value = "SpringMVC/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IOException {
		System.out.println("===> InsertBoardController(������̼�) �� ��� ó��");
		// ���� ���ε�
		MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("D:/" + fileName));
		}
		boardServie.insertBoard(vo);
		return "redirect:getBoardList.do";

	}
	
	// �� ����
	@RequestMapping("SpringMVC/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("===> UpdateBoardController(������̼�) �� ���� ó��");
		System.out.println("��ȣ : " + vo.getSeq());
		System.out.println("����: " + vo.getTitle());
		System.out.println("�ۼ��� : " + vo.getWriter());
		System.out.println("���� : " + vo.getContent());
		System.out.println("����� : " + vo.getRegDate());
		System.out.println("��ȸ�� : " + vo.getCnt());
		
		boardServie.updateBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	// �� ����
	@RequestMapping("SpringMVC/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println("===> DeleteBoardController(������̼�) �� ���� ó��");
		
		boardServie.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	// �� ���
	@RequestMapping("SpringMVC/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		System.out.println("===> GetBoardListController(������̼�) �� ��� �˻� ó��");
		
		// null üũ
		if (vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if (vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		System.out.println("�˻� ���� : " + vo.getSearchCondition());
		System.out.println("�˻� �ܾ� : " + vo.getSearchKeyword());
		
		// model ���� ����
		model.addAttribute("boardList", boardServie.getBoardList(vo));
		model.addAttribute("boardListCount", boardServie.getBoardList(vo).size());
		return "getBoardList.jsp";
	}
	
	// �� �� ���
	@RequestMapping("SpringMVC/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println("===> GetBoardController(������̼�) �� ��� �˻� ó��");
		
		model.addAttribute("board", boardServie.getBoard(vo));
		return "getBoard.jsp";
	}
}
