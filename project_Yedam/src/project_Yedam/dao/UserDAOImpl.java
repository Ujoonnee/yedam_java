package project_Yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project_Yedam.VO.User;

public class UserDAOImpl extends DAO implements ProjectDAO<User> {

	private static ProjectDAO<User> instance = new UserDAOImpl();

	private UserDAOImpl() {
	}

	public static ProjectDAO<User> getInstance() {
		return instance;
	}

	// show all users
	@Override
	public List<User> selectAll() {
		List<User> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM users";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setUserNum(rs.getInt(1));
				user.setId(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
				user.setPhoneNum(rs.getString(4));
//				user.setAuthority();		todo : treat after sqlite enum 
				
				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// register user
	@Override
	public void insert(User user) {
		try {
			connect();
			String insert = "INSERT INTO users VALUES (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setInt(1, user.getUserNum());
			pstmt.setString(2, user.getId());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getName());
			pstmt.setString(5, user.getPhone());
//			pstmt.setString(6, user.getAuthority());	todo : treat after sqlite enum 

			int result = pstmt.executeUpdate();

			System.out.println(result + "명 등록");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// change password
	@Override
	public void update(User user) {
		try {
			connect();
			String update = "UPDATE users SET user_password = ? WHERE user_id= ?";
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// delete user
	@Override
	public void delete(User user) {
		try {
			connect();
			String delete = "DELETE FROM users WHERE user_id = ?";
			pstmt = con.prepareStatement(delete);
			pstmt.setString(1, user.getId());

			int result = pstmt.executeUpdate();

			System.out.println(result + "명 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
