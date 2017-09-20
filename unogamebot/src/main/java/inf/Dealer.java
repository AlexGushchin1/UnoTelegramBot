package inf;

import java.util.List;

import models.Card;

public interface Dealer {

	
	public List<Card> giveCards(int c); // c - count of cards

	public void shuffle();
	
	public void printCards();
}
