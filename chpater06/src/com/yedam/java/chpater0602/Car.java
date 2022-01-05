package com.yedam.java.chpater0602;

public class Car {

	Car(String color, int cc) {}
	
	// 필드
	String company = "현대자동차";
	String model = "그랜져";
	String color = "검정";
	int maxSpeed = 350;
	int speed;
	
	// 생성자
	Car() {
		
	}
	
	Car(String model) {
//		this.model = model;
		this(model, "은색", 250);
	}

	Car(String model, String color) {
//		this.model = model;
//		this.color = color;
		this(model, color, 250);
	}
	
	Car(String model, String color, int maxSpeed) {
		this.model = model;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}
	
}
