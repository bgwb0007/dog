package com.board.model;

import java.util.ArrayList;

public interface BoardDAO {
	//�߰�
	public void boardInsert(Board board);
	//����
	public void boardUpdate(Board board);
	//��ü����
	public ArrayList<Board> boardList(int startRow, int endRow, String field, String word);
	//����
	public int boardDelete(int num);
	//�Խñ� ��
	public int boardCount(String field, String word);
	//�󼼺���
	public Board findById(int num);
	
	
	//-------comment
	public ArrayList<CommentDTO> findAllComment(int bnum);
	public void commentInsert(CommentDTO comment);

	

}
