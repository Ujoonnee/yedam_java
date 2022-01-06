package com.yedam.java.example;

import java.util.Scanner;

public class Won2Dollar extends Converter {

	Won2Dollar(double money) {
		this.ratio = 1 / money;
	}
	
	@Override
	protected double convert(double src) {
		double result = 0;
		result = src * ratio;
		return result;
	}

	@Override
	protected String getSrcString() {
		// TODO Auto-generated method stub
		return "원";
	}

	@Override
	protected String getDestString() {
		// TODO Auto-generated method stub
		return "달러";
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		System.out.println(getSrcString() + "을 " + getDestString() + "로 바꿉니다.");
		System.out.print(getSrcString() + "을 입력하세요>> ");
		double val = scanner.nextDouble();
		double res = convert(val);
		System.out.println("변환 결과: " + res + getDestString() + "입니다");
		scanner.close();
	}
}
