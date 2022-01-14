package project_Yedam.VO;

public class Letter {
	
	private static int serial = 0;
	private int letterNum;
	private String sender;
	private String recipient;
	private String content;
	private String timestamp;
	
	public Letter() {
		serial++;
		letterNum = serial;
	}
	
	public int getLetterNum() {
		return letterNum;
	}
	public void setLetterNum(int letterNum) {
		this.letterNum = letterNum;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "보낸 사람 : " + sender + "\t\t받는 사람 : " + recipient + "\n보낸 시간 : " + timestamp + "\n\n" + content;
	}
	
}
