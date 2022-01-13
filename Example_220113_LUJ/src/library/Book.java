package library;

public class Book {

	private String name;
	private String writer;
	private String content;
	private int isOut = 0;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int isOut() {
		return isOut;
	}
	public void setOut(int isOut) {
		this.isOut = isOut;
	}

	@Override
	public String toString() {
		return "책제목 : " + name + ", 저자명 : " + writer + ", 내용 : " + content + ", 대여여부 : " + ((isOut == 1) ? "대여중" : "대여가능") ;
	}

	
}
