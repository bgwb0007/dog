package com.board.model;

import java.util.ArrayList;

public interface BoardDAO {
	//추가
	public void boardInsert(Board board);
	//수정
	public void boardUpdate(Board board);
	//전체보기
	public ArrayList<Board> boardList(int startRow, int endRow, String field, String word);
	//삭제
	public int boardDelete(int num);
	//게시글 수
	public int boardCount(String field, String word);
	//상세보기
	public Board findById(int num);
	
	
	//-------comment
	public ArrayList<CommentDTO> findAllComment(int bnum);
	public void commentInsert(CommentDTO comment);

	

}
