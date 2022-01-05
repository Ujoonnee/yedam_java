package com.yedam.java.chpater0603;

public class Calculator {
	// 전원 on
	void powerOn() {
		System.out.println("전원을 켭니다.");
	}
	
	// 전원 off
	void powerOff() {
		System.out.println("전원을 끕니다.");
	}
	
	// 더하기
	int plus(int x, int y) {
		int result = 0;
		result = x + y;
		return result;
	}
	
	// 나누기
	double divide(double x, double y) {
		double result = 0;
		result = x / y;
		return result;
	}
	
	// 평균
	double avg(int x, int y) {
		double result = 0;
		int sum = plus(x, y);
		result = sum / 2.0;		
		return result;
	}
	
	// 메세지 출력
	void println(String message) {
		System.out.println(message);
	}
	
	// 실행
	void execute() {
		double result = avg(7, 10);
		println("실행결과 : " + result);
	}
	
	// 정사각형의 넓이
	double areaRectangle(double width) {
		return width * width;
	}
	
	// 직사각형의 넓이
	double areaRectangle(double width, double height) {
		return width * height;
	}
	
	
	
	
}
