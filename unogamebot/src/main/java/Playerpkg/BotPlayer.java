package Playerpkg;

import enums.PlayerStatus;
import models.Player;

public class BotPlayer extends Player {

	public BotPlayer (){
		this.setStatus(PlayerStatus.READY);
	}
	
}
