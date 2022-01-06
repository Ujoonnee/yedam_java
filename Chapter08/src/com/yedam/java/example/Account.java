package com.yedam.java.example;

public class Account {
	
	private String name;
	private long balance = 0;
	
	Account(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public long getBalance() {
		return balance;
	}
	
	public void deposit(long money) {
		this.balance += money;
	}
	
	public long withdraw (long money) {
		if (this.balance >= money) {
			this.balance -= money;
			return money;
		} else {
			System.out.println("Not enough money");
			return 0;
		}
	}
	
	
	
}
