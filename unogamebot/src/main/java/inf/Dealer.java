package inf;

import java.util.List;

import enums.Color;
import models.Card;

public interface Dealer {

	
	public List<Card> giveCards(int c); // c - count of cards

	public void shuffle();
	
	public void printCards();
	
	public Card getDeckCard();
	
	public Color getChangeColorParam();
	
	public boolean getcheckPLUS4Param();
	
	public void puttoDeck (List<Card> cards);
	
	public void changecheckPLUS4Param(boolean bValue);
	
	public void setChangeColorParam(Color color);
}
