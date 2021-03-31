package com.product.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.Member;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ProductDAO pdao = ProductDAOImpl.getInstance();
		 ArrayList<Product> products = pdao.productAllfind();
		 
		 request.setAttribute("products", products);

		 request.getRequestDispatcher("productList.jsp").forward(request, response);
	     //pname  ,description 보이게 구현함
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
