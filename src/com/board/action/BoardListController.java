package com.board.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.Board;
import com.board.model.BoardDAO;
import com.board.model.BoardDAOImpl;
import com.member.util.PageUtil;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/board/boardlist")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardDAO sdao = BoardDAOImpl.getInstance();
		String word=request.getParameter("word")==null?"":request.getParameter("word");
		String field =request.getParameter("field")==null?"subject":request.getParameter("field");
		String pageNum = request.getParameter("pageNum")==null?"1": request.getParameter("pageNum");
		
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int startRow = (currentPage-1)*pageSize+1;  //1   6
		int endRow = currentPage*pageSize;          //5   10
		
		ArrayList<Board> arr = sdao.boardList(startRow, endRow,field, word); //
		int count = sdao.boardCount(field, word);  //
		
		//총페이지수 
		int totPage = count/pageSize + (count%pageSize==0?0:1);
		int blockPage = 3; //[이전 ]  4 5 6 [다음]
		int startPage = ((currentPage-1)/blockPage)*blockPage+1;
		int endPage = startPage+blockPage-1;
		if(endPage>totPage) endPage = totPage;
		
		//번호
		int rowNo = count-(currentPage-1)*pageSize;
		
//		request.setAttribute("totPage", totPage);
//		request.setAttribute("blockPage", blockPage);
//		request.setAttribute("startPage", startPage);
//		request.setAttribute("endPage", endPage);
//		request.setAttribute("currentPage", currentPage);
		PageUtil page = new PageUtil();
		page.setBlockPage(blockPage);
		page.setCurrentPage(currentPage);
		page.setEndPage(endPage);
		page.setStartPage(startPage);
		page.setTotPage(totPage);
		page.setField(field);
		page.setWord(word);
		
		request.setAttribute("rowNo", rowNo);
		request.setAttribute("p", page);
		request.setAttribute("boards", arr);
		request.setAttribute("count", count);
		request.getRequestDispatcher("boardList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
