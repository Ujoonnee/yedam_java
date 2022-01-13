package library;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.common.DAO;

public class BookDAOImpl extends DAO implements BookDAO {

	// singleton
	private static BookDAO instance = new BookDAOImpl();
	private BookDAOImpl() {}
	public static BookDAO getInstance() {
		return instance;
	}
	
	// 전체 도서 검색
	@Override
	public List<Book> showInfoAll() {
		List<Book> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM books";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setName(rs.getString(1));
				book.setWriter(rs.getString(2));
				book.setContent(rs.getString(3));
				book.setOut(rs.getInt(4));
			
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 책제목으로 도서 검색
	@Override
	public Book showInfoOne(String bookName) {
		Book book = null;
		try {
			connect();
			String select = "SELECT * FROM books WHERE book_name = ?";
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, bookName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				book = new Book();
				book.setName(rs.getString(1));
				book.setWriter(rs.getString(2));
				book.setContent(rs.getString(3));
				book.setOut(rs.getInt(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return book;
	}

	// 새로운 도서 등록
	@Override
	public void addBook(Book book) {
		try {
			connect();
			String insert = "INSERT INTO books VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setString(1, book.getName());
			pstmt.setString(2, book.getWriter());
			pstmt.setString(3, book.getContent());
			pstmt.setInt(4, book.isOut());
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "권이 등록되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 도서 대출 or 반납 상태 변경
	@Override
	public void updateBook(Book book) {
		try {
			connect();
			String update = "UPDATE books SET book_rental = ? WHERE book_name = ?";
			pstmt = con.prepareStatement(update);
			pstmt.setInt(1, book.isOut());
			pstmt.setString(2, book.getName());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

}
