package com.yedam.java.chapter0801;

public interface RemoteControl {
	
	// 상수
	public final static int MAX_VOLUME = 10;
	public int MIN_VOLUME = 0;	// final static을 명시적으로 선언하지 않더라도 인터페이스에서는 자동으로 상수화
	
	// 추상메서드
	public abstract void turnOn();
	public void turnOff();	// abstract 없이도 자동으로 추상메소드화
	public void setVolume(int volume);
}
