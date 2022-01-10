package com.yedam.java.test02;

public class DGBCard extends Card{

	private String company = "대구은행";
	private String cardStaff;
	
	DGBCard(String cardNo, int validDate, int cvc, String cardStaff) {
		super(cardNo, validDate, cvc);
		this.cardStaff = cardStaff;
	}

	@Override
	public void showCardInfo() {
		System.out.printf("카드정보 ( Card No :  %s, 유효기간 : %d, CVC : %d )\n담당직원 - %s, %s\n", getCardNo(), getValidDate(), getCvc(), cardStaff, company);
	}
}