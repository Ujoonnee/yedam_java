package project_Yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project_Yedam.VO.AnonymousBoard;

public class AnonymousBoardDAOImpl extends DAO implements ProjectDAO<AnonymousBoard> {

	private static ProjectDAO<AnonymousBoard> instance = new AnonymousBoardDAOImpl();

	private AnonymousBoardDAOImpl() {
	}

	public static ProjectDAO<AnonymousBoard> getInstance() {
		return instance;
	}

	// show all FreeBoards
	@Override
	public List<AnonymousBoard> selectAll() {
		List<AnonymousBoard> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM anonymousBoard";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				AnonymousBoard anonymousBoard = new AnonymousBoard();
				anonymousBoard.setBoardNum(rs.getInt(1));
				anonymousBoard.setPoster(rs.getString(2));
				anonymousBoard.setTitle(rs.getString(3));
				anonymousBoard.setContent(rs.getString(4));
				anonymousBoard.setPostDate(rs.getString(5));
				anonymousBoard.setLikeNum(rs.getInt(6));
				anonymousBoard.setUnlikeNum(rs.getInt(7));
				anonymousBoard.setPageView(rs.getInt(8));
				
				list.add(anonymousBoard);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// register FreeBoard
	@Override
	public void insert(AnonymousBoard anonymousBoard) {
		try {
			connect();
			String insert = "INSERT INTO anonymousBoard VALUES (?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setInt(1, anonymousBoard.getBoardNum());
			pstmt.setString(2, anonymousBoard.getPoster());
			pstmt.setString(3, anonymousBoard.getTitle());
			pstmt.setString(4, anonymousBoard.getContent());
			pstmt.setString(5, anonymousBoard.getPostDate());
			pstmt.setInt(6, anonymousBoard.getLikeNum());
			pstmt.setInt(7, anonymousBoard.getUnlikeNum());
			pstmt.setInt(8, anonymousBoard.getPageView());

			int result = pstmt.executeUpdate();

			System.out.println(result + "건 등록");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// update content
	@Override
	public void update(AnonymousBoard anonymousBoard) {
		try {
			connect();
			String update = "UPDATE anonymousBoard SET anonymousBoard_title = ?, anonymousBoard_content= ? WHERE anonymousBoard_num= ?";
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, anonymousBoard.getTitle());
			pstmt.setString(2, anonymousBoard.getContent());
			pstmt.setInt(3, anonymousBoard.getBoardNum());

			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건 수정");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// delete FreeBoard
	@Override
	public void delete(AnonymousBoard anonymousBoard) {
		try {
			connect();
			String delete = "DELETE FROM anonymousBoard WHERE anonymousBoard_num = ?";
			pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, anonymousBoard.getBoardNum());

			int result = pstmt.executeUpdate();

			System.out.println(result + "건 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
