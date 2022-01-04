package com.yedam.java.example;

public class TV {
	
	String company;
	int year, size;
	
	TV(String company, int year, int size) {
		this.company = company;
		this.year = year;
		this.size = size;
	}
	
	void showInfo() {
		System.out.printf("%s에서 만든 %d년형 %d인치 TV\n", company, year, size);
		System.out.println();
	}
}
