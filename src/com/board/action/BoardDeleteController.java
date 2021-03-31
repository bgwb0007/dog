package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDAOImpl;

/**
 * Servlet implementation class BoardDeleteController
 */
@WebServlet("/board/delete")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO sdao = BoardDAOImpl.getInstance();
		int flag=sdao.boardDelete(num);
//		if(flag==0) {// 삭제오류(삭제 안됨)
//			response.setContentType("text/html;charset=utf-8");
//			PrintWriter out =response.getWriter();
//			out.println("<script>");
//			out.println("alert('삭제할 수 없습니다.')");
//			out.println("history.back()");
//			out.println("</script>");
//			
//		}else {
//			response.sendRedirect("boardlist");
//		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		out.println(flag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
