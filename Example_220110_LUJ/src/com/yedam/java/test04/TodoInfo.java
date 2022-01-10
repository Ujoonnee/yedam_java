package com.yedam.java.test04;

public class TodoInfo {

	private static int serialNo = 0;
	private int todoNo; 
	private String content;
	private boolean isDone = false;

	
	TodoInfo(String content) {
		serialNo++;
		this.todoNo = serialNo;
		this.content = content;
	}

	
	public int getTodoNo() {
		return todoNo;
	}
	
	public String getContent() {
		return content;
	}
	
	public boolean getIsDone() {
		return isDone;
	}
	
	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}
}
