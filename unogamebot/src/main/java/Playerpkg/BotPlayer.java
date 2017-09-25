package Playerpkg;

import enums.PlayerStatus;
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
}
