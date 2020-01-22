package com.model2.mvc.service.board.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.board.BoardDao;
import com.model2.mvc.service.domain.Board;
import com.model2.mvc.service.domain.Cmt;

@Repository("boardDaoImpl")
public class BoardDaoImpl implements BoardDao{

	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// Constructor
	public BoardDaoImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addBoard(Board board) throws Exception {
		sqlSession.insert("BoardMapper.addBoard", board);
	}

	@Override
	public List<Board> getBoardList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList("BoardMapper.getBoardList", map);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("BoardMapper.getTotalCount", map);
	}
	
	public Board getBoard(int boardNo) throws Exception {
		return sqlSession.selectOne("BoardMapper.getBoard", boardNo);
	}
	
	public void addCmt(Cmt cmt) throws Exception {
		sqlSession.insert("BoardMapper.addCmt", cmt);
	}
	
	public List<Cmt> getCmtList(int boardNo) throws Exception {
		return sqlSession.selectList("BoardMapper.getCmtList", boardNo);
	}

}
