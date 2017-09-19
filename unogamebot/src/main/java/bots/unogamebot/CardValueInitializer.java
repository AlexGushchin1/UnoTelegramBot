package bots.unogamebot;

import java.util.EnumMap;
import java.util.Map.Entry;

import com.google.inject.Singleton;

import enums.CardValue;
import enums.CardValueAction;
import enums.CardValueWild;
import inf.EnumService;

@Singleton
public class CardValueInitializer implements EnumService {

	private EnumMap<CardValue, String> valueMap;  
	
	public CardValueInitializer(){
		
		valueMap = new EnumMap<CardValue, String>(CardValue.class);
		valueMap.put(CardValue.ZERO, 	"0");
		valueMap.put(CardValue.ONE, 	"1");
		valueMap.put(CardValue.TWO, 	"2");
		valueMap.put(CardValue.THREE, 	"3");
		valueMap.put(CardValue.FOUR, 	"4");
		valueMap.put(CardValue.FIVE, 	"5");
		valueMap.put(CardValue.SIX, 	"6");
		valueMap.put(CardValue.SEVEN, 	"7");
		valueMap.put(CardValue.EIGHT, 	"8");
		valueMap.put(CardValue.NINE, 	"9");
	}
	
	@Override
	public String getCardValue(CardValue cv) {
		return "test";
	}
	
	public CardValue getCardValue(String cv) {
		
		for (Entry<CardValue, String> entry : valueMap.entrySet()) {
	        if (entry.getValue().equals(cv)) {
	            return entry.getKey();
	        }
	    }
		return null;
	}
	
	public CardValue getCardValue(CardValueAction obj) {
		
		if (obj.equals(CardValueAction.PLUS2)){
			return CardValue.PLUS2;
		}
		
		if (obj.equals(CardValueAction.REVERSE)){
			return CardValue.REVERSE;
		}
		
		if (obj.equals(CardValueAction.SKIP)){
			return CardValue.SKIP;
		}
		
		return null;
	}
	
	
	public CardValue getCardValue(CardValueWild obj){
		if (obj.equals(CardValueWild.CHANGECOLOR)){
			return CardValue.CHANGECOLOR;
		}
		
		if (obj.equals(CardValueWild.PLUS4)){
			return CardValue.PLUS4;
		}
		return null;
	}
	
	

}
