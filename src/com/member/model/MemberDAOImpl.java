package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
// git test
public class MemberDAOImpl  implements MemberDAO{
	private static MemberDAOImpl instance = new MemberDAOImpl();
	public static MemberDAOImpl getInstance() {
		return instance;
	}
	
	private   Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
	@Override
	public void memberJoin(Member member) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con =getConnection();
			String sql="insert into member(name,userid,pwd,email,phone,admin) values(?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getUserid());
			ps.setString(3, member.getPwd());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getPhone());
			ps.setInt(6, member.getAdmin());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, ps, null, null);
		}
	}
    //전체회원보기
	@Override
	public ArrayList<Member> getMember() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Member> arr = new ArrayList<>();
		
		try {
			con = getConnection();
			String sql="select * from member";
			st = con.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()) {
				Member dto = new Member();
				dto.setAdmin(rs.getInt("admin"));
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setPwd(rs.getString("pwd"));
				dto.setUserid(rs.getString("userid"));
				arr.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null,st, rs);
		}
		return arr;
	}

	@Override
	public int memberUpdate(Member member) {
		  Connection con = null;
		    PreparedStatement ps = null;
		    int flag=0;
		    
		    try {
				con =getConnection();
				String sql="update member set name=?, pwd =?, email=?, admin=?, phone=? where userid =?";
				System.out.println(sql);
				ps=con.prepareStatement(sql);
				ps.setString(1, member.getName());
				ps.setString(2, member.getPwd());
				ps.setString(3, member.getEmail());
				ps.setInt(4, member.getAdmin());
				ps.setString(5, member.getPhone());
				ps.setString(6, member.getUserid());
				
				flag =ps.executeUpdate();
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally {
				closeConnection(con, ps, null,null);
			}
			return flag;
	}
    // 회원삭제
	@Override
	public void memberDelete(String userid) {
		Connection con = null;
		Statement st = null;
		try {
			con =getConnection();
			st= con.createStatement();
			String sql ="delete from member where userid='"+userid+"'";
			st.executeUpdate(sql);
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, null, st, null);
		}
		
	}

	@Override
	public Member findById(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs =null;
		Member member = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from member where userid='"+userid+"'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				 member = new Member();
				 member.setAdmin(rs.getInt("admin"));
				 member.setEmail(rs.getString("email"));
				 member.setName(rs.getString("name"));
				 member.setPhone(rs.getString("phone"));
				 member.setPwd(rs.getString("pwd"));
				 member.setUserid(rs.getString("userid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return member;
	}
  //회원수
	@Override
	public int memberCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count =0 ;
		
		try {
			con =getConnection();
			String sql="select count(*) from member";
			st= con.createStatement();
			rs= st.executeQuery(sql);
			if(rs.next()) {
				count =rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con,null, st, rs);
		}
		return count;
	}

	@Override
	public String memberIdCheck(String userid) {
		Connection con = null;
		Statement st =null;
		ResultSet rs = null;
		String flag ="yes"; // id 사용가능
		try {
			con =getConnection();
			String sql = "select * from member where userid = '"+userid+"'" ;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {//rs가 있으면 -->테이블에 userid 존재함. 사용할 수 없음
				flag="no";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return flag;
	}
    
	
	//로그인체크
	@Override
	public Member memberLoginCheck(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Member member = new Member();
		//비회원 
		member.setAdmin(-1);
		try {
			con =getConnection();
			String sql = "select * from member where userid ='"+userid+"'";
			//System.out.println(sql);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) { //회원임
				if(rs.getString("pwd").equals(pwd)) { //회원(일반회원, 관리자)
					member.setAdmin(rs.getInt("admin"));
					member.setEmail(rs.getString("email"));
					member.setName(rs.getString("name"));
					member.setPhone(rs.getString("phone"));
					member.setPwd(rs.getString("pwd"));
					member.setUserid(rs.getString("userid"));
				}else { //비번오류
					member.setAdmin(2); //
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		
		return member;
	}
	private  void closeConnection(Connection con,
			 PreparedStatement ps, Statement st, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
