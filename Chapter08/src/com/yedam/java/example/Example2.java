package com.yedam.java.example;

public class Example2 {
	
	public static void main(String[] args) {
//		문제2) 다음을 만족하는 클래스 SalaryMan을 작성하시오.
//		
//		필드 salary는 월 급여액을 저장하며, int형으로 초기값 1,000,000 저장
//		
//		메서드 getAnnualGross()는 연봉을 계산해 반환하는 메서드로
//		연봉은 12개월치 월 급여액에 한 달치 월 급여액 * 500%를 더한 값으로 한다.
//		
//		기본 생성자에서 필드 salary의 초기 값을 사용하며,
//		정수형 매개변수를 가지는 생성자에서 해당 매개변수를 월 급여액으로 저장
		
		System.out.println(new SalaryMan().getAnnualGross());
		System.out.println(new SalaryMan(2000000).getAnnualGross());
		
//		문제3) 다음을 만족하는 클래스 StudentScores를 작성하시오.
//		
//		필드로 학생이름, 학급, 국어점수, 영어점수, 수학점수를 가진다.
//
//		기본 생성자는 없다.
//		
//		생성자를 통해 학생이름과 학급을 저장한다.
//		
//		메서드는 다음과 같이 구성된다.
//		1. 각 필드별 getter와 setter
//		2. 메서드 getTotal()은 모든 과목의 점수를 더한 총합을 반환한다.
//		3. 메서드 getAverage()는 모든 과목의 점수의 평균을 반환한다.
		
		StudentScores studentHong = new StudentScores("홍길동", 1);
		studentHong.setKorean(100);
		studentHong.setEnglish(60);
		studentHong.setMath(76);
		
		System.out.println("학생 " + studentHong.getName() + "의 총 점수는 " + studentHong.getTotal() +"점이고, 평균은 " + studentHong.getAverage() + "입니다.");
		
		
//		문제4) 다음을 만족하는 클래스 Account를 작성하시오.
//
//		필드로 예금주, 잔액을 가진다.
//		
//		생성자를 통해 예금주를 저장한다.
//		
//		메서드는 다음과 같이 구성된다.
//		1. 각 필드의 getter를 가진다.
//		2. 메서드 deposit은 외부에서 매개변수로 받은 값을 저축한다.
//		3. 메서드 withdrqw는 외부에서 매개변수로 받은 값을 인출한다.

		Account account = new Account("신용권");
		account.deposit(10000);
		account.withdraw(4800);
		System.out.println(account.getName() +"님의 계좌 잔액은 " + account.getBalance() + "원입니다.");
		
		
		/*
		 문제1) 원을 표현하는 다음 Circle 클래스가 있다. 
		 	   Circle 클래스를 상속받은 NamedCircle 클래스를 작성하여, 다음 main()을 실행할 때 다음 실행 결과와 같이 출력되도록 하라.
		 	   - class Circle {
					private int radius;
					public Circle(int radius) {
						this.radius = radius;
					}
					public int getRadius() {
					return radius;
					}
				}
				
				- 실행코드
				public static void main(String[] args) {
					NamedCircle w = new NamedCircle(5, "Waffle");
					w.show();
				}
				
				- 실행결과
				Waffle, 반지름 = 5
		*/
		
		NamedCircle w = new NamedCircle(5, "Waffle");
		w.show();
		
		
		
		/*
		
		  문제2) 다음 코드와 실행 결과를 참고하여 추상 클래스 Calculator를 상속받는 Adder와 Subtracter 클래스를 작성하라.
		  		- abstract class Calculator {
					protected int a, b;
					abstract protected int calc();
					protected void input() {
						Scanner scanner = new Scanner(System.in);
						System.out.print("정수 2개를 입력하세요 >> ");
						a = scanner.nextInt();
						b = scanner.nextInt();
					}
					public void run() {
						input();
						int res = calc();
						System.out.println("계산된 값은 " + res);
					}
				}
				
				- 실행결과
				정수 2개를 입력하세요 >> 5 3
				계산된 값은 8
				정수 2개를 입력하세요 >> 3 5
				계산된 값은 -2
	*/
		
		Adder a = new Adder();
		a.run();
		
		Substractor s = new Substractor();
		s.run();
		
		
		
		
		/*
		문제3) 다음은 단위를 변환하는 추상 클래스 Converter이다.
			  Converter 클래스를 상속받아 원화를 달러로 변환하는 Won2Dollar 클래스를 작성하라. 
			  main() 메소드와 실행 결과는 다음과 같다.
			  - abstract class Converter {
   					abstract protected double convert(double src); // 추상 메소드
   					abstract protected String getSrcString(); // 추상 메소드 
   					abstract protected String getDestString(); // 추상 메소드
   					protected double ratio; // 비율
   					public void run() {
      					Scanner scanner = new Scanner(System.in);
      					System.out.println(getSrcString()+"을 "+getDestString()+"로 바꿉니다.");
      					System.out.print(getSrcString()+"을 입력하세요>> ");
      					double val = scanner.nextDouble();
      					double res = convert(val);
      					System.out.println("변환 결과: "+res+getDestString()+"입니다");
      					scanner.close();
  					 }
					}
				
				- 실행코드
				public static void main(String args[]) {
   					Won2Dollar toDollar = new Won2Dollar(1200); // 1달러는 1200원
   					toDollar.run();
				}
				
				- 실행결과
				원을 달러로 바꿉니다.
				원을 입력하세요>> 24000
				변환 결과: 20.0달러입니다
*/
		
		Won2Dollar toDollar = new Won2Dollar(1200); // 1달러는 1200원
			toDollar.run();
	
		
		
		
	}
}
