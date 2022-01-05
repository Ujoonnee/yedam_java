package com.yedam.java.main;

import java.util.Scanner;

public class Example {

	public static void main(String[] args) {

		// 문제2) 다음은 키보드로부터 상품 수와 상품 가격을 입력받아서 
				//       최고 가격을 가지는 제품과 해당 제품을 제외한 제품들의 총 합을 구하는 프로그램을 작성하세요.
				// 1) 메뉴는 다음과 같이 구성하세요.
				//  1.상품 수 | 2.가격입력 | 3.제품별 가격 | 4.분석 | 5.종료
				// 2) 입력한 상품 수만큼 가격을 입력받을 수 있도록 구현하세요.
				// 3) 각 가격에 해당하는 제품명은 인덱스+1로 합니다. 
				//    예를 들어 배열[0] = 10000 이면 10,000원인 제품은 1번째 제품입니다.
				// 4) 분석기능은 최고 가격을 가지는 제품과 해당 제품을 제외한 제품들의 총합을 구합니다.
				// 5) 종료 시에는 프로그램을 종료한다고 메세지를 출력하도록 구현하세요.
		
		System.out.println("추가문제2");
		
		Scanner sc = new Scanner(System.in);

		int[] products = new int[1];
		boolean run = true;
		while (run) {
			System.out.println("-----------------------------------------------");
			System.out.println("1.상품 수 | 2.가격입력 | 3.제품별 가격 | 4.분석 | 5.종료");
			System.out.println("-----------------------------------------------");
			System.out.println("선택>");
			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("제품 수>");
				int numProducts = sc.nextInt();
				products = new int[numProducts];
				break;
			case 2:
				for (int i = 0; i < products.length; i++) {
					System.out.printf("%d>\n", i+1);
					int price = sc.nextInt();
					products[i] = price;
				}
				break;
			case 3:
				for (int i = 0; i < products.length; i++) {
					System.out.printf("%d: %d\n", i+1, products[i]);
				}
				break;
			case 4:
				System.out.println("제외할 제품>");
				int exception = sc.nextInt();
				int max = products[0];
				for (int i = 0; i < products.length; i++) {
					if (products[i] > max) {
						max = products[i];
					}
				}

				int sum = 0;
				for (int i = 0; i < products.length; i++) {
					sum += products[i];
				}
				
				if (products[exception-1] == max) {
					sum -= max;
				} else {
					sum -= max;
					sum -= products[exception-1];
				}
				
				System.out.println("총합 : " + sum);
				break;
			case 5:
				System.out.println("프로그램 종료");
				run = false;
				break;
			}
				

		}
		
		sc.close();
	}

}
