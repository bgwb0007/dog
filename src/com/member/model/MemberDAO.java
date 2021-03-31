package com.member.model;

import java.util.ArrayList;

public interface MemberDAO {
	//�߰�
	public void memberJoin(Member member);
	//��ü����
	public ArrayList<Member> getMember();
	//����
	public int memberUpdate(Member member);
	//����
	public void memberDelete(String userid);
	//�󼼺���
	public Member findById(String userid);
	//ȸ����
	public int memberCount();
	//���̵��ߺ�Ȯ��
	public String memberIdCheck(String userid);
	//�α���Ȯ��
	public Member memberLoginCheck(String userid, String pwd);

}
