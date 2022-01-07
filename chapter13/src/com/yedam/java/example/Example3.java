package com.yedam.java.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Example3 {

	public static void main(String[] args) {

//		Scanner scan = new Scanner(System.in);
//		boolean run = true;
//
//		List<Product> list = new ArrayList<Product>();
//
//		while (run) {
//			System.out.println("----------------------------------------");
//			System.out.println("1.제품정보입력 | 2.제품별가격 | 3.분석 | 4.종료");
//			System.out.println("----------------------------------------");
//			int menuNo= scan.nextInt();
//						
//			switch (menuNo) {
//
//			case 1:
//				System.out.println("상품명>");
//				String name = scan.next();
//				System.out.println("가격>");
//				int price = scan.nextInt();
//				Product product = new Product(name, price);
//					list.add(product);
//				break;
//			case 2:
//				for(Product product : list) {
//					System.out.printf("%s : %d\n", product.getName(), product.getPrice());
//				}
//				break;
//			case 3:
//				int max = 0;
//				String name1 = null;
//				for (int i = 0; i < list.size(); i++) {
//					Product product = list.get(i) ;
//					if (max < product.getPrice()) {
//						max = product.getPrice();
//						name1 = product.getName();
//					}
//				} 
//				
//				int sum = 0;
//				for (int i = 0; i < list.size(); i++) {
//					Product product = list.get(i);
//					if(max == product.getPrice()) {
//						continue;
//					}
//					sum += product.getPrice();
//					
//				}
//				System.out.println("최고 가격을 가진 제품은 " + name1 + "입니다.");
//				System.out.println("최고 가격을 제외한 제품의 총합은 " + sum+ "입니다.");
//				break;
//			case 4:
//				run = false;
//				System.out.println("종료");
//				break;
//
//			}
//
//		}
//		
//		scan.close();

//		-------------------------------------------------------------------------------------------

		Scanner sc = new Scanner(System.in);

		boolean run = true;

		Map<String, Integer> list = new HashMap<String, Integer>();

		while (run) {
			System.out.println("----------------------------------------");
			System.out.println("1.제품정보입력 | 2.제품별가격 | 3.분석 | 4.종료");
			System.out.println("----------------------------------------");

			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("제품명>");
				String name = sc.next();
				System.out.println("제품 가격>");
				int price = sc.nextInt();

				list.put(name, price);
				break;
			case 2:
				Set<String> keys = list.keySet();
				Iterator<String> keyIterator = keys.iterator();
				while (keyIterator.hasNext()) {
					String key = keyIterator.next();
					int value = list.get(key);

					System.out.println(key + " : " + value);
				}
				break;
			case 3:
				Set<String> keys2 = list.keySet();
				Iterator<String> keyIterator2 = keys2.iterator();
				int max = 0;
				name = null;
				while (keyIterator2.hasNext()) {
					String key = keyIterator2.next();
					int value = list.get(key);
					
					if (max < value) {
						max = value;
						name = key;
					}
				}

				int sum = 0;
				Iterator<String> keyIterator3 = keys2.iterator();
				while (keyIterator3.hasNext()) {
					String key = keyIterator3.next();
					int value = list.get(key);
					if (max == value) {
						continue;
					}
					sum += value;
				}
				System.out.println("최고 가격을 가진 제품은 " + name + "입니다.");
				System.out.println("최고 가격을 제외한 제품의 총합은 " + sum + "입니다.");
				break;
			case 4:
				run = false;
				System.out.println("종료");
				break;
			}

		}

		sc.close();
	}
}
