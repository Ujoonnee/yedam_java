package com.java.yedam.example;

import java.util.Scanner;

public class Example3 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int answer = (int)(Math.random() * 10) + 1;

		for (int i = 0; i < 3; i++) {
			System.out.println("정답 입력>");
			int userInput = sc.nextInt();
			if (userInput < answer) {
				System.out.println("Up\n");
			} else if (userInput > answer) {
				System.out.println("Down\n");
			} else {
				System.out.println("정답입니다.");
				break;
			}
			
			if (i == 0) {
				System.out.println("남은 기회 2번");
			} else if (i == 1) {
				System.out.println("남은 기회 1번");
			} else if (i == 2) {
				System.out.println("실패");
				System.out.println("정답 : " + answer);
			}
		}
		sc.close();
	}
}
