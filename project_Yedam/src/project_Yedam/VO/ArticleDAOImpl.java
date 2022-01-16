package project_Yedam.VO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleDAOImpl extends DAO implements ProjectDAO<Article, String> {
	
	private static ProjectDAO<Article, String> instance = new ArticleDAOImpl();

	private ArticleDAOImpl() {}

	public static ProjectDAO<Article, String> getInstance() {
		return instance;
	}

	@Override
	public List<Article> selectAll() {
		List<Article> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM articles ORDER BY article_num";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Article article = new Article();
				article.setArticleNum(rs.getInt(1));
				article.setBoardType(rs.getString(2));
				article.setPosterId(rs.getString(3));
				article.setPosterName(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setPostTime(rs.getInt(7));
				article.setLikeNum(rs.getInt(8));
				article.setUnlikeNum(rs.getInt(9));
				article.setPageView(rs.getInt(10));
				
				list.add(article);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	@Override
	public Article selectOne(String boardType) {
		Article article = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			connect();
			String select = "SELECT * FROM articles WHERE article_num = ?, board_type = ?";
			System.out.print("글 번호를 입력하세요.\n> ");
			pstmt.setInt(1, sc.nextInt());
			pstmt.setString(2, boardType);
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new Article();
				article.setArticleNum(rs.getInt(1));
				article.setBoardType(rs.getString(2));
				article.setPosterId(rs.getString(3));
				article.setTitle(rs.getString(4));
				article.setContent(rs.getString(5));
				article.setPostTime(rs.getInt(6));
				article.setLikeNum(rs.getInt(7));
				article.setUnlikeNum(rs.getInt(8));
				article.setPageView(rs.getInt(9));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			disconnect();
		}

		return article;
	}
	

	@Override
	public void insert(Article article) {
		try {
			connect();
			String insert = "INSERT INTO articles VALUES (?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setInt(1, article.getArticleNum());
			pstmt.setString(2, article.getBoardType());
			
			// specify User with article.getPosterId()
			ProjectDAO<User, String> userDao = UserDAOImpl.getInstance();
			List<User> userList = userDao.selectAll();
			User user = null;
			for (User u : userList) {
				if (u.getId().equals(article.getPosterId())) {
					user = u;
				}
			}
			pstmt.setString(3, article.getPosterId());
			pstmt.setString(4, user.getName());
			pstmt.setString(5, article.getTitle());
			pstmt.setString(6, article.getContent());
			pstmt.setLong(7, article.getPostTime());
			pstmt.setInt(8, article.getUnlikeNum());
			pstmt.setInt(9, article.getUnlikeNum());
			pstmt.setInt(10, article.getPageView());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void update(Article article) {
		try {
			connect();
			String update = "UPDATE articles SET article_title = ?, article_content= ? WHERE  article_num = ?, board_type = ?";
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getArticleNum());
			pstmt.setString(4, article.getBoardType());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void delete(Article article) {
		try {
			connect();
			String delete = "DELETE FROM articles WHERE article_num = ?, board_type = ?";
			pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, article.getArticleNum());
			pstmt.setString(2, article.getBoardType());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			disconnect();
		}
	}
}
