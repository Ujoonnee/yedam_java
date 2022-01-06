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
