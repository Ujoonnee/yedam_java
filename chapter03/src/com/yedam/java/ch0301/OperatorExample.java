package com.yedam.java.ch0301;

public class OperatorExample {

	public static void main(String[] args) {
		// 부호연산자
		int a = -100;
		int result1 = +a;
		int result2 = -a;
		System.out.println("result1 : " + result1);
		System.out.println("result2 : " + result2);
		
		byte b = 100;
		int result3 = -b;
		System.out.println("result3 : " + result3);
		
		System.out.println();
		
		// 증감연산자
		int x = 10;
		int y = 10;
		int z;
		
		System.out.println("=======================");
		x++;
		++x;
		System.out.println("x = " + x);
		
		System.out.println("=======================");
		y--;
		--y;
		System.out.println("y = " + y);
		
		System.out.println("=======================");
		z = x++;
		System.out.println("z = " + z);
		System.out.println("x = " + x);
		
		System.out.println("=======================");
		z = ++x;
		System.out.println("z = " + z);
		System.out.println("x = " + x);
		
		System.out.println("=======================");
		z = ++x + y++;
		System.out.println("z = " + z);
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		
		x = y = 5;
		
		System.out.println("=======================");
		z = x++ + --y;
		System.out.println("z = " + z);
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		
		System.out.println("=======================");
		z = --x + y++;
		System.out.println("z = " + z);
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		
		System.out.println();
		
		// 논리부정연산자
		boolean play = true;
		
		play = !play;
		System.out.println(play);
		
		play = !play;
		System.out.println(play);
		
		System.out.println();
		
		// 산술연산자
		int v1 = 5;
		int v2 = 2;
		
		int result4 = v1 + v2;
		System.out.println("result4 : " + result4);
		
		int result5 = v1 - v2;
		System.out.println("result4 : " + result5);
		
		int result6 = v1 * v2;
		System.out.println("result4 : " + result6);
		
		int result7 = v1 / v2;
		System.out.println("result4 : " + result7);
		
		int result8 = v1 % v2;
		System.out.println("result4 : " + result8);
		
		System.out.println();
		
		// 문자열 결합 연산자
		String str1 = "JDK" + 6.0;
		String str2 = str1 + " 특징";
		System.out.println(str2);
		
		String str3 = "JDK" + 3 + 3.0;
		String str4 = 3 + 3.0 + "JDK";
		System.out.println(str3);
		System.out.println(str4);
		
		System.out.println();
		
		// 비교연산자
		int num1 = 10;
		int num2 = 10;
		boolean bResult1 = (num1 == num2);
		boolean bResult2 = (num1 != num2);
		boolean bResult3 = (num1 <= num2);
		
		System.out.println("bResult1 : " + bResult1);
		System.out.println("bResult2 : " + bResult2);
		System.out.println("bResult3 : " + bResult3);

		char char1 = 'A';
		char char2 = 'B';
		boolean bResult4 = (char1 < char2);
		System.out.println("bResult4 : " + bResult4);
		
		int v3 = 1;
		double v4 = 1.0;
		System.out.println(v3 == v4);
		
		double v5 = 0.1;
		float v6 = 0.1f;
		System.out.println(v5 == v6);
		System.out.println((float)v5 == v6);
		
		System.out.println();
		
		// 논리연산자
		int charCode = 'A';
		System.out.println(charCode);
		
		// 유니코드 중 65보다 크거나 같고, 90보다 작거나 같으면 대문자
		if ((charCode >= 65) && (charCode <= 90)) {
			System.out.println("대문자");
		}
		
		// 유니코드 중 97보다 크거나 같고, 122보다 작거나 같으면 소문자
		if ((charCode >= 97) && (charCode <= 122)) {
			System.out.println("소문자");
		}
		
		// 유니코드 중 48보다 크고, 57보다 작으면 숫자 0~9
		if ((charCode >= 48) && (charCode <= 58)) {
			System.out.println("숫자 0~9");
		}
		
		int numValue = 9;
		if ((numValue % 2 == 0) || (numValue % 3 == 0)) {
			System.out.println("2 또는 3의 배수입니다.");
		}
		
		System.out.println();
		
		// 대입 연산자
		int resultA = 0;
		resultA += 10;
		System.out.println("resultA : " + resultA);
		resultA -= 5;
		System.out.println("resultA : " + resultA);
		resultA *= 3;
		System.out.println("resultA : " + resultA);
		resultA /= 5;
		System.out.println("resultA : " + resultA);
		resultA %= 3;
		System.out.println("resultA : " + resultA);
	
		System.out.println();
		
		// 삼항연산자
		int score = 95;
		char grade = (score > 90) ? 'A' : 'B';
		System.out.printf("성적은 %d이고 등급은 %c입니다.\n", score, grade);
		
		int age = 27;
		String str = (age >= 20) ? "성인" : "미성년";
		System.out.printf("나이는 %d이고 %s입니다.\n", age, str);
		
		
		
		
		
		
		
		
	}

}
