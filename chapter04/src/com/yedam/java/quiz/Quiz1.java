package com.yedam.java.quiz;

import java.util.Scanner;

public class Quiz1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 문제1) 두 개의 정수가 주어졌을 때 두 정수의 합이 자연수이면 'Natural Number'을 출력하도록 하세요.
		System.out.print("a : ");
		int a = sc.nextInt();
		System.out.print("b : ");
		int b = sc.nextInt();

		System.out.print(((a + b) > 0) ? "Natural Number\n" : "");

		// 문제2) 두 개의 정수가 주어졌을 때 두 수의 대소관계에 따라 부등호( > or = or < )를 출력하도록 하세요.
		if ((a - b) > 0) {
			System.out.println(">");
		} else if ((a - b) == 0) {
			System.out.println("=");
		} else {
			System.out.println("<");
		}

		/*
		 * 문제3) 체질량 지수인 BMI에 따라 비만도를 네가지 단계로 구분하여 결과값을 출력하도록 하세요. BMI = 체중 / ( 키 * 키 )
		 * 저체중(18.5미만), 정상(18.5 이상 25미만), 과체중(25이상 30미만), 비만(30이상)
		 */
		System.out.print("weight : ");
		double weight = sc.nextDouble();
		System.out.print("height : ");
		double heightInput = sc.nextDouble();
		double height = (heightInput > 3) ? heightInput / 100 : heightInput;
		double bmi = weight / (height * height);
		System.out.println("bmi : " + bmi);

		if (bmi < 18.5) {
			System.out.println("저체중(18.5미만)");
		} else if (bmi < 25) {
			System.out.println("정상(18.5 이상 25미만)");
		} else if (bmi < 30) {
			System.out.println("과체중(25이상 30미만)");
		} else {
			System.out.println("비만(30이상)");
		}

		/*
		 * 문제4) 선택한 단의 6번째 값을 출력하도록 하세요. 예를 들어, 2단일 경우 2 X 6 = 12 단, 출력문에 변수를 사용하지 않는다.
		 */

		System.out.print("table : ");
		int table = sc.nextInt();
		switch (table) {
		case 1:
			System.out.println("1 X 6 = 6");
			break;
		case 2:
			System.out.println("2 X 6 = 12");
			break;
		case 3:
			System.out.println("3 X 6 = 18");
			break;
		case 4:
			System.out.println("4 X 6 = 24");
			break;
		case 5:
			System.out.println("5 X 6 = 30");
			break;
		case 6:
			System.out.println("6 X 6 = 36");
			break;
		case 7:
			System.out.println("7 X 6 = 42");
			break;
		case 8:
			System.out.println("8 X 6 = 48");
			break;
		case 9:
			System.out.println("9 X 6 = 54");
			break;
		default:
			System.out.println("Wrong table");
		}

		/*
		 * 문제5) 다음과 같이 점수 범위에 따라 등급을 구분하여 해당 점수에 대한 등급을 출력하도록 하세요. 90점 이상 100점 이하 : A
		 * 등급, 80점 이상 90점미만 : B 등급, 70점 이상 80점미만 : C 등급, 60점 이상 70점미만 : D 등급, 60점미만 :
		 * E등급
		 */
		System.out.print("score : ");
		int score = sc.nextInt();
		switch (score / 10) {
		case 6:
			System.out.println("D 등급");
			break;
		case 7:
			System.out.println("C 등급");
			break;
		case 8:
			System.out.println("B 등급");
			break;
		case 9:
			System.out.println("A 등급");
			break;
		case 10:
			System.out.println("A 등급");
			break;
		default:
			System.out.println("E 등급");
		}

//		System.out.print("score : ");
//		int score = sc.nextInt();
//		switch (score / 10) {
//		case 10:
//		case 9:
//			System.out.println("A 등급");
//			break;
//		case 8:
//			System.out.println("B 등급");
//			break;
//		case 7:
//			System.out.println("C 등급");
//			break;
//		case 6:
//			System.out.println("D 등급");
//			break;
//		default:
//			System.out.println("E 등급");
//		}

		// 예제1



		// 예제2
		System.out.print("num : ");
		int num = sc.nextInt();

		if (num % 2 == 0) {
			System.out.println("2의 배수입니다.");
		} else {
			System.out.println("2의 배수가 아닙니다.");
		}

		// 예제3 - 점수는 0~100점 사이의 값이어야 하며 벗어날 경우 "점수 입력 오류!" 출력
		// 60점 이상이면 합격, 미만이면 불합격

		System.out.print("score : ");
		int scored = sc.nextInt();
		if ((scored >= 0) && (scored <= 100)) {
			System.out.println((scored >= 60) ? "합격" : "불합격");
		} else {
			System.out.println("점수 입력 오류!");
		}

		// 예제4 - 놀이기구, 키 110cm 이상일 경우 라바 트위스터를 탑승
		// 110cm 미만의 경우 보호자가 있는지 체크 -> 있으면 탑승 / 없으면 "보호자와 같이 오세요." 출력

		System.out.print("키 : ");
		double heightA = sc.nextDouble();
		if (heightA >= 110) {
			System.out.println("탑승");
		} else {
			System.out.println("1 : 보호자 O  / 0 : 보호자 X");
			int parent = sc.nextInt();

			if (parent == 1) {
				System.out.println("탑승");
			} else {
				System.out.println("보호자와 같이 오세요.");
			}

		}

		// switch문

		// 예제1 - 정수를 입력받아 0일 경우 false, 1일 경우 true, 둘 다 아닐 경우 "체크오류"

		System.out.println("num : ");
		int numC = sc.nextInt();
		switch (numC) {
		case 0:
			System.out.println("false");
			break;
		case 1:
			System.out.println("true");
			break;
		default:
			System.out.println("체크오류");
		}

		// 예제2 - 정수를 입력받아 홀수인지 짝수인지 판별

		System.out.print("num : ");
		int numOE = sc.nextInt();
		switch (numOE % 2) {
		case 0:
			System.out.println("짝수");
			break;
		case 1:
			System.out.println("홀수");
			break;
		}

		sc.close();
	}

}
