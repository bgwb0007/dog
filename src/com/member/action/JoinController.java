package com.member.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class JoinController
 */
@WebServlet("/member/join")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("join.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Member member = new Member();
		member.setAdmin(Integer.parseInt(request.getParameter("admin")));
		member.setEmail(request.getParameter("email"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		
		String userid = request.getParameter("userid");
		String encPwd = SHA256.getEncrypt(request.getParameter("pwd"), userid);
		//member.setPwd(request.getParameter("pwd"));
		member.setPwd(encPwd); //암호화된 비번
		member.setUserid(userid);
		
		//생성일 저장
		SimpleDateFormat format1 = new SimpleDateFormat ( "YY-MM-dd HH:mm:ss");
		Date now = new Date();
		String format_time1 = format1.format(now.getTime());
		member.setCreatedDate(format_time1);
		
		
		MemberDAO dao = MemberDAOImpl.getInstance();
		dao.memberJoin(member);
		response.sendRedirect("login");
	}

}
