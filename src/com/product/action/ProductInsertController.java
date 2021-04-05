package com.product.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.product.model.Product;
import com.product.model.ProductDAO;
import com.product.model.ProductDAOImpl;

/**
 * Servlet implementation class ProductInsertController
 */
@WebServlet("/product/pinsert")
public class ProductInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String savePath="upload";// 저장될 파일의 위치(폴더)
		int uploadFileSizeLimit = 5*1024*1024; // 최대 5mb로 제한
		String encType="UTF-8";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("서버상의 실제 디렉토리 : " + uploadFilePath);
		
		MultipartRequest multi = new MultipartRequest(
				request, //request 객체
				uploadFilePath, //서버상의 실제 디렉토리
				uploadFileSizeLimit,//최대 업로드 파일 크기
				encType, // 인코딩 방법
				new DefaultFileRenamePolicy());//동일한 이름 존재할때 새로운 이름 부여됨
		//업로드한 파일이름 
		 String fileName = multi.getFilesystemName("productImage");
		 System.out.println("파일 명 : "+fileName);
		 if(fileName==null) fileName="";
		 ProductDAO pdao = ProductDAOImpl.getInstance();
		 Product product= new Product();
		 product.setCategory(multi.getParameter("category"));
		 product.setStatus(multi.getParameter("status"));
		 product.setDescription(multi.getParameter("description"));
		 product.setName(multi.getParameter("name"));
		 product.setAge(Integer.parseInt(multi.getParameter("age")));
		 product.setFilename(fileName);
		 product.setGender(multi.getParameter("gender"));
		 HttpSession session = request.getSession();
		 Member user = (Member)session.getAttribute("user");
		 product.setUserid(user.getUserid());
		 
		 SimpleDateFormat format1 = new SimpleDateFormat ( "YY-MM-dd HH:mm:ss");
		 Date now = new Date();
		 String format_time1 = format1.format(now.getTime());
		 product.setCreatedDate(format_time1);
		 
		 pdao.productInsert(product);
		 response.sendRedirect("plist");
	}

}






