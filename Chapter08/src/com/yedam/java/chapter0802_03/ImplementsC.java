package com.yedam.java.chapter0802_03;

public class ImplementsC implements InterfaceC {

	@Override
	public void methodA() {
		System.out.println("ImplementsC - methodA() 실행");
	}

	@Override
	public void methodB() {
		System.out.println("ImplementsC - methodB() 실행");
	}

	@Override
	public void methodC() {
		System.out.println("ImplementsC - methodC() 실행");
	}

}
