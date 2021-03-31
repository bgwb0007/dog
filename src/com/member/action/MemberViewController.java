package com.member.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDAO;
import com.member.model.MemberDAOImpl;
import com.member.model.Member;

/**
 * Servlet implementation class MemberViewController
 */
@WebServlet("/member/view")
public class MemberViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션에서   userid 를 구해서 findById(userid)-->DAO 실행
		//memberview.jsp 로 감
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Member suser = (Member)session.getAttribute("user");
		String userid = suser.getUserid();
		MemberDAO dao = MemberDAOImpl.getInstance();
		Member member = dao.findById(userid);
		request.setAttribute("member", member);
		request.getRequestDispatcher("memberView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
