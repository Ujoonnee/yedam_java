package project_Yedam.VO;

import java.sql.Timestamp;

public class Article {

	// field
	private int articleNum;
	private String boardType;
	private String posterId;					// userId
	private String posterName;
	private String title;
	private String content;
	private long postTime;
	private int likeNum = 0;
	private int unlikeNum = 0;
	private int pageView = 0;
	
	
	// constructor
	// todo : only Board.newArticle(), ArticleDao.selectAll() can makes articles
	Article() {}
	Article(String boardType, int articleNum) {
		// todo : consider setting articleNum by Auto increment in database
		this.articleNum = articleNum;
		this.boardType = boardType;
		this.postTime = System.currentTimeMillis();
	}
	
	public int getArticleNum() {
		return articleNum;
	}
	
	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

	public String getBoardType() {
		return boardType;
	}
	
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public String getPosterId() {
		return posterId;
	}

	public void setPosterId(String posterId) {
		this.posterId = posterId;
	}
	
	public String getPosterName() {
		return posterName;
	}
	
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getPostTime() {
		return postTime;
	}
	
	public void setPostTime(long postTime) {
		this.postTime = postTime;
	}

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public int getUnlikeNum() {
		return unlikeNum;
	}

	public void setUnlikeNum(int unlikeNum) {
		this.unlikeNum = unlikeNum;
	}

	public int getPageView() {
		return pageView;
	}

	public void setPageView(int pageView) {
		this.pageView = pageView;
	}
	
	
	public String printPostTime(int select) {
		
		String[] timeFormat = new Timestamp(this.postTime).toString().split(" ");
		
		if (select == 0) {
			return timeFormat[0];	// yyyy-MM-dd
			
		} else if (select == 1){
			return timeFormat[1].substring(0, 8);	// HH:mm:ss
		} else {
			return timeFormat[0] + " " + timeFormat[1];	// yyyy-MM-dd HH:mm:ss
		}
	}

	
	private String printPostTime() {

		String[] timeFormat = new Timestamp(this.postTime).toString().split(" ");
		
		long timeGap = System.currentTimeMillis() - this.postTime;
		long day = 1000 * 3600 * 24;
		
		boolean isOver1Day = (timeGap >= day)? true : false;
		
		if (isOver1Day) {
			return timeFormat[0];	// yyyy-MM-dd
			
		} else {
			return timeFormat[1].substring(0, 8);	// HH:mm:ss
		}
	}
	
	
	@Override
	public String toString() {
		ProjectDAO<User, String> userDao = UserDAOImpl.getInstance();
		return "번호 : " + articleNum + "	작성자 : " + userDao.selectOne(posterId).getName() + " 제목 : " + title + "	작성일 : " + printPostTime() + "	조회수 : " + pageView;
	}
	
	
	
}
