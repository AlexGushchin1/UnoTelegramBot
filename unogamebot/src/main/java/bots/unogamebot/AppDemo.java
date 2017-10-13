package bots.unogamebot;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Dealerpkg.ClassicDealer;
import Playerpkg.BotPlayer;
import Playerpkg.UserPlayer;
import enums.CardType;
import enums.CardValue;
import enums.Color;
import inf.Dealer;
import models.Card;
import models.Game;
import models.Player;

public class AppDemo {
	 public static void main( String[] args ) throws CloneNotSupportedException
	    {
		 System.out.println("AppDemo:");
		 
		 
		 Game newgame = new Game(103178L);
		 
		 newgame.addDealer(new ClassicDealer());
		 
		 Player  pl1 = new UserPlayer(222L,"Alex",LocalDateTime.now());
		 
		 //Player  pl2 = new UserPlayer(333L,"Serg",LocalDateTime.now());
		 
		 Player  b_pl2 = new BotPlayer(333L,"Mike");
		 Player  b_pl3 = new BotPlayer(334L,"John");
		 Dealer dl = newgame.getDealer();
		 Set <Player> players = new HashSet <Player>();
		 //players.add(pl1);
		 players.add(b_pl2);
		 players.add(b_pl3);
		 newgame.setPlayers(players);
		 
		 dl.shuffle();
		 
		 
		 for (Player player : newgame.getPlayers()) {
			 player.addCards(dl.giveCards(Constants.INIT));
			 player.printCards();
		 }
		 
		 System.out.println("ИГРОКИ");
		 players.forEach(p->System.out.println(p.getName()));
		 System.out.println("----------------------------");
		 Deque<Player>deque = new ArrayDeque<Player>();
		 deque.addFirst(b_pl3);
		 deque.addFirst(b_pl2);
		 //deque.addFirst(pl1);
		 
		 System.out.println("++++++++++++++++++++++++++++++++++");
		 System.out.println("(before) Deck card : "+ dl.getDeckCard().getColor() + " " +dl.getDeckCard().getValue()); 
		 // почему то всегда RED ZERO ?? - неверно !!!
		 
//		List <Card> tmp = new ArrayList<Card>();
//		Card tmpCard = new  Card(CardType.ACTION, Color.RED, CardValue.PLUS2);
//		tmp.add(tmpCard);
//		 dl.puttoDeck(tmp);
//		 System.out.println("(after) Deck card : "+ dl.getDeckCard().getColor() + " " +dl.getDeckCard().getValue()); 
		 
	        while(deque.peek()!=null) {
	        	
	        	Player  P = deque.pop();
	        	System.out.println("Ход игрока "+ P.getName());
	        	P.makeMove(newgame);
	        	
	        	
	            // извлечение c начала
	        	
	        	 
	           // System.out.println(P.getName());
	            deque.addLast(P);
	            
	            try {
	                Thread.sleep(2000);
	          } catch (InterruptedException e) {} 
	        }
		 
		// dl.printCards();
		 
		 
		 
		// pl1
		// pl2.addCards(dl.giveCards(Constants.INIT));
		 
		 
		 
		 //pl2.printCards();


		 
		 
		 
	    }
	 
}
