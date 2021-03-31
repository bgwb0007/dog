package com.board.model;

public class Board {
	private long id;
	private String subject;
	private String email;
	private String content;
	private String regdate;
	private Integer readcount;
	private String userid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public Integer getReadcount() {
		return readcount;
	}
	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	//getter, setter

	
	
	

}
