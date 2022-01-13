package com.yedam.java.app.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	private String jdbcDriver = "org.sqlite.JDBC";
	private String jdbcUrl = "jdbc:sqlite:/C:/dev/workspace/YedamDataBase.db";
	
	protected Connection con = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	
	public void connect() {
		try {
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(jdbcUrl);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void disconnect() {
		try {
			if(rs != null) rs.close();
			if(pstmt!= null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
}
