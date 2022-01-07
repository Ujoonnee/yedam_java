package com.yedam.java.chapter1502;

public class Box<T> {
	// 타입을 파라미터로 받음
	// ex) T를 String으로 받으면 class 내의 모든 T가 String으로 변환됨
	
	private T t;

	public void set(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

}
