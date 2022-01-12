package com.yedam.app.employee;

import java.util.List;

public interface EmployeeDAO {

	// 전체조회
	public List<Employee> selectAll();
	
	// 단건조회
	public Employee selectOne(int employeeId);
	
	// 등록
	public void insert(Employee emp);
	
	// 수정
	public void update(Employee emp);
	
	// 삭제
	public void delete(int employeeId);
	
}
