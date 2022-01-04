package com.yedam.java.example;

import java.util.Scanner;

public class MainExample {

	public static void main(String[] args) {

		// 문제1) Calculator 클래스 -> 사칙연산 메서드

		int x = 100;
		int y = 50;

		Calculator calc = new Calculator();

		System.out.println("x + y = : " + calc.plus(x, y));
		System.out.println("x - y = : " + calc.minus(x, y));
		System.out.println("x * y = : " + calc.multiply(x, y));
		System.out.println("x / y = : " + calc.divide(x, y));

		// 문제2) TV 클래스 -> 다음과 같은 형태의 생성자와 메서드를 가진다.
		TV myTV = new TV("LG", 2017, 32);
		myTV.showInfo(); // LG에서 만든 2017년형 32인치 TV

		// 문제3) Grade클래스 -> 3과목의 점수를 입력받아 성적평균을 출력한다.
		Scanner scanner = new Scanner(System.in);
		System.out.println("수학, 과학, 영어 순으로 3개의 정수 입력>>");
		int math = scanner.nextInt();
		int science = scanner.nextInt();
		int english = scanner.nextInt();
		Grade myGrade = new Grade(math, science, english);
		// average() 메서드는 평균을 계산하여 리턴
		System.out.println("평균 : " + myGrade.average());

		scanner.close();
	}

}
