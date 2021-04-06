package com.product.model;

import java.util.ArrayList;

public interface ProductDAO {
	//추가
	public void productInsert(Product product);
	//전체보기
	public ArrayList<Product> productAllfind();
	//상세보기
	public Product findById(Long productId);
	//내가 쓴글 전체보기
	public ArrayList<Product> myProductFindAll(Long userId);
	//분양완료로 상태변경
	public void saleCompleted(Long productId);
	//삭제
	public void productDelete(Long productId);
	//수정
	public void productUpdate(Product product);
	
	// admin_ product 개수 그래프
	public ProductAdminCount productAdminCount();
}
