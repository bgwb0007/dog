package com.admin.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.Member;
import com.member.model.MemberDAO;
import com.member.model.MemberDAOImpl;
import com.product.model.ProductAdminCount;
import com.product.model.ProductDAO;
import com.product.model.ProductDAOImpl;

/**
 * Servlet implementation class AdminDashboardController
 */
@WebServlet("/admin/dashboard")
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		ProductAdminCount productAdminCount = dao.productAdminCount();
		
		request.setAttribute("cdates", productAdminCount.getCdate());
		System.out.println(productAdminCount.getCdate());
		request.setAttribute("counts", productAdminCount.getCount());
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
