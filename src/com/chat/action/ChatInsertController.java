package com.chat.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chat.model.Chat;
import com.chat.model.ChatDAO;
import com.chat.model.ChatDAOImpl;
import com.member.model.Member;
import com.product.model.Product;

/**
 * Servlet implementation class ChatInsertController
 */
@WebServlet("/chat/insert")
public class ChatInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Product product = (Product) session.getAttribute("product");
		Member member = (Member) session.getAttribute("user");
		Chat chat = new Chat();
		chat.setBuyerid(member.getUserid());
		chat.setSellerid(product.getUserid());
		chat.setProductid(product.getId());
		
		SimpleDateFormat format1 = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
		Date now = new Date();
		String format_time1 = format1.format(now.getTime());
		chat.setCreateddate(format_time1);
		
		chat.setProductname(product.getName());
		chat.setProductcategory(product.getCategory());
		
		ChatDAO dao = ChatDAOImpl.getInstance();
		
		if (dao.checkCreated(chat)) {  // 이미 채팅방 있으면 생성 안함 (중복확인) 없으면 false
			response.sendRedirect("list");
		}else {
			dao.chatInsert(chat);
			response.sendRedirect("list");
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
