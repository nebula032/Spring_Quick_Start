package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.util.SqlSessionFactoryBean;

@Repository
public class BoardDAOMybatis2 extends SqlSessionDaoSupport {

	@Autowired 
	private SqlSessionTemplate mybatis;

//	@Autowired 
//	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) { 
//		super.setSqlSessionFactory(sqlSessionFactory); 
//	}
	
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis��  insertBoard() ��� ó��");
		mybatis.insert("BoardDAOMybatis.insertBoard", vo);
	}

	public void updateBoard(BoardVO vo) {
		System.out.println("===> Mybatis��  updateBoard() ��� ó��");
		mybatis.update("BoardDAOMybatis.updateBoard", vo);
	}

	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Mybatis��  deleteBoard() ��� ó��");
		mybatis.delete("BoardDAOMybatis.deleteBoard", vo);
	}

	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis��  getBoard() ��� ó��");
		return mybatis.selectOne("BoardDAOMybatis.getBoard", vo);
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Mybatis��  getBoardList() ��� ó��");
		return mybatis.selectList("BoardDAOMybatis.getBoardList", vo);
	}
}
