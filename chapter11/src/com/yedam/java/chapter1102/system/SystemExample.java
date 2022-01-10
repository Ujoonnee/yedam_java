package com.yedam.java.chapter1102.system;

import java.util.Iterator;

public class SystemExample {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			if(i == 5) {
//				System.exit(i);
			}
		}
		
		long time1 = System.nanoTime();
		
		int sum = 0;
		for (int i = 0; i <= 1000000; i++) {
			sum += i ;
		}
		
		long time2 = System.nanoTime();
		
		System.out.println("1~100000까지의 합 : " + sum);
		System.out.println("계산에 " + (time2 - time1) + " 나노초가 소요되었습니다.");
	}

}
