package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.util.SqlSessionFactoryBean;

//@Repository
public class BoardDAOMybatis {
	/*
	 * private SqlSession mybatis;
	 * 
	 * public BoardDAOMybatis() { //mybatis =
	 * SqlSessionFactoryBean.getSqlSessionInstance(); }
	 * 
	 * public void insertBoard(BoardVO vo) {
	 * mybatis.insert("BoardDAOMybatis.insertBoard", vo); mybatis.commit(); }
	 * 
	 * public void updateBoard(BoardVO vo) {
	 * mybatis.insert("BoardDAOMybatis.updateBoard", vo); mybatis.commit(); }
	 * 
	 * public void deleteBoard(BoardVO vo) {
	 * mybatis.insert("BoardDAOMybatis.deleteBoard", vo); mybatis.commit(); }
	 * 
	 * public BoardVO getBoard(BoardVO vo) { return
	 * mybatis.selectOne("BoardDAOMybatis.getBoard", vo); }
	 * 
	 * public List<BoardVO> getBoardList(BoardVO vo) { return
	 * mybatis.selectList("BoardDAOMybatis.getBoardList", vo); }
	 */
}
