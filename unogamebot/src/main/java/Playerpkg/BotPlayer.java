package Playerpkg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
		this();//.setStatus(PlayerStatus.READY);
		this.setName(botname);
		this.setId(id);
	}
	@Override
	public List<Card> makeMove (Game gm) {

		Dealer dl= gm.getDealer();
		Card deckCard = gm.getDealer().getDeckCard(); 
		System.out.println("открытая карта : " + dl.getDeckCard().getColor() + " " + dl.getDeckCard().getValue());
		Color chngColor = gm.getDealer().getChangeColorParam();
		
		
		if (deckCard.getColor().equals(Color.BLACK)) {
			
				if (deckCard.getType().equals(CardType.WILD) && deckCard.getValue().equals(CardValue.PLUS4)) {
					
					if (gm.getDealer().getcheckPLUS4Param() == true) { 
						
							System.out.println("Игрок "+ this.getName() + " берет 4 карты и пропускает ход");
							this.addCards(gm.getDealer().giveCards(4));
							gm.getDealer().changecheckPLUS4Param(false); //cледующий игрок не будет брать карты - он делает обычный ход
							this.printCards();
							
					return Collections.EMPTY_LIST; // игрок берет карты но не делает ход
					
					} else { //игрок ходит после игрока взявшего 4-ре карты
						
						System.out.println("игрок ищет карту "+ chngColor + " цвета "); 
						
						List<Card> findCard = getCardByColor(chngColor);
						
							if ( findCard.size() > 0 ) { // нашли карту соответствующего цвета
								System.out.println("Игрок "+ this.getName() + " делает ход : {" + findCard.get(0).getColor() +
										" " + findCard.get(0).getValue()+"}" );
								gm.getDealer().puttoDeck(findCard);////
								checkBlackAndChangeColor(findCard,gm.getDealer());
									
							}
							 else {
								 System.out.println("Игрок "+ this.getName() + " берет 1 карту");
								 this.addCards(gm.getDealer().giveCards(1));
								 findCard = getCardByColor(chngColor);
									if ( findCard.size() > 0 ) {
										
										gm.getDealer().puttoDeck(findCard);
										checkBlackAndChangeColor(findCard,gm.getDealer());	//gm.getDealer().puttoDeck(getCardByColor(chngColor));
									} else { System.out.println("Игроку нечем ходить - {ПРОПУСК ХОДА}");}
									
							
							 }
						
						
						this.printCards();
						
						
							}
	
				}
			
				if (deckCard.getType().equals(CardType.WILD) && deckCard.getValue().equals(CardValue.CHANGECOLOR)) {
					
						if (gm.getDealer().getcheckPLUS4Param() == true) {
							System.out.println("Игрок "+ this.getName() + " должен найти карту " + chngColor + " цвета" );
							
							List<Card> findCard = getCardByColor(chngColor);
							
							if ( findCard.size() > 0 ) gm.getDealer().puttoDeck(findCard); else {
								this.addCards(gm.getDealer().giveCards(1));
								gm.getDealer().puttoDeck(getCardByColor(chngColor));
							}
							
							
							this.printCards();
							
							
						} else {
							System.out.println("игрок ищет карту "+ chngColor + " цвета ");
							List<Card> findCard = getCardByColor(chngColor);
							
							if ( findCard.size() > 0 ) {
								checkBlackAndChangeColor(findCard,gm.getDealer());
								gm.getDealer().puttoDeck(findCard);
								//
								
							}
								 else {
								this.addCards(gm.getDealer().giveCards(1));
								findCard = getCardByColor(chngColor);
									if ( findCard.size() > 0 ) {
										checkBlackAndChangeColor(findCard,gm.getDealer());
										gm.getDealer().puttoDeck(findCard);
									}
								//gm.getDealer().puttoDeck(getCardByColor(chngColor));
							}
							
							
							this.printCards();
						}
					
				}
				
			
			
			
			
		}  else { // FOR NOT BLACK CARDS ==================================================================================
				
			
			if (deckCard.getType().equals(CardType.ACTION) && deckCard.getValue().equals(CardValue.PLUS2)) {
				if (gm.getDealer().getcheckPLUS4Param() == true) {
					
					System.out.println("Игрок "+ this.getName() + " берет 2 карты и делает ход");
					this.addCards(gm.getDealer().giveCards(2));
					System.out.println("игрок ищет карту "+ deckCard.getColor() + " цвета " + "или "+ deckCard.getValue());
					List<Card> findCard = getCardByColorAndValue(deckCard.getColor(),deckCard.getValue());
					if ( findCard.size() > 0 ) {
						checkBlackAndChangeColor(findCard,gm.getDealer());
						gm.getDealer().puttoDeck(findCard); 
					}
						
					
					else {
						this.addCards(gm.getDealer().giveCards(1));
						gm.getDealer().puttoDeck(getCardByColor(chngColor));
					}
					
					
					
					this.printCards();
					//gm.getDealer().changecheckPLUS4Param(false);
					//this.printCards();
					//return Collections.EMPTY_LIST;// игрок берет карты но не делает ход
					
				} else {
					
				}
				
			}
			
			
			
			
				if (deckCard.getType().equals(CardType.ACTION) && 
						( deckCard.getValue().equals(CardValue.SKIP) || deckCard.getValue().equals(CardValue.REVERSE
						))) {
				System.out.println("SKIP or Reverse");
				
				if ( deckCard.getValue().equals(CardValue.REVERSE)) {
					
					if (gm.getDealer().getcheckPLUS4Param() == true) {
						gm.getDealer().changecheckPLUS4Param(false);
					}
					else {
						List<Card> findCard = getCardByColorAndValue(deckCard.getColor(),deckCard.getValue());
						if ( findCard.size() > 0 ) {
							checkBlackAndChangeColor(findCard,gm.getDealer());
							gm.getDealer().puttoDeck(findCard); 
						}
							
						
						else {
							this.addCards(gm.getDealer().giveCards(1));
							gm.getDealer().puttoDeck(getCardByColor(chngColor));
						}
					}
					
					
//					if (gm.getDealer().getcheckPLUS4Param() == true) {
//						gm.getDealer().changecheckPLUS4Param(false);
//					}
					
					}
				
				if ( deckCard.getValue().equals(CardValue.SKIP)) {
						if (gm.getDealer().getcheckPLUS4Param() == true) {
							gm.getDealer().changecheckPLUS4Param(false);
						}
						else {
							List<Card> findCard = getCardByColorAndValue(deckCard.getColor(),deckCard.getValue());
							if ( findCard.size() > 0 ) {
								checkBlackAndChangeColor(findCard,gm.getDealer());
								gm.getDealer().puttoDeck(findCard); 
							}
								
							
							else {
								this.addCards(gm.getDealer().giveCards(1));
								gm.getDealer().puttoDeck(getCardByColor(chngColor));
							}
						}
				
				
				}
				
				} else { // ОСТАЮТСЯ ЧИСЛА
					System.out.println("игрок ищет карту "+ deckCard.getColor() + " цвета " + "или " + deckCard.getValue() );
					List<Card> findCard = getCardByColorAndValue(deckCard.getColor(),deckCard.getValue());
					if ( findCard.size() > 0 ) {
						checkBlackAndChangeColor(findCard,gm.getDealer());
						gm.getDealer().puttoDeck(findCard); 
					}
						
					
					else {
						this.addCards(gm.getDealer().giveCards(1));
						gm.getDealer().puttoDeck(getCardByColor(chngColor));
					}
					
					
					
					this.printCards();
				}
			
			}
			
			
			
			
			
			
			
		
		
		
		
		
		return null;
	}
	
	
	private void checkBlackAndChangeColor (List<Card> cards , Dealer dl ) {
		Card card = cards.get(cards.size()-1);//?
		checkActionCards(cards,dl);
		if (card.getType().equals(CardType.WILD) && card.getColor().equals(Color.BLACK)) {
			dl.changecheckPLUS4Param(true);
			dl.setChangeColorParam(Color.YELLOW);
			System.out.println("Новый цвет :"+ Color.YELLOW);
		}
		
	}
	
	private void checkActionCards (List<Card> cards , Dealer dl ) {
		Card card = cards.get(cards.size()-1);//?
		
		if (card.getType().equals(CardType.ACTION) &&  ( card.getValue().equals(CardValue.PLUS2)    ||  
														 card.getValue().equals(CardValue.REVERSE)  ||
														 card.getValue().equals(CardValue.SKIP) )) {
		dl.changecheckPLUS4Param(true);
				if (card.getValue().equals(CardValue.PLUS2)) {
					dl.setChangeColorParam(Color.YELLOW);
					System.out.println("Новый цвет :"+ Color.YELLOW);
				}
			
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
	
	
}
