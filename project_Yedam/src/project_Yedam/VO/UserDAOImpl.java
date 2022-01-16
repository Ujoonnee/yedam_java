package project_Yedam.VO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends DAO implements ProjectDAO<User, String> {

	private static ProjectDAO<User, String> instance = new UserDAOImpl();

	private UserDAOImpl() {}

	public static ProjectDAO<User, String> getInstance() {
		return instance;
	}

	@Override
	public List<User> selectAll() {
		List<User> list = new ArrayList<>();
		try {
			connect();
			String select = "SELECT * FROM users ORDER BY user_num";
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUserNum(rs.getInt(1));
				user.setId(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
				user.setPhoneNum(rs.getString(4));
				user.setAuthority(rs.getString(5));

				list.add(user);
			}

		} catch (Exception e) {
			System.out.println("전체 유저 목록 호출 실패");
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;
	}

	@Override
	public User selectOne(String userId) {
		User user = null;

		try {
			connect();
			String select = "SELECT * FROM users WHERE id = ?";
			pstmt.setString(1, userId);
			pstmt = con.prepareStatement(select);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserNum(rs.getInt(1));
				user.setId(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
				user.setPhoneNum(rs.getString(4));
				user.setAuthority(rs.getString(5));
			}

		} catch (Exception e) {
			System.err.println("User selectOne()");
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return user;
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
			pstmt.setString(6, user.getAuthority());

			pstmt.executeUpdate();

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
			String update = "UPDATE users SET password = ? WHERE id = ?";
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
			String delete = "DELETE FROM users WHERE id = ?";
			pstmt = con.prepareStatement(delete);
			pstmt.setString(1, user.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

}
