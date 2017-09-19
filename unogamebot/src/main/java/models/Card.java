package models;

import enums.CardType;
import enums.CardValue;
import enums.Color;

public class Card implements Cloneable {

	private CardType  	type;
	
	private Color    	color;
	
	private CardValue	value;

	public Card(CardType type, Color color, CardValue value) {
		super();
		this.type = type;
		this.color = color;
		this.value = value;
	}

	public CardType getType() {
		return type;
	}

	public Color getColor() {
		return color;
	}

	public CardValue getValue() {
		return value;
	}
	
	 
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
	
}
