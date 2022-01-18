package project_Yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdownDAOImpl extends DAO implements ProjectDAO<Updown, String> {

	private static ProjectDAO<Updown, String> instance = new UpdownDAOImpl();

	private UpdownDAOImpl() {}

	public static ProjectDAO<Updown, String> getInstance() {
		return instance;
	}

	@Override
	public List<Updown> selectAll() {
		List<Updown> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM thumbs_updown ORDER BY article_num";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Updown thumbs = new Updown();
				thumbs.setUserId(rs.getString(1));
				thumbs.setArticleNum(rs.getInt(2));
				thumbs.setUp(rs.getInt(3));
				thumbs.setDown(rs.getInt(4));


				list.add(thumbs);
			}

		} catch (Exception e) {
			System.out.println("좋아요 싫어요 호출 실패");
		} finally {
			disconnect();
		}

		return list;
	}

	@Override
	public Updown selectOne(String userId) {
		Updown user = null;

		try {
			connect();
			String select = "SELECT * FROM thumbs_updown WHERE article_num = ? AND user_id = ?";
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Updown thumbs = new Updown();
				thumbs.setUserId(rs.getString(1));
				thumbs.setArticleNum(rs.getInt(2));
				thumbs.setUp(rs.getInt(3));
				thumbs.setDown(rs.getInt(4));
			}

		} catch (Exception e) {
			System.out.println("error from thumbs_updown selectOne()");
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return user;
	}

	// register user
	@Override
	public void insert(Updown thumbs) {
		try {
			connect();
			String insert = "INSERT INTO thumbs_updown VALUES (?,?,?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setString(1, thumbs.getUserId());
			pstmt.setInt(2, thumbs.getArticleNum());
			pstmt.setString(3, thumbs.getBoardType());
			pstmt.setInt(4, thumbs.getUp());
			pstmt.setInt(5, thumbs.getDown());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			disconnect();
		}
	}

	// change password
	@Override
	public void update(Updown thumbs) {
		try {
			connect();
			String update = "UPDATE thumbs_updown SET up = ?, down = ? WHERE user_id = ? AND article_num = ? AND board_type = ?";
			pstmt = con.prepareStatement(update);
			pstmt.setInt(1, thumbs.getUp());
			pstmt.setInt(2, thumbs.getDown());
			pstmt.setString(3, thumbs.getUserId());
			pstmt.setInt(4, thumbs.getArticleNum());
			pstmt.setString(5, thumbs.getBoardType());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	

	// delete user
	@Override
	public void delete(Updown thumbs) {
		try {
			connect();
			String delete = "DELETE FROM thumbs_updown WHERE article_num = ? and user_id = ?";
			pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, thumbs.getArticleNum());
			pstmt.setString(2, thumbs.getUserId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}


}
