package com.chat.model;

import java.util.ArrayList;



public interface ChatDAO {
	
	//�߰�
	public void chatInsert(Chat chat);
	//ä�ù� �̹� �ִ��� Ȯ��
	public boolean checkCreated(Chat chat);
	//�� ä�ø�� ��ü����
	public ArrayList<Chat> myChatFindAll(String userId);
	//ä�ù� ã��
	public Chat findById(int chatid);

	
	//�޽��� �����ϱ�
	public void messageInsert(Message message);
	//���� �޽��� �ҷ�����
	public ArrayList<Message> messageFindAll(int chatid);
}
