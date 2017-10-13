package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import enums.PlayerStatus;
import inf.Dealer;

public class Player {
	
	private Long 		  id;
	private String		  name;
	private LocalDateTime regDT;
	
	private List<Card> cards ;
	
	private PlayerStatus status ;
	
	
	public PlayerStatus getStatus() {
		return status;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<Card> makeMove (Game gm){ return null;}
	
	public Player(){ cards = new ArrayList<Card>();}
	
	public Player(Long id, String name, LocalDateTime regDT) {
		this();
		this.id = id;
		this.name = name;
		this.regDT = regDT;
		//this.cards.addAll(dealer.give7cards());
	}
	
	public void printCards(){
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("Player "+ name + " cards :");
		for (Card card : cards){
			System.out.println("color  : "+card.getColor() + "  "+ "value : "+ card.getValue() );
		}
		System.out.println("card size = "+ this.cards.size());
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		
	}
	

	public void addCards (List<Card> cards  ) {
		this.cards.addAll(cards);
	}
	
	
//	public List<Card> putCards() {
//		// TODO Auto-generated method stub
//		List<Card> sublist = cards.subList(0, c);
//		cards = cards.subList(c,cards.size());
//		System.out.println("Диллер раздал "+sublist.size() +"карт(ы)");
//		System.out.println("В колоде у диллера осталось  "+cards.size() +"карт(ы)");
//		
//
//		
//		
//		//deck.
//		
//		return sublist;
//	}
	
	
	
	public List<Card> makeMove () {
		return null;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getRegDT() {
		return regDT;
	}

	public void setRegDT(LocalDateTime regDT) {
		this.regDT = regDT;
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
