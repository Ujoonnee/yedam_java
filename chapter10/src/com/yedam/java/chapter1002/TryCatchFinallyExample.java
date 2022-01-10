package com.yedam.java.chapter1002;

public class TryCatchFinallyExample {

	public static void main(String[] args) {
		
		try {
			Class clazz = Class.forName("Java.lang.String2");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 존재합니다.");
		}
		
		String data1 = null;
		String data2 = null;
		
		try {
			data1 = args[0];
			data2 = args[1];
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("실행 매개값의 수가 부족합니다.");
//			return;
		}
		System.out.println("추가실행");
		
		try {
			int value1 = Integer.parseInt(data1);
			int value2 = Integer.parseInt(data2);
			
			int result = value1 + value2;
			System.out.println(value1 + " + " + value2 + " = " + result);
			
		} catch(NumberFormatException e){
			System.out.println("숫자로 변환할 수 없습니다.");
		} finally {
			System.out.println("다시 실행하세요.");
		}

		System.out.println();
		
		try {
			data1 = args[0];
			data2 = args[1];
			
			int value1 = Integer.parseInt(data1);
			int value2 = Integer.parseInt(data2);
			
			int result = value1 + value2;
			System.out.println(value1 + " + " + value2 + " = " + result);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("실행 매개값의 수가 부족합니다.");

		} catch (NumberFormatException e) {
			System.out.println("숫자로 변환할 수 없습니다.");

		} catch (Exception e) {
			System.out.println("실행에 문제가 있습니다.");
			
		} finally {
			System.out.println("반드시 실행되어야 합니다.");
			
		}
		
		try {
			findClass();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void findClass() throws ClassNotFoundException {
		Class clazz = Class.forName("Java.lang.String2");
	}
}
