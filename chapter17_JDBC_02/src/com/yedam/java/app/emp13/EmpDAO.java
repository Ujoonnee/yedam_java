package com.yedam.java.app.emp13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Emp emp = null;

	// DB connection
	private String jdbcDriver = "org.sqlite.JDBC";
	private String url = "jdbc:sqlite:/C:/dev/workspace/YedamDataBase.db";

	// singleton
	private static EmpDAO instance = new EmpDAO();

	private EmpDAO() {
	}

	public static EmpDAO getInstance() {
		return instance;
	}

	// DB 접속
	public void connect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(url);

		} catch (ClassNotFoundException e) {
			System.out.println("DB 연결 실패");
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
		}
	}

	// PreparedStatement
	// SQL 실행
	// 결과 출력, 연산
	public List<Emp> selectAll() {
		List<Emp> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM emp13 ORDER BY employee_id";
			pstmt = conn.prepareStatement(select);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				emp = new Emp();
				emp.setEmployeeId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setJobId(rs.getString(3));
				emp.setSalary(rs.getInt(4));
				emp.setCommissionPct(rs.getString(5));
				emp.setDepartmentName(rs.getString(6));
				emp.setLocationId(rs.getInt(7));
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	public Emp selectOne(int employeeId) {
		try {
			connect();
			String select = "SELECT * FROM emp13 WHERE employee_id = ?";
			pstmt = conn.prepareStatement(select);
			pstmt.setInt(1, employeeId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				emp = new Emp();
				emp.setEmployeeId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setJobId(rs.getString(3));
				emp.setSalary(rs.getInt(4));
				emp.setCommissionPct(rs.getString(5));
				emp.setDepartmentName(rs.getString(6));
				emp.setLocationId(rs.getInt(7));
			} else {
				emp = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return emp;
	}

	public void insert(Emp emp) {
		try {
			connect();
			String insert = "INSERT INTO emp13 VALUES (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, emp.getEmployeeId());
			pstmt.setString(2, emp.getFirstName());
			pstmt.setString(3, emp.getJobId());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setString(5, emp.getCommissionPct());
			pstmt.setString(6, emp.getDepartmentName());
			pstmt.setInt(7, emp.getLocationId());

			int result = pstmt.executeUpdate();

			System.out.println(result + "건 등록");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
	}
	
	public void update(Emp emp) {
		try {
			connect();
			String update = "UPDATE emp13 SET salary = ? WHERE employee_id = ?";
			pstmt = conn.prepareStatement(update);
			pstmt.setInt(1, emp.getSalary());
			pstmt.setInt(2, emp.getEmployeeId());
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건 수정");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	public void delete(int employeeId) {
		try {
			connect();
			String delete = "DELETE FROM emp13 WHERE employee_id = ?";
			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, employeeId);
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건 삭제");
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			disconnect();
		}
	}

	// 자원 해제
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
		}
	}
}
