package com.yedam.java.app;

import java.sql.*;

public class SQLExample {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// 1. JDBC Driver 로딩
		Class.forName("org.sqlite.JDBC");												// class 생성
		
		// 2. DB 서버 접속
		String url = "jdbc:sqlite:/C:/dev/workspace/YedamDataBase.db";					// db 주소
		Connection conn = DriverManager.getConnection(url);

		// ******************* INSERT *******************
		// 3. Statement or PreparedStatement 객체 생성
		String insert = "INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?,?)";			// 쿼리문 작성
		PreparedStatement pstmt = conn.prepareStatement(insert);						// Statement를 쓰면 직접 쿼리문의 내용을 다 작성해야해서 번거로움
		pstmt.setInt(1, 1000);															// 물음표 안의 내용을 setter로 넣어줌
		pstmt.setString(2, "Kil-dong");													// 인덱스랑 다르게 1부터 시작
		pstmt.setString(3, "Hong");														// 물음표의 갯수만큼 setter가 존재해야함
		pstmt.setString(4, "kdHong@gamil.com");
		pstmt.setString(5, "82.10.1234.1234");
		pstmt.setString(6, "21/11/05");
		pstmt.setString(7, "SA_REP");
		pstmt.setInt(8, 6000);
		pstmt.setDouble(9, 0.15);
		pstmt.setInt(10, 149);
		pstmt.setInt(11, 80);															// 쿼리문 안의 내용 완성
		
		// 4. SQL 실행
		int result = pstmt.executeUpdate();												// 실행 결과 횟수를 반환해줌
		
		// 5. 결과값 연산 or 출력
		System.out.println("insert 결과 : " + result);
		
		
		// ******************* UPDATE *******************
		// 3. Statement or PreparedStatement 객체 생성
		String update = "UPDATE employees SET last_name = ? WHERE employee_id = ?";
		pstmt = conn.prepareStatement(update);
		pstmt.setString(1, "Kang");
		pstmt.setInt(2, 1000);
		
		// 4. SQL 실행
		result = pstmt.executeUpdate();
		
		// 5. 결과값 연산 or 출력
		System.out.println("update 결과 : " + result);
		
		
		// ******************* SELECT *******************
		// 3. Statement or PreparedStatement 객체 생성
		String select = "SELECT * FROM employees ORDER BY employee_id";					// ORDER BY : 컬럼 기준으로 정렬시켜줌
		pstmt = conn.prepareStatement(select);											// SELECT라고 굳이 Statement 쓸 필요는 없음(setter 무필요)
		
		// 4. SQL 실행
		ResultSet rs = pstmt.executeQuery();											// ResultSet은 일단 iterator처럼 생각 
		
		// 5. 결과값 연산 or 출력
		while(rs.next()) {
			String name = "사원번호 : " + rs.getInt("employee_id") + ", 이름 : " + rs.getString("first_name") + " " + rs.getString("last_name");
			System.out.println(name);
		}
		
		
		// ******************* DELETE *******************
		// 3. Statement or PreparedStatement 객체 생성
		String delete = "DELETE FROM employees WHERE employee_id = ?";
		pstmt = conn.prepareStatement(delete);
		pstmt.setInt(1, 1000);
		
		// 4. SQL 실행
		result = pstmt.executeUpdate();
		
		// 5. 결과값 연산 or 출력
		System.out.println("delete 결과 : " + result);
		
		// 6. 자원 해제
		if(rs != null) rs.close();														// 해제는 역순으로
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
		
	}
}
