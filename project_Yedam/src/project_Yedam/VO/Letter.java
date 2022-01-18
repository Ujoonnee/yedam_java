package project_Yedam.VO;

import java.sql.Timestamp;

import project_Yedam.dao.ProjectDAO;
import project_Yedam.dao.UserDAOImpl;

public class Letter {

	private int letterNum;
	private String senderId;
	private String recipientId;
	private String content;
	private long timestamp;
	private int isRead = 0;

	public Letter() {
		this.timestamp = System.currentTimeMillis();
//		System.out.println(this.timestamp);
	}

	public int getLetterNum() {
		return letterNum;
	}

	public void setLetterNum(int letterNum) {
		this.letterNum = letterNum;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int isRead() {
		return this.isRead;
	}

	public void setRead(int isRead) {
		this.isRead = isRead;
	}

	public String printTimestamp(int select) {

		String[] timeFormat = new Timestamp(this.timestamp).toString().split(" ");

		if (select == 0) {
			return timeFormat[0]; // yyyy-MM-dd

		} else if (select == 1) {
			return timeFormat[1].substring(0, 8); // HH:mm:ss
		} else {
			return timeFormat[0] + " " + timeFormat[1].substring(0, 8); // yyyy-MM-dd HH:mm:ss
		}
	}

	private String printTimestamp() {

		String[] timeFormat = new Timestamp(this.timestamp).toString().split(" "); // [0] : yyyy-MM-dd, [1] : HH:mm:ss

		long timeGap = System.currentTimeMillis() - this.timestamp;
		long day = 1000 * 3600 * 24;

		boolean isOver1Day = (timeGap >= day) ? true : false;

		if (isOver1Day) {
			return timeFormat[0]; // yyyy-MM-dd

		} else {
			return timeFormat[1].substring(0, 8); // HH:mm:ss
		}
	}
	
	public String getSenderName() {
		ProjectDAO<User, String> userDao = UserDAOImpl.getInstance();
		String senderName = userDao.selectOne(senderId).getName();
		return senderName;
	}
	
	public String getRecipientName() {
		ProjectDAO<User, String> userDao = UserDAOImpl.getInstance();
		String recipientName = userDao.selectOne(recipientId).getName();
		return recipientName;
	}

	public void printLetterInfo(String mailboxType) {
		if (mailboxType.equals("받은쪽지함")) {
			System.out.println("보낸 사람 : " + getSenderName() + "\t보낸 시각 : " + printTimestamp(2) + "\n\n" + content);
		} else {
			System.out.println("받는 사람 : " + getRecipientName() + "\t보낸 시각 : " + printTimestamp(2) + "\n\n" + content);
		}
	}

	public void printList(String mailboxType) {
		
		switch (mailboxType) {
		case "받은쪽지함":
			System.out.println(letterNum + ".  " + printTimestamp() + "\t" + getSenderName() + ((isRead == 0) ? "\t읽지않음\t" : "\t읽음\t") + ((content.replace("\n", " ").length() < 20)? content.replace("\n", " ") : content.replace("\n", " ").substring(0,20) + "...\t"));
			break;
			
		case "보낸쪽지함":
			System.out.println(letterNum + ".  " + printTimestamp() + "\t" + getRecipientName() + ((isRead == 0) ? "\t읽지않음\t" : "\t읽음\t") + ((content.replace("\n", " ").length() < 20)? content.replace("\n", " ") : content.replace("\n", " ").substring(0,20) + "...\t"));
			break;
			
		default:
			System.out.println("목록 출력 오류");
			return;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
