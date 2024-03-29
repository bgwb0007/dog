package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.member.model.MemberDAO;
import com.member.model.MemberDAOImpl;
import com.member.model.Member;

/**
 * Servlet implementation class UserDeleteController
 */
@WebServlet("/member/userDelete")
public class UserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 String userid = request.getParameter("userid");
		 MemberDAO dao =MemberDAOImpl.getInstance();
		 dao.memberDelete(userid); //삭제
		 ArrayList<Member> arr = dao.getMember(); //전체목록
		 int count = dao.memberCount();//회원수
		 //json 출력해야 함.
		 
		 //메인(루트)
		 JSONObject mainObj = new JSONObject();
		 //카운트
		 JSONObject countObj = new JSONObject();
		 countObj.put("count", count);
		 //회원목록
		 JSONArray jarr = new JSONArray();
		 for(Member dto : arr) {
			 String mode = dto.getAdmin()==1?"관리자":"일반회원";
			 JSONObject obj = new JSONObject();
			 obj.put("name", dto.getName());
			 obj.put("userid", dto.getUserid());
			 obj.put("email", dto.getEmail());
			 obj.put("phone", dto.getPhone());
			 obj.put("mode",  mode);
			 jarr.add(obj);
		 }
		 
		 //mainObj 담기
		 mainObj.put("jarr", jarr);
		 mainObj.put("countObj", countObj);
		 //json 출력
		 response.setContentType("application/json;charset=utf-8");
		 PrintWriter out = response.getWriter();
		 out.println(mainObj.toJSONString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
