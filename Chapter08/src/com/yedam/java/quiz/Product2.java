package com.yedam.java.quiz;

public class Product2 {
	// 리팩토링 필수
	
	private static Product2 product2 = new Product2();
	private static ProductInfo[] list;
	
	private Product2() {}
	
	
	public static Product2 getInstance(int number) {
		list = new ProductInfo[number];
		return product2;
	}
	
	public ProductInfo[] getList() {
		return list;
	}
	
	public ProductInfo getListElement(int index) {
		return list[index];
	}

	public void setListElement(int index, ProductInfo productInfo) {
		list[index] = productInfo;
	}
	
}
