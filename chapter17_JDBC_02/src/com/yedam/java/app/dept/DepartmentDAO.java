package com.yedam.java.app.dept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

	// DB
	private String jdbcDriver = "org.sqlite.JDBC";
	private String url = "jdbc:sqlite:/C:/dev/workspace/YedamDataBase.db";

	// fields
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// singleton
	private static DepartmentDAO instance = new DepartmentDAO();

	private DepartmentDAO() {
	}

	public static DepartmentDAO getInstance() {
		return instance;
	}

	// methods
	public void connect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
			e.printStackTrace();
		}
	}

	public void insert(Department dept) {
		try {
			connect();
			String insert = "INSERT INTO departments VALUES (?,?,?,?)";
			pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, dept.getDepartmentId());
			pstmt.setString(2, dept.getDepartmentName());
			pstmt.setString(3, dept.getManagerId());
			pstmt.setInt(4, dept.getLocationId());
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건 등록");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	public List<Department> selectAll() {
		List<Department> list = new ArrayList<>();
		Department dept = null;
		try {
			connect();
			String select ="SELECT * FROM departments ORDER BY department_id";
			pstmt = conn.prepareStatement(select);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dept = new Department();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				dept.setManagerId(rs.getString("manager_id"));
				dept.setLocationId(rs.getInt("location_id"));
				list.add(dept);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	public Department selectOne(int departmentId) {
		Department dept = null;
		try {
			connect();
			String select ="SELECT * FROM departments WHERE department_id = ?";
			pstmt = conn.prepareStatement(select);
			pstmt.setInt(1, departmentId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dept = new Department();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				dept.setManagerId(rs.getString("manager_id"));
				dept.setLocationId(rs.getInt("location_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return dept;
	}

	public void update(Department dept) {
		try {
			connect();
			String update = "UPDATE departments SET department_name = ? WHERE department_id = ?";
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, dept.getDepartmentName());
			pstmt.setInt(2, dept.getDepartmentId());
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건 수정");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	public void delete(int departmentId) {
		try {
			connect();
			String delete = "DELETE FROM departments WHERE department_id = ?";
			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, departmentId);
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건 삭제");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	public void disconnect() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("자원 해제 실패");
			e.printStackTrace();
		}
	}

}
