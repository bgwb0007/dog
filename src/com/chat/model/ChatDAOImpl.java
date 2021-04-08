package com.chat.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.product.model.Product;

public class ChatDAOImpl implements ChatDAO{

	private static ChatDAOImpl instance = new ChatDAOImpl();

	public static ChatDAOImpl getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
	
	
	@Override
	public void chatInsert(Chat chat) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("insert into chat ");
			sb.append("(id, buyerid, productid, createddate, productname, productcategory, sellerid)");
			sb.append(" values(chat_seq.nextval, ?,?,?,?,?,?)");
			ps = con.prepareStatement(sb.toString());
			ps.setString(1, chat.getBuyerid());
			ps.setInt(2, chat.getProductid());
			ps.setString(3, chat.getCreateddate());
			ps.setString(4, chat.getProductname());
			ps.setString(5, chat.getProductcategory());
			ps.setString(6, chat.getSellerid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
		
	}

	@Override
	public ArrayList<Chat> myChatFindAll(String userId) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Chat> parr = new ArrayList<>();

		try {
			con = getConnection();
			String sql = "select * from chat where buyerid = '"+userId+"' or sellerid = '"+userId+"' order by createddate desc";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Chat chat = new Chat();
				chat.setId(rs.getInt("id"));
				chat.setBuyerid(rs.getString("buyerid"));
				chat.setProductid(rs.getInt("productid"));
				chat.setCreateddate(rs.getString("createddate"));
				chat.setProductname(rs.getString("productname"));
				chat.setProductcategory(rs.getString("productcategory"));
				chat.setSellerid(rs.getString("sellerid"));
				parr.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return parr;
	}

	@Override
	public Chat findById(int chatid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Chat chat = null;

		try {
			con = getConnection();
			String sql = "select * from chat where id=" + chatid;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				chat = new Chat();
				chat.setId(rs.getInt("id"));
				chat.setBuyerid(rs.getString("buyerid"));
				chat.setProductid(rs.getInt("productid"));
				chat.setCreateddate(rs.getString("createddate"));
				chat.setProductname(rs.getString("productname"));
				chat.setProductcategory(rs.getString("productcategory"));
				chat.setSellerid(rs.getString("sellerid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return chat;
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


	@Override // 만들어진게 없으면 false
	public boolean checkCreated(Chat chat) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select * from chat where buyerid='" + chat.getBuyerid()+"' and productid="+chat.getProductid();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return false;
	}
	@Override
	public void messageInsert(Message message) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("insert into message ");
			sb.append("(id, userid, chatid, text, createddate)");
			sb.append(" values(message_seq.nextval, ?,?,?,?)");
			ps = con.prepareStatement(sb.toString());
			ps.setString(1, message.getUserid());
			ps.setInt(2, message.getChatid());
			ps.setString(3, message.getText());
			ps.setString(4, message.getCreateddate());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}
	@Override
	public ArrayList<Message> messageFindAll(int chatid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Message> parr = new ArrayList<>();

		try {
			con = getConnection();
			String sql = "select * from message where chatid = "+chatid+" order by createddate desc";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setUserid(rs.getString("userid"));
				message.setChatid(rs.getInt("chatid"));
				message.setText(rs.getString("text"));
				message.setCreateddate(rs.getString("createddate"));
				parr.add(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return parr;
	}

}
