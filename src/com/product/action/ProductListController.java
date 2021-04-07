package com.product.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.Member;
import com.member.model.MemberDAO;
import com.member.model.MemberDAOImpl;
import com.member.util.SHA256;
import com.product.model.Product;
import com.product.model.ProductDAO;
import com.product.model.ProductDAOImpl;

/**
 * Servlet implementation class ProductListController
 */
@WebServlet("/product/plist")
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductListController() {
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

		String status = request.getParameter("status");
		ProductDAO pdao = ProductDAOImpl.getInstance();
		ArrayList<Product> products = new ArrayList<>();
		//기본값으로 분양중인것만 불러오기
		if (status == null) {
			status = "on";
		}
		
		//설정값
		if (status.equals("all")) {
			products = pdao.productFindAll();
		}
		else {
			products = pdao.productOnAllfind();
		}
		request.setAttribute("products", products);

		request.getRequestDispatcher("productList.jsp").forward(request, response);
		// pname ,description 보이게 구현함
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ArrayList<Product> products = new ArrayList<>();
		ProductDAO pdao = ProductDAOImpl.getInstance();
		String keyword = request.getParameter("keyword");
		products = pdao.productSearch(keyword);		

		request.setAttribute("products", products);
		request.getRequestDispatcher("productList.jsp").forward(request, response);
	}

}
