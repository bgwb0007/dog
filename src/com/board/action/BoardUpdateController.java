package com.board.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.Board;
import com.board.model.BoardDAO;
import com.board.model.BoardDAOImpl;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/board/boardupdate")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num =Integer.parseInt(request.getParameter("num"));
		BoardDAO sdao = BoardDAOImpl.getInstance();
		Board board = sdao.findById(num); // 상세보기
		request.setAttribute("board", board);
		request.getRequestDispatcher("boardUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 Board board =new Board();
		 board.setId(Integer.parseInt(request.getParameter("num")));
		 board.setContent(request.getParameter("content"));
		 board.setEmail(request.getParameter("email"));
		 board.setSubject(request.getParameter("subject"));
		 board.setUserid(request.getParameter("userid")); 
		 BoardDAO sdao = BoardDAOImpl.getInstance();
		 sdao.boardUpdate(board);
		 //response.sendRedirect("boardlist");//전체리스트로 가기
		 response.sendRedirect("boardDetail?num="+board.getId()); //상세페이지로
	}

}
