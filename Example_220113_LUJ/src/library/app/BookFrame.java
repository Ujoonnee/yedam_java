package library.app;

import java.util.List;
import java.util.Scanner;

import library.Book;
import library.BookDAO;
import library.BookDAOImpl;

public class BookFrame {

	private BookDAO dao = BookDAOImpl.getInstance();
	private Scanner sc = new Scanner(System.in);

	public BookFrame() {
		while (true) {
			// 메뉴 출력
			menuPrint();

			// 메뉴 선택
			int menuNo = menuSelect();

			// 각 메뉴별 기능 실행
			if (menuNo == 1) {					// 전체 도서목록
				showAllBooks();
			} else if (menuNo == 2) {			// 책 제목으로 도서 검색
				searchBook();
			} else if (menuNo == 3) {			// 키워드를 포함하는 내용의 도서 검색
				searchContent();
			} else if (menuNo == 4) {			// 대출가능한 도서목록
				showAvailableBook();
			} else if (menuNo == 5) {			// 대여
				updateBookStatus(1);
			} else if (menuNo == 6) {			// 반납
				updateBookStatus(0);
			} else if (menuNo == 7) {			// 새로운 도서 등록
				registerBook();
			} else if (menuNo == 9) {
				System.out.println("프로그램 종료");
				break;
			}
		}
	}

	void menuPrint() {
		System.out.println();
		System.out.println("===============================================================================");
		System.out.println("1.전체조회 | 2.단건조회 | 3.내용검색 | 4.대여가능 | 5.책 대여 | 6.책 반납 | 7.책 등록 | 9.종료");
		System.out.println("===============================================================================");
		System.out.println("선택> ");
	}

	int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("없는 메뉴입니다.");
		}
		return menuNo;
	}

	// 전체 도서 조회
	void showAllBooks() {
		List<Book> list = dao.showInfoAll();

		for (Book book : list) {
			System.out.println(book);
		}
	}

	// 책 제목으로 도서 검색
	void searchBook() {
		String name = inputBookName();
		Book book = dao.showInfoOne(name);
		System.out.println(((book != null)? book : "검색결과가 없습니다."));
	}

	// 책 제목 입력
	String inputBookName() {
		System.out.println("책제목> ");
		String name = sc.nextLine();
		return name;
	}

	// 키워드를 포함하는 내용의 도서 검색
	void searchContent() {
		System.out.println("검색내용> ");
		String keyword = sc.nextLine();
		List<Book> list = dao.showInfoAll();

		int count = 0;
		for (Book book : list) {
			if (book.getContent().contains(keyword)) {			 // 키워드 포함 여부 확인
				System.out.println(book);
				count++;
			}
		}
		if (count == 0) {
			System.out.println("검색 결과가 없습니다.");
		}
	}

	// 대출 가능 장서
	void showAvailableBook() {
		List<Book> list = dao.showInfoAll();

		for (Book book : list) {
			if (book.isOut() == 0) {							 // 전체 도서의 대출여부 확인
				System.out.println(book);
			}
		}
	}

	// 도서 대출(1) 및 반납(0)
	void updateBookStatus(int isOut) {
		String name = inputBookName();
		if (dao.showInfoOne(name).isOut() != isOut) { 			// 해당 도서의 대출 or 반납 여부 확인
			Book book = new Book();
			book.setName(name);
			book.setOut(isOut);

			dao.updateBook(book);

			// 정상 대여 or 반납
			if (isOut == 1) {
				System.out.println("대여되었습니다.");
			} else {
				System.out.println("반납되었습니다.");
			}

			// 대여 or 반납 실패
		} else if (isOut == 1) {
			System.out.println("대여중입니다.");
		} else if (isOut == 0) {
			System.out.println("이미 반납된 도서입니다.");
		}
	}

	// 도서 등록
	void registerBook() {
		Book book = new Book();
		book.setName(inputBookName());
		System.out.println("저자명> ");
		book.setWriter(sc.nextLine());
		System.out.println("내용> ");
		book.setContent(sc.nextLine());

		dao.addBook(book);
	}
}
