package Assignment.Jan05;

public class Assignment220105 {
	
	public static void main(String[] args) {
		
		System.out.println("======= 문제1번");
		//문제1) 아침 출근길에 김 씨는 4,000원을 내고 별다방에서 아메리카노를 마셨습니다.
		//		이 씨는 콩다방에서 4,500원을 내고 라떼를 사 마셨습니다.
		//		이 과정을 구현해보세요.
		Person kim = new Person("Kim", 8000);
		Person lee = new Person("Lee", 9800);
		
		StarCafe starCafe = new StarCafe("별다방 1호점");
		kim.buyCoffee(starCafe);
		starCafe.showInfo();
		
		System.out.println();
		BeanCafe beanCafe = new BeanCafe("콩다방 동성로점");
		lee.buyCoffee(beanCafe);
		beanCafe.showInfo();
		
		System.out.println();
		kim.showInfo();
		lee.showInfo();
		
		System.out.println("======= 문제2번");		
		//문제2) 카드 회사에서 카드를 발급힐 떼미디 카드 고유 번호를 부여해줍니다.
		//		카드가 생성될 때마다 카드 번호가 자동으로 증가할 수 있도록 카드 클래스를 만들고 생성해보세요.
		Card kbCard = new Card();
		Card kdbCard = new Card();
		System.out.println("kbCard : " + kbCard.getCardNo());
		System.out.println("kdbCard : " + kdbCard.getCardNo());
		
		System.out.println("======= 문제3번");
		//문제3) 문제2에서 구현한 내용에 카드 회사 클래스 CardCompany를 싱글톤 패턴을 사용하여 구현해보세요.
		CardCompany company = CardCompany.getInstance();
		Card firstCard = company.createCard();
		Card secondCard = company.createCard();
		System.out.println("첫번째 카드 : " + firstCard.getCardNo());
		System.out.println("두번째 카드 : " + secondCard.getCardNo());

	}
}
