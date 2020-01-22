package com.model2.mvc.web.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.board.BoardService;
import com.model2.mvc.service.domain.Board;
import com.model2.mvc.service.domain.Cmt;
import com.model2.mvc.service.domain.User;


@Controller
@ResponseBody
@RequestMapping("/board/json/*")
public class BoardRestController {
	
	@Autowired
	@Qualifier("boardServiceImpl")
	private BoardService boardService;
		
	public BoardRestController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping( value="addCmt" )
	public List<Cmt> addBoard( @RequestBody Map<String, Object> jsonMap) throws Exception {
	//public Cmt addBoard( @RequestParam(value="boardNo") int boardNo, @RequestParam(value="content") String content, @RequestParam(value="userId") String userId) throws Exception {

		System.out.println("/addCmt.do");
		ObjectMapper objectmapper = new ObjectMapper();
		Cmt cmt = objectmapper.convertValue(jsonMap.get("cmt"), Cmt.class);
		System.out.println("cmt :: "+cmt);
		//Business Logic
		/*
		 * System.out.println("boardNo :: "+boardNo);
		 * System.out.println("content :: "+content);
		 * System.out.println("userId :: "+userId);
		 * //System.out.println("작성자 :: "+session.getAttribute("user")); Cmt cmt = new
		 * Cmt(); cmt.setBoardNo(boardNo); cmt.setContent(content);
		 * cmt.setUserId(userId);
		 */
		boardService.addCmt(cmt);
		List<Cmt> cmtList = boardService.getCmtList(cmt.getBoardNo());
		
		return cmtList;
	}
	
	@RequestMapping( value="getBoard" )
	public String getBoard( @RequestParam("boardNo") int boardNo,  Model model) throws Exception {

		System.out.println("/getBoard.do");
		System.out.println("boardNo :: "+boardNo);
		Board board = boardService.getBoard(boardNo);
		model.addAttribute("board", board);
		return "forward:/board/getBoard.jsp";
	}
	
	@RequestMapping( value="getCmtList/{boardNo}" )
	public List<Cmt> getCmtList( @PathVariable("boardNo") int boardNo,  Model model) throws Exception {

		System.out.println("/getCmtList.do");
		System.out.println("boardNo :: "+boardNo);
		List<Cmt> cmt = boardService.getCmtList(boardNo);
		model.addAttribute("cmt", cmt);
		return cmt;
	}
	
	@RequestMapping( value="deleteCmt/{cmtNo}" )
	public void deleteCmt( @PathVariable("cmtNo") int cmtNo,  Model model) throws Exception {

		System.out.println("/deleteCmt.do");
		System.out.println("cmtNo :: "+cmtNo);
		//boardService.deleteCmt(cmtNo);
		
	}
	
	
	@RequestMapping(value="listBoard")
	public String listBasket( @ModelAttribute("search") Search search , Model model , HttpSession session, HttpServletRequest request) throws Exception{
		System.out.println("디버깅!!! : "+search.getCurrentPage());
		System.out.println("/listBoard.do");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		System.out.println("[pageSize] : "+pageSize);
		
		Map<String , Object> map=boardService.getBoardList(search);
		System.out.println("[currentPage] : "+search.getCurrentPage());
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("ㅎㅎㅎㅎㅎㅎㅎlistBoard 완료"+resultPage);
		
		return "forward:/board/listBoard.jsp";
	}
	
}