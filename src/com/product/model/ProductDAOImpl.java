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
			sb.append("(id, name, age, description, category, status, filename, userid )");
			sb.append(" values(product_seq.nextval, ?,?,?,?,?,?,?)");
			ps = con.prepareStatement(sb.toString());
			ps.setString(1, product.getName());
			ps.setLong(2, product.getAge());
			ps.setString(3, product.getDescription());
			ps.setString(4, product.getCategory());
			ps.setString(5, product.getStatus());
			ps.setString(6, product.getFilename());
			ps.setString(7, product.getUserid());
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
			String sql = "select * from product";
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
				parr.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
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

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
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
}
