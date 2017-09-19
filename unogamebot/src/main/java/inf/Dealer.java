package inf;

import java.util.List;

import models.Card;

public interface Dealer {

	
	public List<Card> give7cards();

	public void shuffle();
	
	public void printCards();
}
