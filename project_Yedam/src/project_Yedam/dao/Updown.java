package project_Yedam.dao;

public class Updown {

	private String userId;
	private int articleNum;
	private String boardType;
	private int up = 0;
	private int down = 0;
	
	Updown(){}
	public Updown(int articleNum, String userId, String boardType){
		this.articleNum = articleNum;
		this.userId = userId;
		this.boardType = boardType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public int getUp() {
		return up;
	}
	public void setUp(int up) {
		this.up = up;
	}
	public int getDown() {
		return down;
	}
	public void setDown(int down) {
		this.down = down;
	}
	
	
	
	
	
	
}
