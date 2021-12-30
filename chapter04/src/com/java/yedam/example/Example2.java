package com.java.yedam.example;

import java.util.Scanner;

public class Example2 {
	public static void main(String[] args) {
		// ATM기가 있고, 입금, 출금, 잔액조회, 종료
		// 1. 출금을 할 때 잔액보다 많을 경우 잔액부족 출력
		// 2. 입금, 출금, 잔액조회 -> 비밀번호 정보확인
		
		Scanner sc = new Scanner(System.in);
		System.out.println("문제 7");
		boolean run = true;
		int balance = 0;
		int password = 1234;
		
		Program : while (run) {
			System.out.println("----------------------------------------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
			System.out.println("----------------------------------------------");
			System.out.print("메뉴선택> ");
			int select = sc.nextInt();
			int count = 0;
			while (count < 6) {
				if (select == 4) {
					break;
				} else if (count == 5) {
					System.out.println("비밀번호를 5회 틀렸습니다.");
					continue Program;
				}
				System.out.print("비밀번호>");
				int inputPassword = sc.nextInt();
				if (password != inputPassword) {
					System.out.println("비밀번호가 틀렸습니다.");
					count++;
					continue;
				} else {
					break;
				}
			}
			 switch (select) {
			case 1:
				System.out.println("예금액> ");
				int deposit = sc.nextInt();
				balance += deposit;
				System.out.println("잔고> " + balance);
				break;
			case 2:
				while (count < 3) {
					System.out.println("출금액> ");
					int withdraw = sc.nextInt();
					if(balance - withdraw < 0) {
						System.out.println("잔액이 부족합니다.");
						count++;
						continue;
					} else {
						balance -= withdraw;
						System.out.println("잔고> " + balance);
						break;
					}
				}
				break;
			case 3:
				System.out.println("잔고> " + balance);
				break;
			case 4:
				run = false;
				break;
			default :
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
		}
		System.out.println("프로그램 종료");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
