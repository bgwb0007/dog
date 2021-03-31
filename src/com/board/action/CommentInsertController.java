package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.model.CommentDTO;
import com.board.model.BoardDAO;
import com.board.model.BoardDAOImpl;
import com.member.model.Member;

/**
 * Servlet implementation class CommentInsertController
 */
@WebServlet("/board/commentInsert")
public class CommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("utf-8");
	  String msg = request.getParameter("msg");
	  int bnum =Integer.parseInt(request.getParameter("bnum"));
	  HttpSession session = request.getSession();
	  Member user = (Member)session.getAttribute("user");
	  if(user==null) { //·Î±×ÀÎ ¾ÈµÊ
		 response.setCharacterEncoding("urf-8"); 
		 PrintWriter out = response.getWriter();
		 out.println("1");
	  }else { //·Î±×ÀÎ µÊ
		  BoardDAO sdao = BoardDAOImpl.getInstance();
		  CommentDTO comment = new CommentDTO();
		  comment.setBnum(bnum);
		  comment.setMsg(msg);
		  comment.setUserid(user.getUserid());
		  sdao.commentInsert(comment);
	  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
