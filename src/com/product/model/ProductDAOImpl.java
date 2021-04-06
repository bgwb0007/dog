package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAOImpl implements ProductDAO {
	private static ProductDAOImpl instance = new ProductDAOImpl();

	public static ProductDAOImpl getInstance() {
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

	@Override
	public void productInsert(Product product) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("insert into product ");
			sb.append("(id, name, age, description, category, status, filename, userid, gender, createddate )");
			sb.append(" values(product_seq.nextval, ?,?,?,?,?,?,?,?,?)");
			ps = con.prepareStatement(sb.toString());
			ps.setString(1, product.getName());
			ps.setLong(2, product.getAge());
			ps.setString(3, product.getDescription());
			ps.setString(4, product.getCategory());
			ps.setString(5, product.getStatus());
			ps.setString(6, product.getFilename());
			ps.setString(7, product.getUserid());
			ps.setString(8, product.getGender());
			ps.setString(9, product.getCreatedDate());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}

	}

	@Override
	public ArrayList<Product> productAllfind() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Product> parr = new ArrayList<>();

		try {
			con = getConnection();
			String sql = "select * from product where status = 'on' order by createddate desc";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Product product = new Product();
				product.setCategory(rs.getString("category"));
				product.setDescription(rs.getString("description"));
				product.setName(rs.getString("name"));
				product.setId(rs.getInt("id"));
				product.setStatus(rs.getString("status"));
				product.setAge(rs.getInt("age"));
				product.setCreatedDate(rs.getString("createddate"));
				if (rs.getString("filename") == null) {
					product.setFilename("±âº».png");
				} else {
					product.setFilename(rs.getString("filename"));
				}
				product.setGender(rs.getString("gender"));
				parr.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return parr;
	}

	@Override
	public Product findById(Long productId) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Product product = null;

		try {
			con = getConnection();
			String sql = "select * from product where id=" + productId;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				product = new Product();
				product.setCategory(rs.getString("category"));
				product.setDescription(rs.getString("description"));
				product.setName(rs.getString("name"));
				product.setId(rs.getInt("id"));
				product.setStatus(rs.getString("status"));
				product.setAge(rs.getInt("age"));
				product.setFilename(rs.getString("filename"));
				product.setGender(rs.getString("gender"));
				product.setUserid(rs.getString("userid"));
				product.setCreatedDate(rs.getString("createddate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return product;
	}

	@Override
	public ArrayList<Product> myProductFindAll(Long userId) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Product> productList = null;
		try {
			con = getConnection();
			String sql = "select * from product where id=" + userId;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Product product = new Product();
				product.setCategory(rs.getString("category"));
				product.setDescription(rs.getString("description"));
				product.setName(rs.getString("name"));
				product.setId(rs.getInt("id"));
				product.setStatus(rs.getString("status"));
				product.setAge(rs.getInt("age"));
				product.setFilename(rs.getString("filename"));
				product.setGender(rs.getString("gender"));
				product.setCreatedDate(rs.getString("createddate"));
				productList.add(product);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return productList;
	}

	private void closeConnection(Connection con, PreparedStatement ps, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saleCompleted(Long productId) {
		Connection con = null;
		PreparedStatement ps = null;
		int flag = 0;
		try {
			con = getConnection();
			String sql = "update product set status = 'off' where id =" + productId;
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			flag = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}

	}

	@Override
	public void productDelete(Long productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void productUpdate(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public ProductAdminCount productAdminCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ProductAdminCount productAdminCount = new ProductAdminCount();
		ArrayList<String> cdates = new ArrayList<String>();
		ArrayList<Integer> counts = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select cdate, count(*) as count from (select SUBSTR(product.createddate,1,8) as cdate from product) group by cdate order by cdate";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				cdates.add("'" + rs.getString("cdate") + "'");
				counts.add(rs.getInt("count"));
			}
			productAdminCount.setCdate(cdates);
			productAdminCount.setCount(counts);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return productAdminCount;
	}

}
