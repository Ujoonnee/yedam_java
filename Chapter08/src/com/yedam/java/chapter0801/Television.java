package com.yedam.java.chapter0801;

public class Television implements RemoteControl {

	// 필드
	private int volume;	// 인터페이스에서는 상수만 선언 가능하기 때문에 구현클래스에서 필드 생성
	
	// 메서드
	@Override
	public void turnOn() {
		System.out.println("TV를 켭니다.");
	}

	@Override
	public void turnOff() {
		System.out.println("TV를 끕니다.");
	}

	@Override
	public void setVolume(int volume) {
		if(volume > RemoteControl.MAX_VOLUME) {
			this.volume = RemoteControl.MAX_VOLUME;
		} else if (volume < RemoteControl.MIN_VOLUME) {
			this.volume = RemoteControl.MIN_VOLUME;
		} else {
			this.volume = volume;
		}
		System.out.println("현재 볼륨 : " + this.volume);
	}
	

}
