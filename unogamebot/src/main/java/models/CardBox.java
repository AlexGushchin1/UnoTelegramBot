package models;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import bots.unogamebot.CardValueInitializer;
import bots.unogamebot.Constants;
import enums.CardType;
import enums.CardValue;
import enums.CardValueAction;
import enums.CardValueWild;
import enums.Color;

public class CardBox {
	
	private List <Card>card; 
	Logger log = Logger.getLogger(CardBox.class.getName());
	
	public CardBox() throws CloneNotSupportedException {
		
		CardValueInitializer cvi = new CardValueInitializer();
		
		card = new ArrayList<Card>();
		
		for (Color color : Color.values()) {
				if (!color.equals(Color.BLACK))	{
					
						int i=0;
						do{
							Card newcard  = new Card(CardType.NUMBER,color,cvi.getCardValue(String.valueOf(i)));
							
						if ( cvi.getCardValue(String.valueOf(i)) != CardValue.ZERO){
							Card cardclone = (Card) newcard.clone() ;
							card.add(newcard);
							card.add(cardclone);
							} else {
								card.add(newcard);
						}
							

							i++;
						}while(i<10 );
					
						
						
						for (CardValueAction cardValueAction : CardValueAction.values()) {
			
								for(int j=0;j<2;j++) card.add(new Card(CardType.ACTION,
												   				       color,
												                       cvi.getCardValue(cardValueAction)));
		
						}
					
				}
		
		}
		
		for(CardValueWild cardValueWild : CardValueWild.values()) {
		for(int i=0;i<4;i++){
			card.add(new Card(CardType.WILD,Color.BLACK,cvi.getCardValue(cardValueWild) ));
		}
		
		
	}
		
		
		log.info("Создан новый набор карт{} : "+ card.size() );
		
		
			}

	
	public List <Card> getCardsFromCardBox(){
		return card;
	}
	
}
