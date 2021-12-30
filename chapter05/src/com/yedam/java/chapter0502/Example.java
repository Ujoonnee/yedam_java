package com.yedam.java.chapter0502;

import java.util.Scanner;

public class Example {
	public static void main(String[] args) {
		int[] arr1 = { 10, 20, 30, 50, 3, 60, -5 };

		// 예제1 - 주어진 배열 안의 값을 전부 더하고 합과 경균을 출력
		System.out.println("예제1");
		int sum = 0;
		for (int i = 0; i < arr1.length; i++) {
			sum += arr1[i];
		}
		System.out.printf("총합 : %d, 평균 : %.1f\n", sum, (double)sum / arr1.length);
		System.out.println();
		
		// 예제2 - 주어진 배열 안의 값을 출력하는데 인덱스가 2의 배수인 경우만 출력한다.
		System.out.println("예제2");
		for (int i = 0; i < arr1.length; i += 2) {
			System.out.print(arr1[i]+" ");
		}
		System.out.println();
		
		// 예제3 - 인덱스 번호 2개를 입력받아, 그 값을 서로 바꿔보세요.
		System.out.println("예제3");
		Scanner sc = new Scanner(System.in);
		System.out.print("첫번째 인덱스 (0 ~ 6) : ");
		int index1 = sc.nextInt();
		System.out.print("두번째 인덱스 (0 ~ 6) : ");
		int index2 = sc.nextInt();
		
		int temp = arr1[index1];
		arr1[index1] = arr1[index2];
		arr1[index2] = temp;
		for (int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.println();
		
		sc.close();
	}
}
