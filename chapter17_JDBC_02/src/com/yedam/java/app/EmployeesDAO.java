package com.yedam.java.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDAO {

	// sqlite 연결정보
	String jdbc_driver = "org.sqlite.JDBC";
	String jdbc_url = "jdbc:sqlite:/C:/dev/workspace/YedamDataBase.db";

	// 각 메서드에서 공통적으로 사용하는 필드
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// singleton
	private static EmployeesDAO instance = new EmployeesDAO();

	private EmployeesDAO() {
	}

	public static EmployeesDAO getInstance() {
		return instance;
	}

	// JDBC Driver 로딩
	// DB 서버 접속
	// -> connect()
	public void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url);
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
		}
	}

	// PreparedStatement 객체 생성
	// SQL 실행
	// 결과 출력 or 연산
	// -> 각 CRUD 메서드로 반복적으로 사용

	// 전체조회
	public List<Employee> selectAll() {
		List<Employee> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM employees ORDER BY employee_id";
			pstmt = conn.prepareStatement(select);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCommissionPct(rs.getString("commission_pct"));
				emp.setManagerId(rs.getString("manager_id"));
				emp.setDepartmentId(rs.getString("department_id"));

				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 단건조회
	public Employee selectOne(int employeeId) {
		Employee emp = null;
		try {
			connect();
			String select = "SELECT * FROM employees WHERE employee_id = ?";
			pstmt = conn.prepareStatement(select);
			pstmt.setInt(1, employeeId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getInt("salary"));
				emp.setCommissionPct(rs.getString("commission_pct"));
				emp.setManagerId(rs.getString("manager_id"));
				emp.setDepartmentId(rs.getString("department_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return emp;
	}
	
	// 등록
	public void insert(Employee emp) {
		try {
			connect();
			String insert = "INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, emp.getEmployeeId());
			pstmt.setString(2, emp.getFirstName());
			pstmt.setString(3, emp.getLastName());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhoneNumber());
			pstmt.setString(6, emp.getHireDate());
			pstmt.setString(7, emp.getJobId());
			pstmt.setInt(8, emp.getSalary());
			pstmt.setString(9, emp.getCommissionPct());
			pstmt.setString(10, emp.getManagerId());
			pstmt.setString(11, emp.getDepartmentId());
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건이 등록되었습니다.");
			
		} catch (SQLException e) {
			
		} finally {
			disconnect();
		}
	}

	// 수정
	public void update(Employee emp) {
		try {
			connect();
			String update = "UPDATE employees SET salary = ? WHERE employee_id = ?";
			pstmt = conn.prepareStatement(update);
			pstmt.setInt(1, emp.getSalary());
			pstmt.setInt(2, emp.getEmployeeId());
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + " 건이 수정되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	// 삭제
	public void delete(int employeeId) {
		try {
			connect();
			String delete = "DELETE FROM employees WHERE employee_id = ?";
			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, employeeId);
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건이 수정되었습니다.");
					
		} catch (SQLException e) {
			
		} finally {
			disconnect();
		}
	}
	
	
	// 자원 해제 -> disconnect()
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
