package project_Yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project_Yedam.VO.Article;

public class FreeBoardDAOImpl extends DAO implements ProjectDAO<Article> {

	private static ProjectDAO<Article> instance = new FreeBoardDAOImpl();

	private FreeBoardDAOImpl() {
	}

	public static ProjectDAO<Article> getInstance() {
		return instance;
	}

	// show all FreeBoards
	@Override
	public List<Article> selectAll() {
		List<Article> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM freeBoard";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Article freeBoard = new Article();
				freeBoard.setBoardNum(rs.getInt(1));
				freeBoard.setPoster(rs.getString(2));
				freeBoard.setTitle(rs.getString(3));
				freeBoard.setContent(rs.getString(4));
				freeBoard.setPostDate(rs.getString(5));
				freeBoard.setLikeNum(rs.getInt(6));
				freeBoard.setUnlikeNum(rs.getInt(7));
				freeBoard.setPageView(rs.getInt(8));
				
				list.add(freeBoard);
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
	public void insert(Article freeBoard) {
		try {
			connect();
			String insert = "INSERT INTO freeBoard VALUES (?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setInt(1, freeBoard.getBoardNum());
			pstmt.setString(2, freeBoard.getPoster());
			pstmt.setString(3, freeBoard.getTitle());
			pstmt.setString(4, freeBoard.getContent());
			pstmt.setString(5, freeBoard.getPostDate());
			pstmt.setInt(6, freeBoard.getLikeNum());
			pstmt.setInt(7, freeBoard.getUnlikeNum());
			pstmt.setInt(8, freeBoard.getPageView());

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
	public void update(Article freeBoard) {
		try {
			connect();
			String update = "UPDATE freeBoard SET freeBoard_title = ?, freeBoard_content= ? WHERE freeBoard_num= ?";
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, freeBoard.getTitle());
			pstmt.setString(2, freeBoard.getContent());
			pstmt.setInt(3, freeBoard.getBoardNum());

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
	public void delete(Article freeBoard) {
		try {
			connect();
			String delete = "DELETE FROM freeBoard WHERE freeBoard_num = ?";
			pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, freeBoard.getBoardNum());

			int result = pstmt.executeUpdate();

			System.out.println(result + "건 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
