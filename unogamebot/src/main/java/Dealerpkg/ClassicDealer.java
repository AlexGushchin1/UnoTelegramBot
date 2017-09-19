package Dealerpkg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import inf.Dealer;
import models.Card;
import models.CardBox;

public class ClassicDealer implements Dealer {

	
	private List<Card> cards ;
	
	public ClassicDealer() throws CloneNotSupportedException{
		cards = new ArrayList<Card>();
		CardBox cardBox = new CardBox();	
		cards = cardBox.getCardsFromCardBox();

	}
	
	
	@Override
	public List<Card> give7cards() {
		// TODO Auto-generated method stub
		return null;
	}
	

	public void  shuffle() {
		for ( int i=0 ; i <10;i++) {
			Collections.shuffle(cards);
		}
		

	}


	@Override
	public void printCards() {
		
		for (Card card : cards ) {
			System.out.println("color : "+ card.getColor()+ "  value : " + card.getValue());
		}
		
	}
	
}
