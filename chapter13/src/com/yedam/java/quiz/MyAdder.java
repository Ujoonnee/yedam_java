package com.yedam.java.quiz;

public class MyAdder implements AdderInterface {

	@Override
	public int add(int x, int y) {
		int result;
		result = x + y;
		return result;
	}

	@Override
	public int add(int n) {
		int result;
		result = n * (n + 1) / 2;
		return result;
	}

}
