package com.yedam.java.quiz;

public class Quiz2 {
	public static void main(String[] args) {
		long a = 2L;
		float b = 1.8f;
		double c = 2.5;
		String d = "3.9";
		int D = (int) Double.parseDouble(d);
		int sum = (int) (a+b+c+D);
		System.out.println(sum);
	}
}
