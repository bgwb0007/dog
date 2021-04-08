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
	
	//관리페이지 _ 전체 채팅방 개수
	public int countAllChat();
	//관리페이지 _ 오늘 채팅방 개수
	public int countTodayChat();
	//관리페이지 _ 전체 채팅 개수
	public int countAllMessage();
	//관리페이지 _ 오늘 채팅 개수
	public int countTodayMessage();
	
	//메시지 저장하기
	public void messageInsert(Message message);
	//이전 메시지 불러오기
	public ArrayList<Message> messageFindAll(int chatid);
}
