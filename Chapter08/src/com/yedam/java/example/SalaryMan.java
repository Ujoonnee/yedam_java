package com.yedam.java.example;

import java.text.DecimalFormat;

public class SalaryMan {

	private int salary;
	
	SalaryMan() {
		this.salary = 1000000;
	}

	SalaryMan(int salary) {
		this.salary = salary;
	}
	
	public String getAnnualGross() {
		DecimalFormat df = new DecimalFormat("###,###");
		String annualGross = df.format(salary * 12 + salary * 5) + "원";
		return annualGross;
	}
	
	
}
