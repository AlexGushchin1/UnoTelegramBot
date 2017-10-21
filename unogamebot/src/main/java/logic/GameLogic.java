package logic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import Playerpkg.BotPlayer;
import Playerpkg.UserPlayer;
import bots.unogamebot.Constants;
import bots.unogamebot.UnoGameBot;
import enums.GameMode;
import enums.PlayerStatus;
import models.Game;
import models.Player;

public class GameLogic {

	//public Message lastmsg;
	
	public void afterNewGameMessage(Game gm , Message msg){
		
	}
	
	public void afterReadyMessage(UnoGameBot bot , Game currentGame , Message msg ) {
		SendMessage message = new SendMessage();
		
		if(currentGame.getGameMode().equals(GameMode.PVB)) {
			
			if  (checkready(currentGame.getPlayers()) == false ) {
				
				int fromId = msg.getFrom().getId();
				String fromName = msg.getFrom().getUserName();
				currentGame.addNewPlayer(new UserPlayer(fromId, fromName,LocalDateTime.now()));
				
			} 
			else {
				currentGame.AddPlayersToDeque(); //------------
				
				Player nextPlayer =  currentGame.getNextPlayer(); //------------
				
				nextPlayer = currentGame.nextMove();
				message.setChatId(msg.getChatId()).setText("Ход игрока {00} " + nextPlayer.getName() );
				bot.newSendMessage(message);
				
				
				
//				nextPlayer =  
//				message.setChatId(msg.getChatId())
//		 		.setText("Ход игрока " + nextPlayer.getName() );
//				bot.newSendMessage(message);
				
//				if (nextPlayer instanceof BotPlayer) {
//			 		nextPlayer.makeMove();
//			 		//currentGame.nextMove();
//			 		message = new SendMessage();
//				 	message.setChatId(msg.getChatId())
//				 		.setText("Ход игрока " + currentGame.nextMove().getName() );
//			 		bot.newSendMessage(message);
//				}
				
				
//			 	}
				
			//}
			
			
//			if  (checkready(currentGame.getPlayers()) == false ) {
//
//				int id = msg.getFrom().getId();
//				String userName = msg.getFrom().getUserName();
//				
//				
//				
//				message.setChatId(msg.getChatId()).setText("Игрок " + userName + " готов ! " );
//				bot.newSendMessage(message);
//				
//				
//				
//				
//				//System.out.println("currentGame  ="+currentGame.getPlayers().size()+"\n");//---------
//				
//				 message.setChatId(msg.getChatId())
//                   .setText("Игра началась . Раздаем карты");
//				 
//				 bot.newSendMessage(message);
//				 currentGame.getDealer().shuffle();
//				 for(Player pl: currentGame.getPlayers()){
//					 pl.addCards(currentGame.getDealer().giveCards(Constants.INIT));
//					 pl.printCards();
//					 
//				 }
//				 
//				 
//				 message.setChatId(msg.getChatId())
//                 .setText("Проверьте Ваши карты : ");
//				 bot.newSendMessage(message);
//				 
//				// currentGame.ge
//				// pl1.addCards(dl.giveCards(Constants.INIT));
//				 //pl2.addCards(dl.giveCards(Constants.INIT));
//				 
//				
//				 
//				// pl2.printCards();
//			}
//			else{
//				
//				SendMessage message = new SendMessage();
//				 message.setChatId(msg.getChatId())
//                   .setText("все игроки готовы");
//			}
//			
			}
		}
	}
	
	public void afterNewGameMessage(UnoGameBot bot , Game gm ) throws CloneNotSupportedException{//, Message msg
		long chat_id = gm.getChatId();//msg.getChatId();
		
		SendMessage message = new SendMessage();
			//if (!bot.hasGame(chat_id)){
				List <Game> activeGames =bot.getActiveGames();
				activeGames.add(new Game(chat_id));
				  // Create a message object object
					 message.setChatId(chat_id)
	                       .setText("выберите режим {v2}");
					 
				    //newSendMessage(message);
				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("pvp").setCallbackData("ng_pvp " +  chat_id));
                rowInline.add(new InlineKeyboardButton().setText("pvb").setCallbackData("ng_pvb " +  chat_id));

                rowsInline.add(rowInline);

                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                bot.newSendMessage(message);
              //  }
	}
	
	public boolean checkready (Set<Player> players , GameMode gm) {
		if (gm.equals(GameMode.PVP)){
			for (Player pl : players ){
			 if( pl.getStatus().equals(PlayerStatus.NOT_READY)) return false;
			 System.out.println("Есть не готовые игроки");
			}
		}
		
		return true;
		
	}
	
	public boolean checkready (Set<Player> players ) {
		//if (gm.equals(GameMode.PVP)){
		//&& currentGame.getPlayers().size()>1
		if (players.size()>2){
			for (Player pl : players ){
			 if( pl.getStatus().equals(PlayerStatus.NOT_READY)) return false;
			// System.out.println("Есть не готовые игроки");
			}
		}
		else return false;
			
		//}
		
		return true;
		
	}
	
	
	
	
	
	
	
	public void newGame(Message msg){
		
	}
	
	public void readyLogic (UnoGameBot bot) {
		
	}
	
	
}
