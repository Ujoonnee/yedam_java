package com.yedam.java.ch0201;

public class VariableExample {

	public static void main(String[] args) {
		// 변수 선언 후 초기화해야 메모리가 할당됨
		int age;
		double value;
		
		age = 20;
		
//		int result = age + (int) value;
		
		int hour = 3;
		int min = 5;
		System.out.println(hour + "시간 " + min + "분");
		
		
		int totalMin = (hour*60) + min;
		System.out.println("총 " + totalMin + "분");
		
		
		// 변수 복사
		int x = 3;
		int y = 5;
		System.out.println("x: " + x + " y: " + y);
		
		int temp = x;
		x = y;
		y = temp;
		System.out.println("x: " + x + " y: " + y);
		
		
		// 변수의 사용범위
		int v1 = 15;
		if (v1 > 10) {
			int v2;
			v2 = v1 - 10;
			
		}
//		int v3 = v1 + v2 + 5;
		
		
	}

}
