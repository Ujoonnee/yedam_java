package com.yedam.java.account;

import java.sql.SQLException;

import com.yedam.java.common.DAO;

public class AccountDAOImpl extends DAO implements AccountDAO {

	private static AccountDAO instance = new AccountDAOImpl();
	private AccountDAOImpl() {}
	public static AccountDAO getInstance() {
		return instance;
	}
	
	@Override
	public Account selectAccountInfo(long accountNo) {
		Account account = null;
		try {
			connect();
			String select = "SELECT * FROM accounts WHERE account_no = ?";
			pstmt = con.prepareStatement(select);
			pstmt.setLong(1, accountNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				account = new Account();
				account.setAccountNo(accountNo);
				account.setAccountOwner(rs.getString(2));
				account.setAccountPassword(rs.getString(3));
				account.setAccountBalance(rs.getInt(4));
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return account;
	}

	@Override
	public long selectAccountNo(Account account) {
		long accountNo = 0;
		try {
			connect();
			String select = "SELECT account_no FROM accounts WHERE account_owner = ? AND account_password = ? ORDER BY account_no DESC";
			pstmt = con.prepareStatement(select);
			pstmt.setString(1,account.getAccountOwner());
			pstmt.setString(2,account.getAccountPassword());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				accountNo = rs.getLong("account_no");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return accountNo;
	}

	@Override
	public void createAccount(Account account) {
		try {
			connect();
			String insert = "INSERT INTO accounts (account_owner, account_password, account_balance) VALUES (?,?,?)";
			pstmt = con.prepareStatement(insert);
			pstmt.setString(1, account.getAccountOwner());
			pstmt.setString(2, account.getAccountPassword());
			pstmt.setLong(3, account.getAccountBalance());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("계좌가 정상적으로 등록되었습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void updateAccount(Account account) {
		try {
			connect();
			String update = "UPDATE accounts SET account_balance = account_balance + ? WHERE account_no = ?";
			pstmt = con.prepareStatement(update);
			pstmt.setLong(1, account.getAccountBalance());
			pstmt.setLong(2, account.getAccountNo());;
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("정상적으로 처리되었습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

}
