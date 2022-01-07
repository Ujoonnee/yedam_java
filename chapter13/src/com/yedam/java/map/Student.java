package com.yedam.java.map;

public class Student {

	public int sno; // 학번
	public String name; // 이름

	public Student(int sno, String name) {
		this.sno = sno;
		this.name = name;
	}

	@Override
	public int hashCode() { // 동일한 객체인지 확인
		return name.hashCode() + sno;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Student) {
			Student student = (Student) obj;
			
			boolean isSno = (sno == student.sno);
			boolean isName = (name == student.name);
			
			return isSno && isName;
		} else {
			return false;
		}
	}

}
