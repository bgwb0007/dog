package com.chat.model;

import java.util.ArrayList;



public interface ChatDAO {
	
	//추가
	public void chatInsert(Chat chat);
	//채팅방 이미 있는지 확인
	public boolean checkCreated(Chat chat);
	//내 채팅목록 전체보기
	public ArrayList<Chat> myChatFindAll(String userId);
	//채팅방 찾기
	public Chat findById(int chatid);

	
	//메시지 저장하기
	public void messageInsert(Message message);
	//이전 메시지 불러오기
	public ArrayList<Message> messageFindAll(int chatid);
}
