package com.product.model;

import java.util.ArrayList;

public interface ProductDAO {
	//추가
	public void productInsert(Product product);
	//분양중인거 전체보기
	public ArrayList<Product> productOnAllfind();
	//분양중&분양완료 전체보기
	public ArrayList<Product> productFindAll();
	//상세보기
	public Product findById(int productId);
	//내가 쓴글 전체보기
	public ArrayList<Product> myProductFindAll(Long userId);
	//분양완료로 상태변경
	public void saleCompleted(Long productId);
	//삭제
	public void productDelete(Long productId);
	//수정
	public void productUpdate(Product product);
	// 최근 3개 게시글 보기 (index)
	public ArrayList<Product> productFindTop3();
	
	// 검색어 포함된 게시글 찾기
	public ArrayList<Product> productSearch(String keyword);

	// admin_ product 개수 그래프
	public ProductAdminCount productAdminCount();
}
