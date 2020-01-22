package com.model2.mvc.service.board;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Board;
import com.model2.mvc.service.domain.Cmt;

public interface BoardService {
	
	// 글 등록
	public void addBoard(Board board) throws Exception;
	
	// 글 조회
	public Board getBoard(int boardNo) throws Exception;
	
	// 글 목록
	public Map<String, Object> getBoardList(Search search) throws Exception;
	
	
	// 댓글 등록
	public void addCmt(Cmt cmt) throws Exception;
	
	// 댓글 목록 조회
	public List<Cmt> getCmtList(int boardNo) throws Exception;

}
