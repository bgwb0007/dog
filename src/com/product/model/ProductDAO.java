package com.product.model;

import java.util.ArrayList;

public interface ProductDAO {
	//�߰�
	public void productInsert(Product product);
	//��ü����
	public ArrayList<Product> productAllfind();
	//�󼼺���
	public Product findById(Long productId);
	//���� ���� ��ü����
	public ArrayList<Product> myProductFindAll(Long userId);
	//�о�Ϸ�� ���º���
	public void saleCompleted(Long productId);
	//����
	public void productDelete(Long productId);
	//����
	public void productUpdate(Product product);
	
	// admin_ product ���� �׷���
	public ProductAdminCount productAdminCount();
}
