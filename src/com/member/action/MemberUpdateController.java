package com.member.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDAOImpl;
import com.member.model.Member;
import com.member.util.SHA256;

/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/member/update")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정후 로그인
		request.setCharacterEncoding("utf-8");
		Member member = new Member();
		member.setAdmin(Integer.parseInt(request.getParameter("admin")));
		member.setEmail(request.getParameter("email"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String encPwd = SHA256.getEncrypt(pwd, userid);
		member.setPwd(encPwd);
		member.setUserid(userid);
		MemberDAO dao = MemberDAOImpl.getInstance();
		dao.memberUpdate(member); // 
		response.sendRedirect("login");
		
		
	}

}
