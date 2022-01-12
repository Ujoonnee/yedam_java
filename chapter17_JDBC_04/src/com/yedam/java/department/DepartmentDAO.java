package com.yedam.java.department;

import java.util.List;

public interface DepartmentDAO {

	// 전체조회
	public List<Department> selectAll();
	
	// 단건조회
	public Department selectOne(int departmentId);
	
	// 등록
	public void insert(Department dept);
	
	// 수정
	public void update(Department dept);
	
	// 삭제
	public void delete(int departmentId);
}
