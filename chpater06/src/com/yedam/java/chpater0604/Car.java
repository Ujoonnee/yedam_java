package com.yedam.java.chpater0604;

public class Car {
	// 필드
	String model;
	int speed;
	
	// 생성자
	Car(String model) {
		this.model = model;
	}
	
	// 메서드
	void setSpeed(int speed) {
		this.speed = speed;
	}
	
	void run() {
		for (int i = 10; i <60; i+=10) {
			setSpeed(i);
			System.out.println(model + "가 달립니다.(시속 : " + speed + "km/h)");
		}
	}
	
}
