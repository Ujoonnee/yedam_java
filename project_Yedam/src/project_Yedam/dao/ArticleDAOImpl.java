package project_Yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project_Yedam.VO.Article;
import project_Yedam.VO.User;

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
				article.setPostTime(rs.getLong(7));
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
			String select = "SELECT * FROM articles WHERE article_num = ? AND board_type = ?";
			System.out.print("\n글 번호를 입력하세요.\n>> ");
			pstmt = con.prepareStatement(select);
			pstmt.setInt(1, sc.nextInt());
			pstmt.setString(2, boardType);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new Article();
				article.setArticleNum(rs.getInt(1));
				article.setBoardType(rs.getString(2));
				article.setPosterId(rs.getString(3));
				article.setPosterName(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setPostTime(rs.getLong(7));
				article.setLikeNum(rs.getInt(8));
				article.setUnlikeNum(rs.getInt(9));
				article.setPageView(rs.getInt(10));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return article;
	}
	

	@Override
	public void insert(Article article) {
		try {
			connect();
			String insert = "INSERT INTO articles (board_type, poster_id, poster_name, title, content, post_time, like_num, unlike_num, page_view) VALUES (?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setString(1, article.getBoardType());
			
			// specify User with article.getPosterId()
			ProjectDAO<User, String> userDao = UserDAOImpl.getInstance();
			List<User> userList = userDao.selectAll();
			User user = null;
			for (User u : userList) {
				if (u.getId().equals(article.getPosterId())) {
					user = u;
				}
			}
			pstmt.setString(2, article.getPosterId());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, article.getTitle());
			pstmt.setString(5, article.getContent());
			pstmt.setLong(6, article.getPostTime());
			pstmt.setInt(7, article.getLikeNum());
			pstmt.setInt(8, article.getUnlikeNum());
			pstmt.setInt(9, article.getPageView());

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
			String update = "UPDATE articles SET title = ?, content= ?, like_num = ?, unlike_num = ?, page_view = ? WHERE  article_num = ? AND board_type = ?";
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getLikeNum());
			pstmt.setInt(4, article.getUnlikeNum());
			pstmt.setInt(5, article.getPageView());
			pstmt.setInt(6, article.getArticleNum());
			pstmt.setString(7, article.getBoardType());

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
			String delete = "DELETE FROM articles WHERE article_num = ? AND board_type = ?";
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
