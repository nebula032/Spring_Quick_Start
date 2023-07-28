package com.springbook.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
       
    public void init() throws ServletException {
    	handlerMapping = new HandlerMapping();
    	viewResolver = new ViewResolver();
    	viewResolver.setPrefix("./");
    	viewResolver.setSuffix(".jsp");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. Ŭ���̾�Ʈ�� ��û path ������ �����Ѵ�.
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println("url : " + url);
		System.out.println("uri : " + uri);
		System.out.println("path : " + path);
		
		/* // Model2(MVC) ���
		 * // 2. Ŭ���̾�Ʈ�� ��û path�� ���� ������ �б�ó�� �Ѵ�. if (path.equals("/login.do")) {
		 * 
		 * System.out.println("�α��� ó��");
		 * 
		 * // 1. ����� �Է� ���� ���� String id = request.getParameter("id"); String password =
		 * request.getParameter("password");
		 * 
		 * // 2. DB ���� ó�� UserVO vo = new UserVO(); vo.setId(id);
		 * vo.setPassword(password);
		 * 
		 * UserDAO userDAO = new UserDAO(); UserVO user = userDAO.getUser(vo);
		 * 
		 * // 3. ȭ�� �׺���̼� if (user != null) { System.out.println("�α��� ����");
		 * response.sendRedirect("getBoardList.do"); } else {
		 * System.out.println("�α��� ����"); response.sendRedirect("login.jsp"); }
		 * 
		 * } else if (path.equals("/logout.do")) {
		 * 
		 * System.out.println("�α׾ƿ� ó��");
		 * 
		 * // 1. �������� ����� ���� ��ü�� ���� �����Ѵ�. HttpSession session = request.getSession();
		 * session.invalidate();
		 * 
		 * // 2. ���� ���� ��, ���� ȭ������ �̵��Ѵ�. response.sendRedirect("login.jsp");
		 * 
		 * } else if (path.equals("/insertBoard.do")) {
		 * 
		 * System.out.println("�� ��� ó��");
		 * 
		 * // 1. ����� �Է� ���� ���� String title = request.getParameter("title"); String
		 * writer = request.getParameter("writer"); String content =
		 * request.getParameter("content");
		 * 
		 * // 2. DB ���� BoardVO vo = new BoardVO(); vo.setTitle(title);
		 * vo.setWriter(writer); vo.setContent(content);
		 * 
		 * BoardDAO boardDAO = new BoardDAO(); boardDAO.insertBoard(vo);
		 * 
		 * // 3. ȭ�� �׺���̼� response.sendRedirect("getBoardList.do");
		 * 
		 * } else if (path.equals("/updateBoard.do")) {
		 * 
		 * System.out.println("�� ���� ó��");
		 * 
		 * // 1. ����� �Է� ���� ���� String title = request.getParameter("title"); String
		 * content = request.getParameter("content"); String seq =
		 * request.getParameter("seq");
		 * 
		 * // 2. DB ���� BoardVO vo = new BoardVO(); vo.setTitle(title);
		 * vo.setContent(content); vo.setSeq(Integer.parseInt(seq));
		 * 
		 * BoardDAO boardDAO = new BoardDAO(); boardDAO.updateBoard(vo);
		 * 
		 * // 3. ȭ�� �׺���̼� response.sendRedirect("getBoardList.do");
		 * 
		 * } else if (path.equals("/deleteBoard.do")) {
		 * 
		 * System.out.println("�� ���� ó��");
		 * 
		 * // 1. ����� �Է� ���� ���� String seq = request.getParameter("seq");
		 * 
		 * // 2. DB ���� BoardVO vo = new BoardVO(); vo.setSeq(Integer.parseInt(seq));
		 * 
		 * BoardDAO boardDAO = new BoardDAO(); boardDAO.deleteBoard(vo);
		 * 
		 * // 3. ȭ�� �׺���̼� response.sendRedirect("getBoardList.do");
		 * 
		 * } else if (path.equals("/getBoard.do")) {
		 * 
		 * System.out.println("�� �� ��ȸ ó��");
		 * 
		 * // 1. �˻��� �Խñ� ��ȣ ���� String seq = request.getParameter("seq");
		 * 
		 * // 2. DB ���� BoardVO vo = new BoardVO(); vo.setSeq(Integer.parseInt(seq));
		 * 
		 * BoardDAO boardDAO = new BoardDAO(); BoardVO board = boardDAO.getBoard(vo);
		 * 
		 * // 3. ���� ȭ�� ���� HttpSession session = request.getSession();
		 * session.setAttribute("board", board); response.sendRedirect("getBoard.jsp");
		 * 
		 * } else if (path.equals("/getBoardList.do")) {
		 * 
		 * System.out.println("�� ��� �˻� ó��");
		 * 
		 * // 1. ����� �Է� ���� ���� // 2. DB ���� ó�� BoardVO vo = new BoardVO(); BoardDAO
		 * boardDAO = new BoardDAO(); List<BoardVO> boardList =
		 * boardDAO.getBoardList(vo);
		 * 
		 * // 3. �˻� ����� ���ǿ� �����ϰ� ��� ȭ������ �̵��Ѵ�. HttpSession session =
		 * request.getSession(); session.setAttribute("boardList", boardList);
		 * response.sendRedirect("getBoardList.jsp");
		 * 
		 * }
		 */
		
		// MVC �����ӿ�ũ ���
		// 2. HandlerMapping�� ���� path�� �ش��ϴ� Controller�� �˻��Ѵ�.
		Controller ctrl = handlerMapping.getController(path);
		
		// 3. �˻���  Controller�� �����Ѵ�.
		String viewName = ctrl.handleRequest(request, response);
		System.out.println("viewName : " + viewName);
		
		// 4. ViewResolver�� ���� viewName�� �ش��ϴ� ȭ���� �˻��Ѵ�.
		String view = null;
		if (!viewName.contains(".do")) {
			view = viewResolver.getView(viewName);
		} else {
			view = viewName;
		}
		
		// 5. �˻��� ȭ������ �̵��Ѵ�.
		System.out.println("view : " + view);
		response.sendRedirect(view);
		
	}
}
