package com.yedam.java.chpater0603;

public class MainExample {

	public static void main(String[] args) {
		// 메소드 호출
		Calculator myCalc = new Calculator();
		myCalc.powerOn();
		
		int result1 = myCalc.plus(5, 6);
		System.out.println("result1 : " + result1);
		
		int x = 10;
		int y = 4;
		double result2 = myCalc.divide(x, y);
		
		System.out.println("result2 : " + result2);
		
		// 매개변수의 갯수를 모를 경우
		Computer myCom = new Computer();
		
		int[] values1 = {1,2,3};
		int intResult1 = myCom.sum1(values1);
		System.out.println("intResult1 : " + intResult1);
		
		int intResult2 = myCom.sum1(new int[] {1,2,3,4,5});
		System.out.println("intResult2 : " + intResult2);
		
		int intResult3 = myCom.sum2(1,2,3);
		System.out.println("intResult3 : " + intResult3);
		
		int intResult4 = myCom.sum2(1,2,3,4,5);
		System.out.println("intResult4 : " + intResult4);
		
		System.out.println();
		
		Car myCar = new Car();
		myCar.setGas(5);
		
		boolean gasState = myCar.isLeftGas();
		if (gasState) {
			System.out.println("출발합니다.");
			myCar.run();
		}
		
		if (myCar.isLeftGas()) {
			System.out.println("gas를 주입할 필요가 없습니다.");
		} else {
			System.out.println("gas를 주입하세요.");
		}
		
		System.out.println();
		
		// 클래스 내부에서 메소드 호출
		Calculator myCalc1 = new Calculator();
		myCalc1.execute();
		
		System.out.println();
		
		// 메서드 오버로딩
		Calculator myCalc2 = new Calculator();
		
		double dbResult1 = myCalc2.areaRectangle(10);
		double dbResult2 = myCalc2.areaRectangle(10, 20);
		System.out.println("정사각형의 넓이 : " + dbResult1);
		System.out.println("직사각형의 넓이 : " + dbResult2);
		
		
		
		
		
		
		
	
	}

}
