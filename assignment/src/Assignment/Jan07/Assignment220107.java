package Assignment.Jan07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment220107 {
	// 문제) 다음은 도서관리프로그램 중 일부입니다.
	// 1) 메뉴는 다음과 같이 구성하세요.
	// 1.책정보 입력 \t 2.전체조회 \t 3.단건조회 \t 4.책 대여 \t 5.책 반납 \t 6.종료
	// 2) 입력되는 책정보는 책이름과 저자명입니다.
	// 3) 전체 조회 및 단건 조회 시 책번호, 책이름, 저자명, 해당 책의 대여여부(대여중, 대여가능)도 함께 출력되도록 하세요.
	// 4) 책 대여 시 해당 책의 대여여부를 확인해서
	// 이미 대여 중인 책일 경우 "대여 중입니다."라는 안내문구를 출력하고
	// 대여가 가능한 책일 경우 대여처리 후 "대여되었습니다"라고 출력하도록 하세요.
	// 5) 책 반납 시 반납처리 후 "반납되었습니다."라고 출력하도록 하세요.
	// 6) 종료시 "프로그램 종료"를 출력하도록 하세요.

	/*
	 * 중앙로 도서관에서 근무 중인 김씨의 오늘 업무일지는 다음과 같습니다. - 당일 들어온 책에 대해 입고처리 (참고, 책마다 고유번호가
	 * 자동으로 부여됩니다. ) - static 1) 혼자공부하는자바, 신용권 2) 이것이자바다, 신용권 3) 자바스크립트파워북, 에이포스트 4)
	 * 나는프로그래머다, 이상순 5) 오늘부터개발자, 김용닥 6) SQL의 비밀노트, 김예담 - 현재 보유 중인 책 목록 확인 - 이용자가 요청한
	 * 책 정보 확인 1) 이것이자바다 2) 오늘부터개발자 - 책 대여 1) 나는프로그래머다 -> 정상대여 2) 혼자공부하는자바 -> 정상대여
	 * 3) 나는프로그래머다 -> 대여 중 - 책 반납 1) 혼자공부하는자바
	 */

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Book> list = new ArrayList<Book>();
		

		boolean run = true;

		while (run) {
			System.out.println("--------------------------------------------------------------");
			System.out.println("1.책정보 입력 | 2.전체조회 | 3.단건조회 | 4.책 대여 | 5.책 반납 | 6.종료");
			System.out.println("--------------------------------------------------------------");

			int menu = Integer.parseInt(sc.nextLine());

			switch (menu) {
			case 1:
				// book information
				System.out.println("책 이름>");
				String name = sc.nextLine();
				System.out.println("작가 이름>");
				String author = sc.nextLine();
				Book book = new Book(name, author);

				list.add(book);
				break;

			case 2:
				// information all
				System.out.println();
				System.out.printf("%-5s \t %-15s \t %-6s \t %-6s\n", "도서 번호", "도서명", "저자", "대여 가능 여부");
				System.out.println("--------------------------------------------------------------");
				for (Book books : list) {
					String rent = (books.isOut()) ? "대여 중" : "대여 가능";
					System.out.printf("%-5d \t %-15s \t %-6s \t %-6s\n", books.getBookNo(), books.getName(),
							books.getAuthor(), rent);
				}
				System.out.println();
				break;

			case 3:
				// information selected
				System.out.println("제목>");
				String search = sc.nextLine();
				System.out.println();
				System.out.printf("%-5s \t %-15s \t %-6s \t %-6s\n", "도서 번호", "도서명", "저자", "대여 가능 여부");
				System.out.println("--------------------------------------------------------------");
				for (Book books : list) {
					if (books.getName().equals(search)) {
						String rent = (books.isOut()) ? "대여 중" : "대여 가능";
						System.out.printf("%-5d \t %-15s \t %-6s \t %-6s\n", books.getBookNo(), books.getName(),
								books.getAuthor(), rent);
					}
				}
				System.out.println();
				break;

			case 4:
				// book rent
				System.out.println("대여할 책 제목>");
				String rental = sc.nextLine();
				for (Book books : list) {
					if (books.getName().equals(rental)) {
						if (books.isOut() == false) {
							books.setOut(true);
							System.out.println("대여되었습니다.");
						} else {
							System.out.println("대여 중");
						}
					}
				}
				System.out.println();
				break;

			case 5:
				// book return
				System.out.println("반납할 책 제목>");
				String returnBook = sc.nextLine();
				for (Book books : list) {
					if (books.getName().equals(returnBook) && (books.isOut() == true)) {
						books.setOut(false);
						System.out.println("반납되었습니다.");
						break;
					} else {
						continue;
					}
				}
				System.out.println();
				break;

			case 6:
				run = false;
				System.out.println("프로그램 종료");
				break;

			}

		}
		sc.close();
	}
}
