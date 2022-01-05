package com.yedam.java.chapter0701_1;

public class Computer extends Calculator {

	@Override
	double areaCircle(double r) {
		System.out.println("Computer의 areaCircle() 실행");
		return Math.PI * r * r;
	}

}
