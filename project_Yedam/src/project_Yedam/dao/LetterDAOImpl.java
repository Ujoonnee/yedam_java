package project_Yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project_Yedam.VO.Letter;

public class LetterDAOImpl extends DAO implements ProjectDAO<Letter, String> {

	private static ProjectDAO<Letter, String> instance = new LetterDAOImpl();

	private LetterDAOImpl() {
	}

	public static ProjectDAO<Letter, String> getInstance() {
		return instance;
	}

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
				letter.setLetterNum(rs.getInt(1));
				letter.setSenderId(rs.getString(2));
				letter.setRecipientId(rs.getString(3));
				letter.setContent(rs.getString(4));
				letter.setTimestamp(rs.getLong(5));
				letter.setRead(rs.getInt(6));
				
				list.add(letter);
			}

		} catch (Exception e) {
			System.out.println("전체 쪽지 호출 실패");
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	@Override
	public Letter selectOne(String p) {
		return null;
	}

	@Override
	public void insert(Letter letter) {
		try {
			connect();
			String insert = "INSERT INTO letters (sender_id, recipient_id, content, timestamp, is_read) VALUES (?,?,?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setString(1, letter.getSenderId());
			pstmt.setString(2, letter.getRecipientId());
			pstmt.setString(3, letter.getContent());
			pstmt.setLong(4, letter.getTimestamp());
			pstmt.setInt(5, letter.isRead());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("쪽지 발송 실패");
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void update(Letter letter) {
		try {
			connect();
			String update = "UPDATE letters SET is_read = ? WHERE timestamp = ?";
			pstmt = con.prepareStatement(update);
			pstmt.setInt(1, 1);
			pstmt.setLong(2, letter.getTimestamp());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("쪽지 수정 실페");
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void delete(Letter letter) {
		try {
			connect();
			String delete = "DELETE FROM letters WHERE timestamp = ?";
			pstmt = con.prepareStatement(delete);
			pstmt.setLong(1, letter.getTimestamp());
			System.out.println(letter.getTimestamp());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("쪽지 삭제 실패");
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}


}
