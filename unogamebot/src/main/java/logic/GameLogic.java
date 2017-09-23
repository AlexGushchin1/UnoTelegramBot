package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import bots.unogamebot.UnoGameBot;
import enums.PlayerStatus;
import models.Game;
import models.Player;

public class GameLogic {

	//public Message lastmsg;
	
	public void afterNewGameMessage(Game gm , Message msg){
		
	}
	
	
	
	public void afterNewGameMessage(UnoGameBot bot , Game gm ){//, Message msg
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
	
	public boolean checkready (Set<Player> players) {
		
		for (Player pl : players ){
			 if( pl.getStatus().equals(PlayerStatus.NOT_READY)) return false;
		}
		return true;
		
	}
	
	
	
	
	
	
	
	
	
	public void newGame(Message msg){
		
	}
	
	public void readyLogic (UnoGameBot bot) {
		
	}
	
	
}
