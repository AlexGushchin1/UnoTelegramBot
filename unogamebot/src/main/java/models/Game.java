
package models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import Dealerpkg.ClassicDealer;
import enums.GameMode;
import inf.Dealer;

public class Game {

	private long  		  id;
	private long 		  chatId;
	private boolean 	  isOver;
	private LocalDateTime regDT;
	private Set<Player>	  players;
	private GameMode gameMode;
	
	private Dealer dealer; 
	
	public Game() {
		this.players = new HashSet<Player>();
		this.regDT   = LocalDateTime.now();
	}
	
	public Game(long chatId) throws CloneNotSupportedException {
		this();
		this.chatId = chatId;
		this.regDT   = LocalDateTime.now();
		this.dealer = new ClassicDealer();
	}
	
	
	public Game(long id, long chatId, boolean isOver, LocalDateTime regDT, Set<Player> players ,Dealer dealer) {
		this();
		this.id = id;
		this.chatId = chatId;
		this.isOver = isOver;
		this.dealer = dealer;
	}
	
	
	public Set<Player>addNewPlayer(Player pl) {
		if (pl != null) players.add(pl);
		return players;
	}
	
	public void addDealer(Dealer dl) {
		if (dl != null) this.dealer = dl;
		//return players;
	}

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getChatId() {
		return chatId;
	}
	public void setChatId(long chatId) {
		this.chatId = chatId;
	}
	public boolean isOver() {
		return isOver;
	}
	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}
	public LocalDateTime getRegDT() {
		return regDT;
	}
	public void setRegDT(LocalDateTime regDT) {
		this.regDT = regDT;
	}
	public Set<Player> getPlayers() {
		return players;
	}
	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}
	
	
	
	
	
}
