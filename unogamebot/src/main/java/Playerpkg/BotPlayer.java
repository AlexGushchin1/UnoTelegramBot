package Playerpkg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import enums.CardType;
import enums.CardValue;
import enums.Color;
import enums.PlayerStatus;
import inf.Dealer;
import jersey.repackaged.com.google.common.collect.Lists;
import models.Card;
import models.Game;
import models.Player;

public class BotPlayer extends Player {

	public BotPlayer (){
		this.setStatus(PlayerStatus.READY);
	}
	
	public BotPlayer (long id , String botname){
		this();
		this.setName(botname);
		this.setId(id);
	}
	@Override
	public List<Card> makeMove (Game gm) {

		Dealer 	   dl		 = gm.getDealer();
		Card 	   deckCard  = gm.getDealer().getDeckCard();
		CardType   dcType 	 = deckCard.getType();	
		CardValue  dcValue 	 = deckCard.getValue();
		Color 	   chngColor = gm.getDealer().getChangeColorParam();
		
		//System.out.println("открытая карта : " + dl.getDeckCard().getColor() + " " + dl.getDeckCard().getValue());
		printOpenCard(deckCard);
		
		
		
		
		if (deckCard.getColor().equals(Color.BLACK)) {
			
				if (dcType.equals(CardType.WILD) && dcValue.equals(CardValue.PLUS4)) {
					
					if (dl.getNextMoveflag() == true) { 
						
							System.out.println("Игрок "+ this.getName() + " берет 4 карты и пропускает ход");
							this.addCards(dl.giveCards(4));	
							dl.setNextMoveflag(false);
							
					return Collections.EMPTY_LIST; // игрок берет карты но не делает ход
					
					} else { //игрок ходит после игрока взявшего 4-ре карты
						
						findCardActions(dl, chngColor);
						
					  }

				}
			
				if (dcType.equals(CardType.WILD) && dcValue.equals(CardValue.CHANGECOLOR)) {
					
					findCardActions(dl, chngColor);
					
				}
				
			
			
		this.printCards();
			
			
		}  else { // FOR NOT BLACK CARDS ==================================================================================
				
			
				if (deckCard.getType().equals(CardType.ACTION)) { // skip or reverse card
					
					if (deckCard.getValue().equals(CardValue.PLUS2)) {
						
						if (dl.getcheckPLUS4Param() == true) { 
							
							System.out.println("Игрок "+ this.getName() + " берет 2 карты и пропускает ход");
							this.addCards(dl.giveCards(2));
							dl.changecheckPLUS4Param(false); //cледующий игрок не будет брать карты - он делает обычный ход
							//this.printCards();
							
					return Collections.EMPTY_LIST; // игрок берет карты но не делает ход
					
						} else { //игрок ходит после игрока взявшего 4-ре карты
							
								System.out.println("игрок ищет карту "+ chngColor + " цвета "); 
							
								List<Card> findCard = getCardByColor(chngColor);
							
								makeMoveOrTakeCard(dl, chngColor, findCard);
							
	
							
							}
						
						
					}
					
// 	-------------------------------------------------------------------------------------------------------------------				
					
					if (deckCard.getValue().equals(CardValue.SKIP)) {
						if (dl.getcheckPLUS4Param() == true) { 
							
							System.out.println("Игрок "+ this.getName() + " пропускает ход");

							dl.changecheckPLUS4Param(false); //cледующий игрок не будет пропускать ход 
							
					return Collections.EMPTY_LIST; // игрок берет карты но не делает ход
					
						} else { //игрок ходит после игрока взявшего 4-ре карты
							
								System.out.println("игрок ищет карту "+ deckCard.getColor() + " цвета или "+ deckCard.getValue()); 
							
								List<Card> findCard = getCardByColorAndValue(deckCard.getColor(), deckCard.getValue()); // getCardByColor(chngColor);
							
								makeMoveOrTakeCard(dl, chngColor, findCard);
							
	
							
							}
					}
					
					
//	-------------------------------------------------------------------------------------------------------------------						
					
					if (deckCard.getValue().equals(CardValue.REVERSE)) {
						
						System.out.println("игрок ищет карту "+ deckCard.getColor() + " цвета или "+ deckCard.getValue()); 
						
						List<Card> findCard = getCardByColorAndValue(deckCard.getColor(), deckCard.getValue()); // getCardByColor(chngColor);
					
						makeMoveOrTakeCard(dl, chngColor, findCard);
						
						
					}
					
					
					
				} 
				
		else { // for color numbers cards
					
			System.out.println("игрок ищет карту "+ deckCard.getColor() + " цвета или "+ deckCard.getValue()); 
			
			List<Card> findCard = getCardByColorAndValue(deckCard.getColor(), deckCard.getValue()); // getCardByColor(chngColor);
		
			makeMoveOrTakeCard(dl, chngColor, findCard);		
					
					
		}
			
			
			
			
			
			
//			if (deckCard.getType().equals(CardType.ACTION) && deckCard.getValue().equals(CardValue.PLUS2)) {
//				if (gm.getDealer().getcheckPLUS4Param() == true) {
//					
//					System.out.println("Игрок "+ this.getName() + " берет 2 карты и делает ход");
//					this.addCards(gm.getDealer().giveCards(2));
//					System.out.println("игрок ищет карту "+ deckCard.getColor() + " цвета " + "или "+ deckCard.getValue());
//					List<Card> findCard = getCardByColorAndValue(deckCard.getColor(),deckCard.getValue());
//					if ( findCard.size() > 0 ) {
//						checkBlackAndChangeColor(findCard,gm.getDealer());
//						gm.getDealer().puttoDeck(findCard); 
//					}
//						
//					
//					else {
//						this.addCards(gm.getDealer().giveCards(1));
//						gm.getDealer().puttoDeck(getCardByColor(chngColor));
//					}
//					
//					
//					
//					this.printCards();
//					//gm.getDealer().changecheckPLUS4Param(false);
//					//this.printCards();
//					//return Collections.EMPTY_LIST;// игрок берет карты но не делает ход
//					
//				} else {
//					
//				}
				
			}
			
			
			
			
//				if (deckCard.getType().equals(CardType.ACTION) && 
//						( deckCard.getValue().equals(CardValue.SKIP) || deckCard.getValue().equals(CardValue.REVERSE
//						))) {
//				System.out.println("SKIP or Reverse");
//				
//				if ( deckCard.getValue().equals(CardValue.REVERSE)) {
//					
//					if (gm.getDealer().getcheckPLUS4Param() == true) {
//						gm.getDealer().changecheckPLUS4Param(false);
//					}
//					else {
//						List<Card> findCard = getCardByColorAndValue(deckCard.getColor(),deckCard.getValue());
//						if ( findCard.size() > 0 ) {
//							checkBlackAndChangeColor(findCard,gm.getDealer());
//							gm.getDealer().puttoDeck(findCard); 
//						}
//							
//						
//						else {
//							this.addCards(gm.getDealer().giveCards(1));
//							gm.getDealer().puttoDeck(getCardByColor(chngColor));
//						}
//					}
//					
//					
////					if (gm.getDealer().getcheckPLUS4Param() == true) {
////						gm.getDealer().changecheckPLUS4Param(false);
////					}
//					
//					}
//				
//				if ( deckCard.getValue().equals(CardValue.SKIP)) {
//						if (gm.getDealer().getcheckPLUS4Param() == true) {
//							gm.getDealer().changecheckPLUS4Param(false);
//						}
//						else {
//							List<Card> findCard = getCardByColorAndValue(deckCard.getColor(),deckCard.getValue());
//							if ( findCard.size() > 0 ) {
//								checkBlackAndChangeColor(findCard,gm.getDealer());
//								gm.getDealer().puttoDeck(findCard); 
//							}
//								
//							
//							else {
//								this.addCards(gm.getDealer().giveCards(1));
//								gm.getDealer().puttoDeck(getCardByColor(chngColor));
//							}
//						}
//				
//				
//				}
//				
//				} else { // ОСТАЮТСЯ ЧИСЛА
//					System.out.println("игрок ищет карту "+ deckCard.getColor() + " цвета " + "или " + deckCard.getValue() );
//					List<Card> findCard = getCardByColorAndValue(deckCard.getColor(),deckCard.getValue());
//					if ( findCard.size() > 0 ) {
//						checkBlackAndChangeColor(findCard,gm.getDealer());
//						gm.getDealer().puttoDeck(findCard); 
//					}
//						
//					
//					else {
//						this.addCards(gm.getDealer().giveCards(1));
//						gm.getDealer().puttoDeck(getCardByColor(chngColor));
//					}
//					
//					
//					
//					this.printCards();
//				}
//			
//			}
//			
			
			
			
			
			
			
		
		
		
		
		
		return null;
	}

	/**
	 * @param dl
	 * @param chngColor
	 */
	private void findCardActions(Dealer dl, Color chngColor) {
		printFindColor(chngColor);	
		List<Card> findCard = getCardByColor(chngColor);
		makeMoveOrTakeCard(dl, chngColor, findCard);
	}

	
	/**
	 * @param dl
	 * @param chngColor
	 * @param findCard
	 */
	private void makeMoveOrTakeCard(Dealer dl, Color chngColor, List<Card> findCard) {
		if ( findCard.size() > 0 ) { // нашли карту соответствующего цвета
			printMove(findCard);//System.out.println("Игрок "+ this.getName() + " делает ход : {" + findCard.get(0).getColor() + //" " + findCard.get(0).getValue()+"}" );
			dl.puttoDeck(findCard);////
			checkBlackAndChangeColor(findCard,dl);
				
		}
		 else {
			 System.out.println("Игрок "+ this.getName() + " берет 1 карту");
			 this.addCards(dl.giveCards(1));
			 findCard = getCardByColor(chngColor);
				if ( findCard.size() > 0 ) {
					
					dl.puttoDeck(findCard);
					checkBlackAndChangeColor(findCard, dl);	//gm.getDealer().puttoDeck(getCardByColor(chngColor));
				} else { System.out.println("Игроку нечем ходить - {ПРОПУСК ХОДА} ");}
				
		
		 }
	}
	
	
	private void checkBlackAndChangeColor (List<Card> cards , Dealer dl ) {
		Card card = cards.get(cards.size()-1);//?
		checkActionCards(cards,dl);
		if (card.getType().equals(CardType.WILD) && card.getColor().equals(Color.BLACK)) {
			dl.changecheckPLUS4Param(true);
			Color color = null ;
			while ( color == null) {
				
				color =  Color.values()[new Random().nextInt(Color.values().length)];
				if (color.equals(Color.BLACK))color = null; 
			}
			dl.setChangeColorParam(color);
			System.out.println("Новый цвет :"+ color);
		}
		
	}
	
	private void checkActionCards (List<Card> cards , Dealer dl ) {
		Card card = cards.get(cards.size()-1);//?
		
		if (card.getType().equals(CardType.ACTION) &&  ( card.getValue().equals(CardValue.PLUS2)    ||  
														 card.getValue().equals(CardValue.REVERSE)  ||
														 card.getValue().equals(CardValue.SKIP) )) {
		dl.changecheckPLUS4Param(true);
//				if (card.getValue().equals(CardValue.PLUS2)) {
//					Color color = null ;
//					while ( color == null) {
//						
//						color =  Color.values()[new Random().nextInt(Color.values().length)];
//						if (color.equals(Color.BLACK)) color = null; 
//					}
//					dl.setChangeColorParam(color);
//					System.out.println("Новый цвет :"+ color);
//					
//					//dl.setChangeColorParam(Color.YELLOW);
//					//System.out.println("Новый цвет :"+ Color.YELLOW);
//				}
			
		}
		
	}
	
	
	
	
	private List<Card> getCardByColorAndValue(Color findColor , CardValue cardValue) {
		List<Card> cardsByColor = getCardByColor(findColor);
		List<Card> cardsByCardValue = new ArrayList<Card>();
		if (cardsByColor.size() < 1 ){
			Optional<Card> result = this.getCards()
					.stream()
					.filter(e->e.getValue().equals(cardValue))
					.findFirst();
			if (result.isPresent()) {
				cardsByCardValue.add(result.get());
				this.getCards().remove(result.get());
			} 
			
		} else {
			return cardsByColor;
		}
		return Lists.newArrayList();
		
	}
	
	public List<Card> getCardByColor(Color findColor) {
		
		List<Card> findResult = new ArrayList<Card>();
		
		Optional<Card> result = this.getCards()
				.stream()
				.filter(e->e.getColor().equals(findColor))
				.findFirst();
		
		if (result.isPresent()) {
			findResult.add(result.get());
			this.getCards().remove(result.get());
		} else {
			List<Card> findblack = new ArrayList<Card>();
			Optional<Card> resultblack = this.getCards()
					.stream()
					.filter(e->e.getColor().equals(Color.BLACK))
					.findFirst();
			if (resultblack.isPresent()) {
								
				findResult.add(resultblack.get());
				
				
				
				this.getCards().remove(resultblack.get());
			} 
		}
		
		return findResult;
		
	}
	
	
	
	private void printMove(List<Card> card ) {
		System.out.println("Игрок "+ this.getName() + 
				" делает ход : {" + card.get(0).getColor() + " " + card.get(0).getValue() + "}" );
	}
		
	private void printOpenCard(Card openCard ) { 
		System.out.println("открытая карта : " + openCard.getColor() + " " + openCard.getValue());
	}
	
	private void printFindColor(Color findcolor ) { 
		System.out.println("Игрок "+ this.getName() + " должен найти карту " + findcolor + " цвета" );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
