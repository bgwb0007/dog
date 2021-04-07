package com.product.action;

import java.io.IOException;
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
 * Servlet implementation class ProductUpdateController
 */
@WebServlet("/product/pupdate")
public class ProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		int productId = (int) session.getAttribute("productId");
		
		ProductDAO dao = ProductDAOImpl.getInstance();
		Product product = dao.findById(productId);

		request.setAttribute("product", product);
		
		request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Product product = new Product();
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("name"));
		product.setId(Integer.parseInt(request.getParameter("id")));
		product.setName(request.getParameter("name"));
		product.setAge(Integer.parseInt(request.getParameter("age")));
		product.setDescription(request.getParameter("description"));
		product.setCategory(request.getParameter("category"));
		product.setGender(request.getParameter("gender"));
		
		ProductDAO dao = ProductDAOImpl.getInstance();
		dao.productUpdate(product); // 
		
		response.sendRedirect("plist");
	}

}
