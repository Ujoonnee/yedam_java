package com.yedam.java.quiz;

public class ProductInfo {

	private String name;
	private int price;
	private static ProductInfo[] list;
	
	ProductInfo(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
}
