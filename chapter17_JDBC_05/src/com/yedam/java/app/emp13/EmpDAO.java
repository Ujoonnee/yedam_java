package com.yedam.java.app.emp13;

import java.util.List;

public interface EmpDAO {
	
	public List<Emp13> selectAll();
	
	public Emp13 selectOne(int departmentId);
	
	public void insert(Emp13 emp);
	
	public void update(Emp13 emp); 
	
	public void delete(int departmentId);

}
