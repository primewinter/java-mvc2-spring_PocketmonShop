package com.model2.mvc.service.board;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Board;
import com.model2.mvc.service.domain.Cmt;

public interface BoardService {
	
	// �� ���
	public void addBoard(Board board) throws Exception;
	
	// �� ��ȸ
	public Board getBoard(int boardNo) throws Exception;
	
	// �� ���
	public Map<String, Object> getBoardList(Search search) throws Exception;
	
	
	// ��� ���
	public void addCmt(Cmt cmt) throws Exception;
	
	// ��� ��� ��ȸ
	public List<Cmt> getCmtList(int boardNo) throws Exception;

}
