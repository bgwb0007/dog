package com.product.model;

import java.util.ArrayList;

public interface ProductDAO {
	//�߰�
	public void productInsert(Product product);
	//�о����ΰ� ��ü����
	public ArrayList<Product> productOnAllfind();
	//�о���&�о�Ϸ� ��ü����
	public ArrayList<Product> productFindAll();
	//�󼼺���
	public Product findById(int productId);
	//���� ���� ��ü����
	public ArrayList<Product> myProductFindAll(Long userId);
	//�о�Ϸ�� ���º���
	public void saleCompleted(Long productId);
	//����
	public void productDelete(Long productId);
	//����
	public void productUpdate(Product product);
	// �ֱ� 3�� �Խñ� ���� (index)
	public ArrayList<Product> productFindTop3();
	
	// �˻��� ���Ե� �Խñ� ã��
	public ArrayList<Product> productSearch(String keyword);

	// admin_ product ���� �׷���
	public ProductAdminCount productAdminCount();
}
