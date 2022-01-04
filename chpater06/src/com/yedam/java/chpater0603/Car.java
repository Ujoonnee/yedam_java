package com.yedam.java.chpater0603;

public class Car {
	// 필드
	int gas;
	
	// 생성자
	
	
	// 메서드
	
	// 외부에서 gas 필드를 수정
	void setGas(int gas) {
		this.gas = gas;
	}
	
	// 가스 잔량엽주 확인
	boolean isLeftGas() {
		if(gas == 0) {
			System.out.println("gas가 없습니다.");
			return false;
		}
		
		System.out.println("gas가 있습니다.");
		return true;
	}
	
	// 자동차를 운행
	void run() {
		while(true) {
			if (gas>0) {
				System.out.println("달립니다. (gas 잔량 : " + gas + ")");
				gas -= 1;
			} else {
				System.out.println("멈춥니다. (gas 잔량 : " + gas + ")");
				return;
			}
		}
	}
	
}
