package project_Yedam.VO;

public class FreeBoard {

	// articles in board
	
	private static int boardSerial = 0;			// all articles share serial
	private int boardNum;
	private String poster;					// userId
	private String title;
	private String content;
	private String postDate;
	private int likeNum = 0;
	private int unlikeNum = 0;
	private int pageView = 0;
	
	public FreeBoard() {
		// todo : consider setting bulletinNum by Auto increment in database
		boardSerial++;
		this.boardNum = boardSerial;
	}


	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
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

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
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


	@Override
	public String toString() {
		return "번호 : " + boardNum + "	작성자 : " + poster + " 제목 : " + title + "	작성일 : " + postDate
				+ "	조회수 : " + pageView;
	}
	
	
}
