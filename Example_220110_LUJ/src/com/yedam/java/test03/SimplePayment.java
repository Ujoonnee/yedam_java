package com.yedam.java.test03;

public class SimplePayment implements Payment {

	private double simplePaymentRatio;
	
	
	SimplePayment(double simplePaymentRatio) {
		this.simplePaymentRatio = simplePaymentRatio;
	}
	
	
	@Override
	public int online(int price) {
		int result = (int) (price * (1 - ONLINE_PAYMENT_RATIO - this.simplePaymentRatio));
		return result;
	}

	@Override
	public int offline(int price) {
		int result = (int) (price * (1- OFFLINE_PAYMENT_RATIO - this.simplePaymentRatio));
		return result;
	}

	@Override
	public void showInfo() {
		double onlineRatio = ONLINE_PAYMENT_RATIO + this.simplePaymentRatio;
		double offlineRatio = OFFLINE_PAYMENT_RATIO + this.simplePaymentRatio;
		System.out.println("*** 카드로 결제 시 할인정보\n온라인 결제시 총 할인율 : " + onlineRatio
				+ "\n오프라인 결제 시 총 할인율 : " + offlineRatio);
	}


}
