package com.yedam.java.chpater0604;

public class Singleton {
	// 필드
	private static Singleton singleton = new Singleton();
	
	// 생성자
	Singleton() {}
	
	// 메서드
	static Singleton getInstance() {
		return singleton;
	}
}
