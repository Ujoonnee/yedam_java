package com.yedam.java.example;

public class StudentScores {

	private String name;
	private int classroom;
	private int korean;
	private int english;
	private int math;
	
	StudentScores(String name, int classroom) {
		this.name = name;
		this.classroom = classroom;
	}

	public String getName() {
		return name;
	}

	public int getKorean() {
		return korean;
	}

	public void setKorean(int korean) {
		this.korean = korean;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
	public int getTotal() {
		int total = 0;
		total = korean + english + math;
		return total;
	}
	
	public double getAverage() {
		double avg = 0;
		avg = (korean + english + math) / 3.0;
		return avg;
	}
	
}
