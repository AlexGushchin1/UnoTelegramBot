package bots.unogamebot;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import enums.CardValue;
import inf.EnumService;
import models.Game;

public class UnoGameBot extends TelegramLongPollingBot {
	   
	List <Game> activeGames = new ArrayList<Game>();
	
	public boolean hasGame (long chat_id) {
		
		for(Game game : activeGames ) {
			if (game.getChatId() == chat_id) return true;
		}
		
		return false;
	}
	
	@Override
    public void onUpdateReceived(Update update) {
		
		
		if (update.hasMessage()) {
			if(update.getMessage().hasText()) {
				Message msg = update.getMessage();
				if (msg.getText().equals("new game") || msg.getText().equals("новая игра") ) {
					long chat_id = msg.getChatId();
					SendMessage message = new SendMessage();
						if (!hasGame(chat_id)){
							activeGames.add(new Game(chat_id));
							  // Create a message object object
								 message.setChatId(chat_id)
				                        .setText("START NEW GAME");
							    newSendMessage(message);
						} else{
							message.setChatId(chat_id)
	                        .setText("Игра уже существует : Начать новую или продолжить?");
							  InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
				                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
				                List<InlineKeyboardButton> rowInline = new ArrayList<>();
				                rowInline.add(new InlineKeyboardButton().setText("Новая игра").setCallbackData("ng_c " + chat_id));
				                rowInline.add(new InlineKeyboardButton().setText("Продолжить").setCallbackData("cg_c"));

				                rowsInline.add(rowInline);

				                markupInline.setKeyboard(rowsInline);
				                message.setReplyMarkup(markupInline);
				                newSendMessage(message);
						}
					
				}
		}
		
		}
		
		
		
		if (update.hasCallbackQuery()){
			CallbackQuery callbackQuery = update.getCallbackQuery();
			String callbackData =  callbackQuery.getData();
					String[] ary = callbackData.split(" ");
					
				if (ary[0].equals("ng_c")){
					
					activeGames.add(new Game(-2030410L));
					activeGames.add(new Game(-203043L));
					System.out.println("ary 1 "+ary[1]);
					System.out.println("activeGames size  ="+activeGames.size());
					
					for (Game gm : activeGames){
						System.out.println("---"+gm.getChatId());
					}
					System.out.println("long= "+Long.parseLong(ary[1]));
					List<Game> filteredList = activeGames.stream()
							.filter(i ->  i.getChatId()!= -204380010l   ).collect(Collectors.toList());
					System.out.println("filteredList size  ="+filteredList.size());
					//activeGames.st
				}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		if (update.hasMessage() && ) {
//			Message msg = update.getMessage();	
//			
//				
//				 SendMessage message = new SendMessage() 
//                    .setChatId(chat_id)
//                    .setText("Hello " + msg.getFrom().getFirstName());
//	            try {
             
//	            } catch (TelegramApiException e) {
//               e.printStackTrace();}
//			}
//		}
//		
		
            
    }
	
	
	
	
	
	
	//public void newGameLogic
	
	public void newSendMessage (SendMessage  message) {
		try {
			sendMessage(message);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	

    @Override
    public String getBotUsername() {
        // TODO
        return "crazysnailsUno";
    }

    @Override
    public String getBotToken() {
        
        try {
			return new String(Files.readAllBytes(Paths.get("token.txt")));
		} catch (IOException e) {e.printStackTrace();};
		
	return "empty token string";
    }
}
