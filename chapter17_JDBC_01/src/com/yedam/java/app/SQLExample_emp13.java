package com.yedam.java.app;

import java.sql.*;

public class SQLExample_emp13 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 1. JDBC Driver 로딩
		Class.forName("org.sqlite.JDBC");
		
		// 2. DB서버 접속
		String url = "jdbc:sqlite:/C:/dev/workspace/YedamDataBase.db";
		Connection conn = DriverManager.getConnection(url);
		
		// ******************** INSERT ********************
		// 3. PreparedStatement 객체 생성
		String insert = "INSERT INTO emp13 VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(insert);
		pstmt.setInt(1, 777);
		pstmt.setString(2, "Yedam");
		pstmt.setString(3, "CEO");
		pstmt.setInt(4, 77777);
		pstmt.setDouble(5, 0.1);
		pstmt.setString(6, "Executive");
		pstmt.setInt(7, 777);
		
		// 4. SQL 실행
		int result = pstmt.executeUpdate();
		
		// 5. 결과 출력 or 연산
		System.out.println("insert 결과 : " + result);
		
		// ******************** UPDATE ********************
		// 3. PreparedStatement 객체 생성
		String update = "UPDATE emp13 SET job_id = ? WHERE job_id = ?";
		pstmt = conn.prepareStatement(update);
		pstmt.setString(1, "Executive");
		pstmt.setString(2, "CEO");
		
		// 4. SQL 실행
		result = pstmt.executeUpdate();
		
		// 5. 결과 출력 or 연산
		System.out.println("update 결과 : " + result);
		
		// ******************** SELECT ********************
		// 3. PreparedStatement 객체 생성
		String select = "SELECT * FROM emp13 ORDER BY salary";
		pstmt = conn.prepareStatement(select);
		
		// 4. SQL 실행
		ResultSet rs = pstmt.executeQuery();
		
		// 5. 결과 출력 or 연산
		while(rs.next()) {
			String emp13 = "이름 : " + rs.getString("first_name") + ", 직급 : " + rs.getString("job_id") + ", 연봉 : " + rs.getInt("salary");
			System.out.println(emp13);
		}
		
		// ******************** DELETE ********************
		// 3. PreparedStatement 객체 생성
		String delete = "DELETE FROM emp13 WHERE employee_id = ?";
		pstmt = conn.prepareStatement(delete);
		pstmt.setInt(1, 777);
		
		// 4. SQL 실행
		result = pstmt.executeUpdate();
		
		// 5. 결과 출력 or 연산
		System.out.println("delete 결과 : " + result);
		
		// 6. 자원 해제
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}
}
