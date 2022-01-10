package Assignment.Jan07;

import java.util.*;

public class Library {

	private List<Book> list;
	
	public Library() {
		list = new ArrayList<>();
	}
	
	public void addBook(String name, String author) {
		list.add(new Book(name, author));
	}
	
	public void printBookList() {
		System.out.println();
		System.out.printf("%-5s \t %-15s \t %-6s \t %-6s\n", "도서 번호", "도서명", "저자", "대여 가능 여부");
		System.out.println("--------------------------------------------------------------");
		for(Book book : list) {
			String rent = (book.isOut()) ? "대여 중" : "대여 가능";
			System.out.printf("%-5d \t %-15s \t %-6s \t %-6s\n", book.getBookNo(), book.getName(),
					book.getAuthor(), rent);
		}
	}
	
	public void selectBookInfo(String name) {
		System.out.println();
		System.out.printf("%-5s \t %-15s \t %-6s \t %-6s\n", "도서 번호", "도서명", "저자", "대여 가능 여부");
		System.out.println("--------------------------------------------------------------");
		for (Book book : list) {
			if (book.getName().equals(name)) {
				System.out.printf("%-5d \t %-15s \t %-6s \t %-6s\n",
						book.getBookNo(), book.getName(),
						book.getAuthor(), (book.isOut()) ? "대여 중" : "대여 가능");
			}
		}
		System.out.println();
	}
	
	public void rentBook(String name) {
		for (Book book : list) {
			if (book.getName().equals(name)) {
				if (book.isOut() == false) {
					book.setOut(true);
					System.out.println("대여되었습니다.");
				} else {
					System.out.println("대여 중");
				}
			}
		}
		System.out.println();
	}
	
	public void returnBook(String name) {
		for (Book book : list) {
			if (book.getName().equals(name) && (book.isOut() == true)) {
				book.setOut(false);
				System.out.println("반납되었습니다.");
				break;
			} else {
				continue;
			}
		}
		System.out.println();
	}
}
