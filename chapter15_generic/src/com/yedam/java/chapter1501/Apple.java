package com.yedam.java.chapter1501;

public class Apple {

	public static void main(String[] args) {
		Box box = new Box();
		box.set("홍길동");
		String name = (String) box.get();
		
		box.set(new Apple());
		Apple apple = (Apple) box.get();
	
//		Object object = new Object();

		
//		Object 클래스는 모든 클래스의 부모 클래스이기 때문에 타입이 다른 객채들을 담을 수 있음
//		그러나 캐스팅이 필요하므로 성능 상의 손해가 있고 비효율적임
//		그러므로 여러 타입을 필드로 지정하기 위해서 제네릭을 이용함
	
	
	
	
	
	
	
	}
}
