package com.yedam.java.app.emp13;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.java.app.common.DAO;

public class EmpDAOImpl extends DAO implements EmpDAO {
	
	private static EmpDAO instance = new EmpDAOImpl();
	private EmpDAOImpl() {}
	public static EmpDAO getInstance() {
		return instance;
	}

	@Override
	public List<Emp13> selectAll() {
		List<Emp13> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM emp13 ORDER BY employee_id";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Emp13 emp = new Emp13();
				emp.setEmployeeId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setJobId(rs.getString(3));
				emp.setSalary(rs.getInt(4));
				emp.setCommissionPst(rs.getString(5));
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

	@Override
	public Emp13 selectOne(int departmentId) {
		Emp13 emp = null;
		try {
			connect();
			String select = "SELECT * FROM emp13 WHERE employee_id = ?";
			pstmt = con.prepareStatement(select);
			pstmt.setInt(1, departmentId);
			rs = pstmt.executeQuery();

			emp = new Emp13();
			if (rs.next()) {
				emp.setEmployeeId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setJobId(rs.getString(3));
				emp.setSalary(rs.getInt(4));
				emp.setCommissionPst(rs.getString(5));
				emp.setDepartmentName(rs.getString(6));
				emp.setLocationId(rs.getInt(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}

		return emp;
	}

	@Override
	public void insert(Emp13 emp) {
		try {
			connect();
			String insert = "INSERT INTO emp13 VALUES (?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setInt(1, emp.getEmployeeId());
			pstmt.setString(2, emp.getFirstName());
			pstmt.setString(3, emp.getJobId());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setString(5, emp.getCommissionPst());
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

	@Override
	public void update(Emp13 emp) {
		try {
			connect();
			String update = "UPDATE emp13 SET salary = ? WHERE employee_id = ?";
			pstmt = con.prepareStatement(update);
			pstmt.setInt(1, emp.getSalary());
			pstmt.setInt(1, emp.getEmployeeId());
			
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
			String delete = "DELETE FROM emp13 WHERE employee_id = ?";
			pstmt = con.prepareStatement(delete);
			pstmt.setInt(1, departmentId);
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result + "건 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
