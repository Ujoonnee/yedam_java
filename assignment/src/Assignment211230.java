import java.util.Scanner;

public class Assignment211230 {
	
	public static void main(String[] args) {
		// 주어진 배열을 이용하여 다음 내용을 구현하세요.
		int[] arr1 = { 10, 20, 30, 50, 3, 60, -3 };

		// 문제1. 주어진 배열 중에서 값이 60인 곳의 인덱스를 출력해보자.
		System.out.println("문제1");
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] == 60) {
				System.out.println(i);
			}
		}
		System.out.println();

		// 문제2. 주어진 배열의 인덱스가 3인 곳은 출력하지 말고, 나머지만 출력해보자.
		System.out.println("문제2");
		for (int i = 0; i < arr1.length; i++) {
			if (i != 3) {
				System.out.print(arr1[i] + " ");
			}
		}
		System.out.println();
		System.out.println();

		// 문제3. 주어진 배열 안의 변경하고 싶은 값의 인덱스 번호를 입력받아, 그 값을 1000으로 변경해보자.
		// 입력) 인덱스: 3 -> {10, 20, 30, 1000, 3, 60, -3}
		System.out.println("문제3");
		Scanner sc = new Scanner(System.in);
		System.out.print("변경할 인덱스>");
		int index = sc.nextInt();
		arr1[index] = 1000;
		for (int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.println();
		System.out.println();

		// 문제4. 주어진 배열의 요소에서 최대값과 최소값을 구해보자.
		System.out.println("문제4");
		arr1 = new int[] { 10, 20, 30, 50, 3, 60, -3 };
		int arr1Max = arr1[0];
		int arr1Min = arr1[0];

		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] > arr1Max) {
				arr1Max = arr1[i];
			} else if (arr1[i] < arr1Min) {
				arr1Min = arr1[i];
			}
		}
		System.out.printf("max : %d, min : %d\n", arr1Max, arr1Min);
		String flush = sc.nextLine();
		System.out.println();

		// 문제5. 별도의 배열을 선언하여 양의 정수 10개를 입력받아 배열에 저장하고, 배열에 있는 정수 중에서 3의 배수만 출력해보자.
		System.out.println("문제5");
		System.out.print("양의 정수 10개 입력>");
		String[] array = sc.nextLine().split(" ");
		int[] arr = new int[array.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(array[i]);
			if (arr[i] % 3 == 0) {
				System.out.print(arr[i] + " ");
			}
		}
		System.out.println();
		System.out.println();

		// 추가문제)
		// 문제1) 다음은 키보드로부터 학생수와 각 학생들의 점수를 입력받아서, 최고 점수 및 평균 점수를 구하는 프로그램입니다.
		// 실행결과를 보고, 알맞게 작성해보세요.
		// 문제출처, 이것이 자바다 183페이지 9번
		System.out.println("추가문제1");

		int[] scores = new int[1];
		boolean run = true;
		while (run) {
			System.out.println("---------------------------------------------");
			System.out.println("1.학생 수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
			System.out.println("---------------------------------------------");
			System.out.println("선택>");
			int menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("학생 수>");
				int numStudent = sc.nextInt();
				scores = new int[numStudent];
				break;
			case 2:
				for (int i = 0; i < scores.length; i++) {
					System.out.printf("scores[%d]>\n", i);
					int scoreStudent = sc.nextInt();
					scores[i] = scoreStudent;
				}
				break;
			case 3:
				for (int i = 0; i < scores.length; i++) {
					System.out.printf("scores[%d]: %d\n", i, scores[i]);
				}
				break;
			case 4:
				int max = scores[0];
				for (int i = 0; i < scores.length; i++) {
					if (scores[i] > max) {
						max = scores[i];
					}
				}
				System.out.println("최고 점수 : " + max);
				
				int sum = 0;
				for (int i = 0; i < scores.length; i++) {
					sum += scores[i];
				}
				System.out.println("평균 점수 : " + sum / scores.length);
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
