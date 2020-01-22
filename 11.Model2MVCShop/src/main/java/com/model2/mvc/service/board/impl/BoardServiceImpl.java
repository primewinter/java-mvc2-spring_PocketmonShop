package com.model2.mvc.service.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.board.BoardDao;
import com.model2.mvc.service.board.BoardService;
import com.model2.mvc.service.domain.Board;
import com.model2.mvc.service.domain.Cmt;

@Service("boardServiceImpl")
public class BoardServiceImpl implements BoardService {

	
	@Autowired
	@Qualifier("boardDaoImpl")
	private BoardDao boardDao;
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	public BoardServiceImpl() {
		System.out.println(this.getClass());
	}

	public void addBoard(Board board) throws Exception {
		boardDao.addBoard(board);
	}

	public Board getBoard(int boardNo) throws Exception {
		return boardDao.getBoard(boardNo);
	}
	
	public Map<String, Object> getBoardList(Search search) throws Exception {
		Map<String, Object> dbMap = new HashMap<String, Object>();
		dbMap.put("search", search);
		
		List<Board> list = (List<Board>) boardDao.getBoardList(dbMap);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("search", search);
		int totalCount = boardDao.getTotalCount(result);
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public void addCmt(Cmt cmt) throws Exception {
		boardDao.addCmt(cmt);
	}
	
	public List<Cmt> getCmtList(int boardNo) throws Exception {
		return boardDao.getCmtList(boardNo);
	}
	
}
