package com.yedam.java.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample2 {

	public static void main(String[] args) {
		
		Map<Student, Integer> map = new HashMap<Student, Integer>();
		
		map.put(new Student(1, "홍길동"), 95);
		map.put(new Student(1, "홍길동"), 95);
		System.out.println(new Student(1, "홍길동").hashCode());
		System.out.println("홍길동".hashCode());
		
		System.out.println("총 Entry 수 : " + map.size());
		
		
	}
}
