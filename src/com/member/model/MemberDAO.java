package com.member.model;

import java.util.ArrayList;

public interface MemberDAO {
	//추가
	public void memberJoin(Member member);
	//전체보기
	public ArrayList<Member> getMember();
	//수정
	public int memberUpdate(Member member);
	//삭제
	public void memberDelete(String userid);
	//상세보기
	public Member findById(String userid);
	//회원수
	public int memberCount();
	//아이디중복확인
	public String memberIdCheck(String userid);
	//로그인확인
	public Member memberLoginCheck(String userid, String pwd);

}
