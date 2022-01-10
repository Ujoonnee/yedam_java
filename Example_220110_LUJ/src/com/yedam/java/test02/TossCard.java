package com.yedam.java.test02;

public class TossCard extends Card{
	
	private String company = "Toss";
	private String cardStaff;
	
	TossCard(String cardNo, int validDate, int cvc, String cardStaff) {
		super(cardNo, validDate, cvc);
		this.cardStaff = cardStaff;
	}

	
	@Override
	public void showCardInfo() {
		System.out.printf("카드정보 - Card No, %s\n담당직원 - %s, %s\n", getCardNo(), cardStaff, company);
	}
}
