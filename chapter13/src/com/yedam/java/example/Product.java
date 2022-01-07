package com.yedam.java.example;

public class Product {

	private String name;
	private int price;
	
	Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	
	
}
