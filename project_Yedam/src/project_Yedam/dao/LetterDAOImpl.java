package project_Yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project_Yedam.VO.Letter;

public class LetterDAOImpl extends DAO implements ProjectDAO<Letter> {

	private static ProjectDAO<Letter> instance = new LetterDAOImpl();

	private LetterDAOImpl() {
	}

	public static ProjectDAO<Letter> getInstance() {
		return instance;
	}

	// show all users
	@Override
	public List<Letter> selectAll() {
		List<Letter> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM letters";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Letter letter = new Letter();
				letter.setSender(rs.getString(1));
				letter.setRecipient(rs.getString(2));
				letter.setContent(rs.getString(3));
				letter.setTimestamp(rs.getString(4));
				
				list.add(letter);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// register user
	@Override
	public void insert(Letter letter) {
		try {
			connect();
			String insert = "INSERT INTO letters VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setString(1, letter.getSender());
			pstmt.setString(2, letter.getRecipient());
			pstmt.setString(3, letter.getContent());
			pstmt.setString(4, letter.getTimestamp());

			int result = pstmt.executeUpdate();

			System.out.println(result + "개 쪽지 보냄");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// change password
	@Override
	public void update(Letter letter) {
		try {
			connect();
			String update = "UPDATE letters SET letter_content = ? WHERE letter_no = ?";
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, letter.getContent());
			pstmt.setInt(2, letter.getLetterNum());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// delete user
	@Override
	public void delete(Letter letter) {
		try {
			connect();
			String delete = "DELETE FROM letters WHERE letter_no = ?";
			pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, letter.getLetterNum());

			int result = pstmt.executeUpdate();

			System.out.println(result + "명 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
