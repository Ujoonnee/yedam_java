package com.yedam.java.test02;

public class Test {

	public static void main(String[] args) {
		
		TossCard toss = new TossCard("1234-1234-1234-1234", 20220110, 123, "신빛용");
		toss.showCardInfo();
		
		DGBCard dgb = new DGBCard("1234-1234-1234-1234", 20220110, 123, "신빛용");
		dgb.showCardInfo();
	}
	
}
