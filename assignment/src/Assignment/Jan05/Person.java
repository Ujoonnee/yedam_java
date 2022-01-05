package Assignment.Jan05;

public class Person {
	
	private String name;
	private int money;
	
	Person(String name, int money) {
		this.name = name;
		this.money = money;
	}
	
	void buyCoffee(StarCafe starCafe) {
		this.money -= 4000;
		starCafe.buy(4000);
	}
	
	void buyCoffee(BeanCafe beanCafe) {
		this.money -= 4500;
		beanCafe.buy(4500);
	}
	
	void showInfo() {
		System.out.printf("%s님의 남은 돈은 %d원입니다.\n", name, this.money);
	}
	
	
}
