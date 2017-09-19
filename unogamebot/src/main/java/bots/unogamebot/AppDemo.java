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
		 
		
		 
		// EnumMap<Color, String> stateMap = new EnumMap<Color, String>(Color.class);
	    //stateMap.get(Color.BLUE);
		 
		 
		 Game newgame = new Game(103178L);
		 
		 newgame.addDealer(new ClassicDealer());
		 
		 Player  pl1 = new Player(222L,"Alex",LocalDateTime.now());
		 
		 Player  pl2 = new Player(333L,"Serg",LocalDateTime.now());
		 
		 newgame.getDealer().shuffle();
		 
		 newgame.getDealer().printCards();
		 
		 //newgame.getDealer().
		 
		 
		 
		// 
		// 
		 
		 //Player pl5 =  pl3;
		// newgame.setPlayers(newgame.addNewPlayer(pl2));;
		 //newgame.setPlayers(newgame.addNewPlayer(pl3));
		 //
		 // Player pl4 = new Player(222L,"AA",LocalDateTime.now());
		 // newgame.setPlayers(newgame.addNewPlayer(pl4));
		 // System.out.println("00S");
		  
		 // Set<Player> players = newgame.getPlayers();
		 // players.stream().forEach(e->System.out.println(e.getName()));
		  
//		 if(newgame.getPlayers().stream().filter(e->e.getId().equals(222L)).count()==0 ) {
//			
//		 } else
//		 {
//			
//		 }
		 
		 //Stream.filter(java.util.function.Predicate<? super T>)
		 
		 
		 
		 
		 
	    }
	 
}
