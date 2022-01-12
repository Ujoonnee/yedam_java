package com.yedam.java.department;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.java.common.DAO;

public class DepartmentDAOImpl extends DAO implements DepartmentDAO {

	private static DepartmentDAO instance = new DepartmentDAOImpl();
	private DepartmentDAOImpl() {}
	public static DepartmentDAO getInstance() {
		return instance;
	}
	
	@Override
	public List<Department> selectAll() {
		List<Department> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM departments ORDER BY department_id";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Department dept = new Department();
				dept.setDepartmentId(rs.getInt(1));
				dept.setDepartmentName(rs.getString(2));
				dept.setManagerId(rs.getString(3));
				dept.setLocationId(rs.getInt(4));
				
				list.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	@Override
	public Department selectOne(int departmentId) {
		Department dept = null;
		try {
			connect();
			String select = "SELECT * FROM departments WHERE department_id = ?";
			pstmt = con.prepareStatement(select);
			pstmt.setInt(1, departmentId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dept = new Department();
				dept.setDepartmentId(departmentId);
				dept.setDepartmentName(rs.getString(2));
				dept.setManagerId(rs.getString(3));
				dept.setLocationId(rs.getInt(4));
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return dept;
	}

	@Override
	public void insert(Department dept) {
		try {
			connect();
			String insert = "INSERT INTO departments VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setInt(1, dept.getDepartmentId());
			pstmt.setString(2, dept.getDepartmentName());
			pstmt.setString(3, dept.getManagerId());
			pstmt.setInt(4, dept.getLocationId());
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건 등록");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void update(Department dept) {
		try {
			connect();
			String update = "UPDATE departments SET department_name = ? WHERE department_id = ?";
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, dept.getDepartmentName());
			pstmt.setInt(2, dept.getDepartmentId());;
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건 수정");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void delete(int departmentId) {
		try {
			connect();
			String delete = "DELETE FROM departments WHERE department_id = ?";
			pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, departmentId);
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건 삭제");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}

}
