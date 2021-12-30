package com.yedam.java.ch0402;

import java.util.Scanner;

public class Example {

	public static void main(String[] args) {
		// for문
		Scanner sc = new Scanner(System.in);

		// 예제1 - 0부터 90까지 10단위로 출력
		System.out.println("예제 1");
		for (int i = 0; i < 91; i += 10) {
			System.out.println(i);
		}

		System.out.println();

		// 예제2 - 0부터 20까지 짝수만 출력
		System.out.println("예제 2");
		for (int i = 0; i < 21; i += 2) {
			System.out.println(i);
		}
		System.out.println();

		// 예제3 - 숫자를 5번 입력하고, 입력받을 때 마다 더해지는 값을 출력
		System.out.println("예제 3");
		System.out.println("숫자 5개를 입력하세요");
		for (int i = 0, sum = 0; i < 5; i++) {
			System.out.println("숫자를 입력하세요");
			sum += sc.nextInt();
			System.out.println("출력 : " + sum);
		}
		System.out.println();

		// 예제4 - 1부터 사용자가 입력한 숫자까지 합계;
		System.out.println("예제 4");
		System.out.println("숫자를 입력하세요");
		int input = sc.nextInt();
		int sum = 0;
		for (int i = 0; i <= input; i++) {
			sum += i;
		}
		System.out.println("1부터 " + input + "까지의 합 : " + sum);
		System.out.println();

		// 문제1) 책 161페이지 5번, for문을 이용해서 다음과 같이 *를 출력하는 코드를 작성해보세요.
		// *
		// **
		// ***
		// ****
		// *****

		System.out.println("문제 1");
		String tree = "";
		for (int i = 0; i < 5; i++) {
			tree += "*";
			System.out.println(tree);
		}
		System.out.println();

		// 문제2) 책 160페이지 2번, for문을 이용해서 1부터 100까지의 정수 중에서 2의 배수가 아닌 숫자의 개수를 구하세요.
		// 단, continue 문을 사용한다.
		System.out.println("문제 2");
		int count = 0;
		for (int i = 1; i < 101; i++) {
			if ((i % 2) == 0) {
				continue;
			}
			count++;
		}
		System.out.println("count : " + count);

		// 문제3) 책 161페이지 4번, 중첩 for문을 이용하여 방정식 4x+5y = 60의 모든 해 중 첫번째로 구해지는 값을 (x, y)
		// 형태로 출력해보세요.
		// 단, x와 y는 10이하의 자연수입니다.

		System.out.println("문제 3");
		int x = 1, y = 1;
		Label: for (; x <= 10; x++) {
			for (; y <= 10; y++) {
				if ((x * 4 + y * 5) == 60) {
					break Label;
				}
			}
		}
		System.out.println("x : " + x + ", y : " + y);
		System.out.println();

		// 문제4) do ~ while문과 Math.random()함수를 이용하여 1 ~ 10의 정수 중 7이라는 수가 나올때까지 숫자를 출력하는
		// 코드를 작성해보세요.
		System.out.println("문제 4");
		int result;
		do {
			result = (int) (Math.random() * 10) + 1;
			System.out.println(result);
		} while (result != 7);
		System.out.println();

		// 문제5) 책 161페이지 3번, while문과 Math.random() 함수를 이용해서 2개의 주사위를 던졌을때 나오는 숫자를 (숫자1,
		// 숫자2) 형태로 출력하고,
		// 숫자의 합이 5가 아니면 계속 주사위를 던지고, 숫자의 합이 5이면 실행을 멈추는 코드를 작성해보세요.
		// 또한 주사위를 몇번 던졌는지 횟수를 출력하세요.
		// 숫자의 합이 5가 되는 조합은 (1,4), (4,1), (2,3),(3,2)

		System.out.println("문제 5");
		count = 0;
		while (true) {
			count++;
			System.out.print("set" + count + " : ");
			int dice1 = (int) (Math.random() * 7) + 1;
			int dice2 = (int) (Math.random() * 7) + 1;
			System.out.printf("(%d,%d)\n", dice1, dice2);
			System.out.println();

			if (dice1 + dice2 == 5) {
				break;
			}
		}
		System.out.println("count : " + count);
		System.out.println();

		// 문제7)
		System.out.println("문제 7");
		boolean run = true;
		int balance = 0;

		while (run) {
			System.out.println("----------------------------------------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
			System.out.println("----------------------------------------------");
			System.out.println("선택> ");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				System.out.println("예금액> ");
				int deposit = sc.nextInt();
				balance += deposit;
				System.out.println("잔고> " + balance);
				break;
			case 2:
				System.out.println("출금액> ");
				int withdraw = sc.nextInt();
				balance -= withdraw;
				System.out.println("잔고> " + balance);
				break;
			case 3:
				System.out.println("잔고> " + balance);
				break;
			case 4:
				run = false;
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
		}
		System.out.println("프로그램 종료");

	}

}
