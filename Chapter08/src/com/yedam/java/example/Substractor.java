package com.yedam.java.example;

import java.util.Scanner;

public class Substractor extends Calculator {

	@Override
	protected int calc() {
		int result = a - b;
		return result;
	}

	@Override
	protected void input() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("정수 2개를 입력하세요 >> ");
		a = scanner.nextInt();
		b = scanner.nextInt();
	}

	@Override
	public void run() {
		input();
		int res = calc();
		System.out.println("계산된 값은 " + res);			
	}

}
