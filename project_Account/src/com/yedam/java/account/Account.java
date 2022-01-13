package com.yedam.java.account;

public class Account {

	private long accountNo;
	private String accountOwner;
	private String accountPassword;
	private long accountBalance;
	
	public Account() {

	}
	
	
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	public long getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(long amount) {
		this.accountBalance = amount;
	}


	@Override
	public String toString() {
		return "계좌번호 " + getAccountNo() + "의 잔액은 " + getAccountBalance() + "입니다.";
	}
	
}
