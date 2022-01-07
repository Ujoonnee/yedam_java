package Assignment.Jan07;

public class Book {

	private static int serialNo = 0;
	private String name;
	private String author;
	private boolean isOut = false;

	Book(String name, String author) {
		this.name = name;
		this.author = author;
		serialNo++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isOut() {
		return isOut;
	}

	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}
	
	
	
}
