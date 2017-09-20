package bots.unogamebot;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Set;

import Dealerpkg.ClassicDealer;
import Playerpkg.UserPlayer;
import enums.Color;
import inf.Dealer;
import models.Game;
import models.Player;

public class AppDemo {
	 public static void main( String[] args ) throws CloneNotSupportedException
	    {
		 System.out.println("AppDemo:");
		 
		 
		 Game newgame = new Game(103178L);
		 
		 newgame.addDealer(new ClassicDealer());
		 
		 Player  pl1 = new UserPlayer(222L,"Alex",LocalDateTime.now());
		 
		 Player  pl2 = new UserPlayer(333L,"Serg",LocalDateTime.now());
		 
		 Dealer dl = newgame.getDealer();
		 dl.shuffle();
		 dl.printCards();
		 
		 pl1.addCards(dl.giveCards(Constants.INIT));
		 pl2.addCards(dl.giveCards(Constants.INIT));
		 
		 pl1.printCards();
		 
		 pl2.printCards();


		 
		 
		 
	    }
	 
}
