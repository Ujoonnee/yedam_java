package com.yedam.java.quiz;

import java.util.Scanner;

public class Quiz2 {

	// 문제1) 다음은 키보드로부터 상품 수와 상품 가격을 입력받아서
	// 최고 가격을 가지는 제품과 해당 제품을 제외한 제품들의 총 합을 구하는 프로그램을 작성하세요.
	// 1) 메뉴는 다음과 같이 구성하세요.
	// 1.상품 수 | 2.상품 및 가격입력 | 3.제품별 가격 | 4.분석 | 5.종료
	// 2) 입력한 상품 수만큼 상품명과 해당 가격을 입력받을 수 있도록 구현하세요.
	// 3) 제품별 가격을 출력하세요.
	// 출력예시, "상품명 : 가격"
	// 4) 분석기능은 최고 가격을 가지는 제품과 해당 제품을 제외한 제품들의 총합을 구합니다.
	// 5) 종료 시에는 프로그램을 종료한다고 메세지를 출력하도록 구현하세요.

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Product2 pd = null;
		boolean run = true;
		while (run) {

			System.out.println("----------------------------------------------------");
			System.out.println("1.상품수 | 2.가격입력 | 3.제품별가격 | 4.분석 | 5.종료");
			System.out.println("----------------------------------------------------");
			int menuNo = sc.nextInt();

			switch (menuNo) {

			case 1:
				System.out.print("상품수 > : ");
				int num = sc.nextInt();
				pd = Product2.getInstance(num);
				break;
			case 2:
				System.out.println(pd.getList().length);
				for (int i = 0; i < pd.getList().length; i++) {
					System.out.println("상품명>");
					String name = sc.next();
					System.out.println("가격>");
					int price = sc.nextInt();

					ProductInfo pdInfo = new ProductInfo(name, price);
					pd.setListElement(i, pdInfo);
					ProductInfo element = pd.getListElement(i);
					System.out.println(element.getName() + " : " + element.getPrice());
				}
				break;
			case 3:
				for (int i = 0; i < pd.getList().length; i++) {
					ProductInfo element = pd.getListElement(i);
					System.out.println(element.getName() + " : " + element.getPrice());
				}
				break;
			case 4:
				int max = pd.getListElement(0).getPrice();
				int maxIndex = 0;
				for (int i = 0; i < pd.getList().length; i++) {
					if (max < pd.getListElement(i).getPrice()) {
						max = pd.getListElement(i).getPrice();
						maxIndex = i;
					}
				}

				int sum = 0;
				System.out.println("선택>");
				int selected = pd.getListElement(sc.nextInt() -1).getPrice();
				for (int i = 0; i < pd.getList().length; i++) {
					sum += pd.getListElement(i).getPrice();
				}
				if (selected == max) {
					sum -= max;
				} else {
					sum = sum - max - selected;
				}

				System.out.println("최고 가격을 가진 제품은 " + pd.getListElement(maxIndex).getName()  + "입니다.");
				System.out.println("최고 가격을 제외한 제품의 총합은 " + sum + "입니다.");
				break;
			case 5:
				run = false;
				break;

			}
			System.out.println("종료");
		}

		sc.close();
	}
}
