//package project_Yedam.dao;
//
//import java.sql.SQLException;
//
//import com.yedam.java.account.Account;
//
//public class ProjectDAOImpl extends DAO implements ProjectDAO {
//
//	private static ProjectDAO instance = new ProjectDAOImpl();
//	private ProjectDAOImpl() {}
//	public static ProjectDAO getInstance() {
//		return instance;
//	}
//	
//	@Override
//	public void selectAll(String table) {
//		try {
//			connect();
//			String select = "SELECT * FROM ?";
//			pstmt = con.prepareStatement(select);
//			pstmt.setString(1, table);
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				account = new Account();
//				account.setAccountNo(accountNo);
//				account.setAccountOwner(rs.getString(2));
//				account.setAccountPassword(rs.getString(3));
//				account.setAccountBalance(rs.getInt(4));
//			} 
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			disconnect();
//		}
//	}
//	@Override
//	public void insert() {
//		try {
//			connect();
//			String insert = "INSERT INTO books VALUES (?,?,?,?)";
//			pstmt = con.prepareStatement(insert);
//			pstmt.setString(1, book.getName());
//			pstmt.setString(2, book.getWriter());
//			pstmt.setString(3, book.getContent());
//			pstmt.setInt(4, book.isOut());
//			
//			int result = pstmt.executeUpdate();
//			
//			System.out.println(result + "권이 등록되었습니다.");
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			disconnect();
//		}
//	}
//	@Override
//	public void update() {
//		try {
//			connect();
//			String update = "UPDATE books SET book_rental = ? WHERE book_name = ?";
//			pstmt = con.prepareStatement(update);
//			pstmt.setInt(1, book.isOut());
//			pstmt.setString(2, book.getName());
//			
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			disconnect();
//		}
//	}
//	@Override
//	public void delete() {
//		try {
//			connect();
//			String delete = "DELETE FROM emp13 WHERE employee_id = ?";
//			pstmt = con.prepareStatement(delete);
//			pstmt.setInt(1, departmentId);
//			
//			int result = pstmt.executeUpdate();
//			
//			System.out.println(result + "건 삭제");
//		} catch (SQLException e) {
//			e.printStackTrace();
//	}
//
//	
//}
