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
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}
	
	// 글 등록
	@RequestMapping(value = "SpringMVC/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IOException {
		System.out.println("===> InsertBoardController(어노테이션) 글 등록 처리");
		// 파일 업로드
		MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("D:/" + fileName));
		}
		boardServie.insertBoard(vo);
		return "redirect:getBoardList.do";

	}
	
	// 글 수정
	@RequestMapping("SpringMVC/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("===> UpdateBoardController(어노테이션) 글 수정 처리");
		System.out.println("번호 : " + vo.getSeq());
		System.out.println("제목: " + vo.getTitle());
		System.out.println("작성자 : " + vo.getWriter());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("등록일 : " + vo.getRegDate());
		System.out.println("조회수 : " + vo.getCnt());
		
		boardServie.updateBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	// 글 삭제
	@RequestMapping("SpringMVC/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println("===> DeleteBoardController(어노테이션) 글 삭제 처리");
		
		boardServie.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	// 글 목록
	@RequestMapping("SpringMVC/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		System.out.println("===> GetBoardListController(어노테이션) 글 목록 검색 처리");
		
		// null 체크
		if (vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if (vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		System.out.println("검색 조건 : " + vo.getSearchCondition());
		System.out.println("검색 단어 : " + vo.getSearchKeyword());
		
		// model 정보 저장
		model.addAttribute("boardList", boardServie.getBoardList(vo));
		model.addAttribute("boardListCount", boardServie.getBoardList(vo).size());
		return "getBoardList.jsp";
	}
	
	// 글 상세 목록
	@RequestMapping("SpringMVC/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println("===> GetBoardController(어노테이션) 글 목록 검색 처리");
		
		model.addAttribute("board", boardServie.getBoard(vo));
		return "getBoard.jsp";
	}
}
