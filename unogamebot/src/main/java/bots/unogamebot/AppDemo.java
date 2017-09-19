package bots.unogamebot;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Set;

import Dealerpkg.ClassicDealer;
import enums.Color;
import models.Game;
import models.Player;

public class AppDemo {
	 public static void main( String[] args ) throws CloneNotSupportedException
	    {
		 System.out.println("AppDemo:");
		 

		 
		 Game newgame = new Game(103178L);
		 
		 newgame.addDealer(new ClassicDealer());
		 
		 Player  pl1 = new Player(222L,"Alex",LocalDateTime.now());
		 
		 Player  pl2 = new Player(333L,"Serg",LocalDateTime.now());
		 
		 newgame.getDealer().shuffle();
		 
		 newgame.getDealer().printCards();
		 

		 
		 
		 
	    }
	 
}
