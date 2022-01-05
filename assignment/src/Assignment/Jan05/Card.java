package Assignment.Jan05;

public class Card {

	private static int serialNo = 0;
	private int cardNo;
	
	Card () {
		serialNo++;
		cardNo = serialNo;
	}
	
	public int getCardNo() {
		return cardNo;
	}
}
