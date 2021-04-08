package com.chat.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chat.model.Chat;
import com.chat.model.ChatDAO;
import com.chat.model.ChatDAOImpl;
import com.chat.model.Message;
import com.member.model.Member;
import com.product.model.Product;
import com.product.model.ProductDAO;
import com.product.model.ProductDAOImpl;

/**
 * Servlet implementation class ChatOnController
 */
@WebServlet("/chat/on")
public class ChatOnController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatOnController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		int chatid = Integer.parseInt(request.getParameter("chatid"));
		Member user = (Member) session.getAttribute("user");
		String temp = "'"+user.getUserid()+"'";
		// 채팅방 정보 찾기
		ChatDAO dao = ChatDAOImpl.getInstance();
		Chat chat = dao.findById(chatid);

		// 이전메시지 가져오기
		ArrayList<Message> messages = dao.messageFindAll(chat.getId());

		request.setAttribute("me", temp);
		request.setAttribute("chat", chat);
		request.setAttribute("messages", messages);

		request.getRequestDispatcher("chatRoom.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
