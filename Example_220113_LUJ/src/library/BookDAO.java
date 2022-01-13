package library;

import java.util.List;

public interface BookDAO {

	// 전체조회
	public List<Book> showInfoAll();
	
	// 단건조회
	public Book showInfoOne(String bookName);
	
	// 책 등록
	public void addBook(Book book);
	
	// 책 대여, 반납
	public void updateBook(Book book);

	
}
