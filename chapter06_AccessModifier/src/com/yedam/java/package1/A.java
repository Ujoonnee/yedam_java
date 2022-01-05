package com.yedam.java.package1;

//class A {	// default visibility
public class A{
	
	// 필드
	A a1 = new A(true);
	A a2 = new A(1);
	A a3 = new A("String");
	
	
	// 생성자
	public A(boolean B) {}
	A(int b) {}
	private A(String b) {}
	
	
	
	public int field1;
	int field2;
	private int field3;
	
	public A() {
		field1 = 1;
		field2 = 1;
		field3 = 1;

		method1();
		method2();
		method3();
	}
	
	public void method1() {}
	void method2() {}
	private void method3() {}
	
}
