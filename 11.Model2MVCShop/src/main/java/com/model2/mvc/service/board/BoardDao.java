package com.model2.mvc.service.board;

import java.util.List;
import java.util.Map;

import com.model2.mvc.service.domain.Board;
import com.model2.mvc.service.domain.Cmt;

public interface BoardDao {
	
	// insert
	public void addBoard(Board board) throws Exception;
	
	// select list
	public List<Board> getBoardList(Map<String, Object> map) throws Exception;
	
	// �Խ��� Page ó���� ���� ��ü Row(totalCount) return
	public int getTotalCount(Map<String, Object> map) throws Exception;
	
	
	public Board getBoard(int boardNo) throws Exception;
	
	public void addCmt(Cmt cmt) throws Exception;
	
	public List<Cmt> getCmtList(int boardNo) throws Exception;

}
