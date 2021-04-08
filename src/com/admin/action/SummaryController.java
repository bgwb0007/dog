package com.admin.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chat.model.ChatDAO;
import com.chat.model.ChatDAOImpl;
import com.product.model.Product;
import com.product.model.ProductDAO;
import com.product.model.ProductDAOImpl;

/**
 * Servlet implementation class SummaryController
 */
@WebServlet("/admin/summary")
public class SummaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SummaryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		ChatDAO cdao = ChatDAOImpl.getInstance();
		int chatAll = cdao.countAllChat();
		int chatToday = cdao.countTodayChat();
		int messageAll = cdao.countAllMessage();
		int messageToday = cdao.countTodayMessage();
		
		request.setAttribute("chatAll", chatAll);
		request.setAttribute("chatToday", chatToday);
		request.setAttribute("messageAll", messageAll);
		request.setAttribute("messageToday", messageToday);
		
		request.getRequestDispatcher("summary.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
