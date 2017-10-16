package Dealerpkg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import enums.Color;
import inf.Dealer;
import models.Card;
import models.CardBox;

public class ClassicDealer implements Dealer {

	
	private List<Card> cards ;
	private List<Card> deck;
	
	private Color ChangeColor;// = Color.GREEN ; //вернуть на пустое значение 
	private boolean checkPLUS4 = false ;//= true; // вернуть на false
	
	private boolean nextMoveFl = false ;
	
	
	
	
	public boolean isNextMoveFl() {
		return nextMoveFl;
	}

	public void setNextMoveFl(boolean nextMoveFl) {
		this.nextMoveFl = nextMoveFl;
	}

	public void changecheckPLUS4Param(boolean bValue){
		checkPLUS4 = bValue;
	}
	
	public void puttoDeck (List<Card> cards){
		deck.addAll(cards);
		//System.out.println("$$$$$$$$$$$$$$$$$");
		//deck.forEach(item->System.out.println(item.getColor()+" "+ item.getValue() ));
		//System.out.println("deck size = "+deck.size());
		//System.out.println("$$$$$$$$$$$$$$$$$");
	}
	
	public boolean isCheckPLUS4() {
		return checkPLUS4;
	}

	public void setCheckPLUS4(boolean checkPLUS4) {
		this.checkPLUS4 = checkPLUS4;
	}

	public Color getChangeColor() {
		return ChangeColor;
	}

	public void setChangeColor(Color changeColor) {
		ChangeColor = changeColor;
	}

	public Card getDeckCard() {
		
		return  deck.get(deck.size() - 1);	
	}
	
	public ClassicDealer() throws CloneNotSupportedException{
		cards = new ArrayList<Card>();
		deck = new ArrayList<Card>();
		CardBox cardBox = new CardBox();	
		cards = cardBox.getCardsFromCardBox();
		
		if (deck.size()==0) {
			//System.out.println("add to deck");
			deck.addAll(giveCards(1));
			ChangeColor = deck.get(deck.size()-1).getColor();
			checkPLUS4 = false;
			
			System.out.println("$$$$$$$$$$$$$$$$$");
			deck.forEach(item->System.out.println(item.getColor()+" "+ item.getValue() ));
			System.out.println("deck size = "+deck.size());
			System.out.println("$$$$$$$$$$$$$$$$$");
		}
		

	}
	
	
//	@Override
//	public List<Card> give7cards() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	

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


	@Override
	public List<Card> giveCards(int c) {
		// TODO Auto-generated method stub
		if (cards.size() < 5){
			//List<Card> tmp = deck.
			cards.addAll(deck);
			deck.removeAll(deck.subList(0, deck.size()-2));
		}
		List<Card> sublist = cards.subList(0, c);
		cards = cards.subList(c,cards.size());
		System.out.println("Диллер раздал "+sublist.size() +"карт(ы)");
		System.out.println("В колоде у диллера осталось  "+cards.size() +"карт(ы)");
		

		
		
		//deck.
		
		return sublist;
	}

	@Override
	public Color getChangeColorParam() {
		// TODO Auto-generated method stub
		return getChangeColor();
	}

	@Override
	public boolean getcheckPLUS4Param() {
		// TODO Auto-generated method stub
		return isCheckPLUS4();
	}

	@Override
	public void setChangeColorParam(Color color) {
		this.setChangeColor(color);
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getNextMoveflag() {
		// TODO Auto-generated method stub
		return isNextMoveFl();
	}

	@Override
	public void setNextMoveflag(boolean bValue) {
		setNextMoveFl(bValue);
		// TODO Auto-generated method stub
		
	}
	
	
	
}
