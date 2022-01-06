package com.yedam.java.chapter0802_02;

public class DriveExample {

	public static void main(String[] args) {
		Driver driver = new Driver();
		
		driver.drive(new Bus());
		driver.drive(new Taxi());
		
		System.out.println();
		
		Vehicle vehicle = new Bus();
		vehicle.run();	// 자동 타입 변환으로 인해 checkFare() 사용 불가 -> 캐스팅 필요
		
		Bus bus = (Bus) vehicle;
		bus.run();
		bus.checkFare();
		
		System.out.println();
		
		driver.drive(new Bus());
		driver.drive(new Taxi());
	}
}
