package Assignment.Jan05;

public class StarCafe {
	
	private String name;
	private int income = 0;
	private int passengerCount = 0;
	
	StarCafe(String name) {
		this.name = name;
	}
	
	public void showInfo() {
		System.out.printf("%s의 고객 수는 %d명이고, 수입은 %d원입니다.", name, passengerCount, income);
	}
	
	public void buy(int income) {
		this.income += income;
		this.passengerCount++;
	}
}
