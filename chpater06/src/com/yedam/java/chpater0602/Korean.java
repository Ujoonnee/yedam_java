package com.yedam.java.chpater0602;

public class Korean {
	
	// 필드
	String nation = "대한민국";
	String name;
	String ssn;
	
	//생성자
	// this는 인스턴스 내에서의 필드를 말한다. 클래스가 아님
	Korean(String name, String ssn) {
		this.name = name;
		this.ssn = ssn;
	}
}
