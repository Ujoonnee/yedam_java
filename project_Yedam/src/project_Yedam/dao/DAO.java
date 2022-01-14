package project_Yedam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	// DB 연결정보
	private String jdbc_driver = "org.sqlite.JDBC";
	private String jdbc_url = "jdbc:sqlite:/C:/Users/admin/Desktop/JAVA/SQLite/YedamDataBase.db";
	
	// 각 메서드 공통필드
	protected Connection con = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	
	// connect()
	public void connect() {
			try {
				Class.forName(jdbc_driver);
				con = DriverManager.getConnection(jdbc_url);
				
			} catch (ClassNotFoundException e) {
				System.out.println("JDBC driver 로딩 실패");
				
			} catch (SQLException e) {
				System.out.println("DB 연결 실패");
			}
	}
	
	// disconnect()
	public void disconnect() {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				System.out.println("자원 해제 실패");
				e.printStackTrace();
			}
	}
}
