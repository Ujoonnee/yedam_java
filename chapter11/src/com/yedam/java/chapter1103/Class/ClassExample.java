package com.yedam.java.chapter1103.Class;

import com.yedam.java.chapter1101.object.Member;

public class ClassExample {

	public static void main(String[] args) throws ClassNotFoundException {
//		Class clazz = Class.forName("com.yedam.java.chapter1101.object.Member");
		Member member = new Member("홍길동");
		Class clazz = member.getClass();
		
		System.out.println(clazz.getName());
		System.out.println(clazz.getConstructors());
		System.out.println(clazz.getMethods());
	}
}
