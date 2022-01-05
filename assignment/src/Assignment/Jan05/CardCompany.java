package Assignment.Jan05;

public class CardCompany {
		
	private static CardCompany cardCompany = new CardCompany();
	
	private CardCompany() {}
	
	public static CardCompany getInstance() {
		return cardCompany;
	}
	
	public Card createCard() {
		Card card = new Card();
		return card;
	}
}
