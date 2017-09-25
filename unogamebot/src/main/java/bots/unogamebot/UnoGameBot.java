package bots.unogamebot;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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

import Playerpkg.BotPlayer;
import Playerpkg.UserPlayer;
import enums.CardValue;
import enums.GameMode;
import inf.EnumService;
import logic.GameLogic;
import models.Game;
import models.Player;

public class UnoGameBot extends TelegramLongPollingBot {
	   
	private List <Game> activeGames = new ArrayList<Game>();
	
	public boolean hasGame (long chat_id) {
		
		for(Game game : activeGames ) {
			if (game.getChatId() == chat_id) return true;
		}
		
		return false;
	}
	
	public Game getGameByid(long id) {
		for(Game game : activeGames) {
			if (game.getChatId() == id) return game;
		}
		return null;
	}
	
	
	
	@Override
    public void onUpdateReceived(Update update) {
		
		
		if (update.hasMessage()) {
			if(update.getMessage().hasText()) {
				Message msg = update.getMessage();
				if (msg.getText().toLowerCase().equals("new game") || msg.getText().toLowerCase().equals("новая игра") ) {
					long chat_id = msg.getChatId();
					SendMessage message = new SendMessage();
						if (!hasGame(chat_id)){
							GameLogic gl = new GameLogic();
							try {
								gl.afterNewGameMessage(this, new Game(chat_id));
							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}//, msg);
							
							
//							activeGames.add(new Game(chat_id));
//							  // Create a message object object
//								 message.setChatId(chat_id)
//				                       .setText("выберите режим");
//								 
//							    //newSendMessage(message);
//							InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//			                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//			                List<InlineKeyboardButton> rowInline = new ArrayList<>();
//			                rowInline.add(new InlineKeyboardButton().setText("pvp").setCallbackData("ng_pvp " +  chat_id));
//			                rowInline.add(new InlineKeyboardButton().setText("pvb").setCallbackData("ng_pvb " +  chat_id));
//
//			                rowsInline.add(rowInline);
//
//			                markupInline.setKeyboard(rowsInline);
//			                message.setReplyMarkup(markupInline);
//			                newSendMessage(message);
							
							
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
				
				
				
				
				if ( (msg.getText().toLowerCase().equals("ready"))){
					GameLogic gl = new GameLogic();
					Game currentGame = getGameByid(msg.getChatId());
					gl.afterReadyMessage(this, currentGame, msg);
//					if(currentGame.getGameMode().equals(GameMode.PVB)){
//						System.out.println("00123");
//						if  (gl.checkready(currentGame.getPlayers()) == false ) {
//							///addPlayertoGame();
//							System.out.println("00124");
//							int id = msg.getFrom().getId();
//							String userName= msg.getFrom().getUserName();
//							currentGame.addNewPlayer(new UserPlayer(id,userName,LocalDateTime.now()));
//							System.out.println("currentGame  ="+currentGame.getPlayers().size()+"\n");//---------
//							SendMessage message = new SendMessage();
//							 message.setChatId(msg.getChatId())
//		                       .setText("Игра началась . Раздаем карты");
//							 
//							 newSendMessage(message);
//							 currentGame.getDealer().shuffle();
//							 for(Player pl: currentGame.getPlayers()){
//								 pl.addCards(currentGame.getDealer().giveCards(Constants.INIT));
//								 pl.printCards();
//							 }
//							// pl1.addCards(dl.giveCards(Constants.INIT));
//							 //pl2.addCards(dl.giveCards(Constants.INIT));
//							 
//							
//							 
//							// pl2.printCards();
//						}
//						else{
//							
//							SendMessage message = new SendMessage();
//							 message.setChatId(msg.getChatId())
//		                       .setText("все игроки готовы");
//						}
//						
//						
//					}
					
					
//					if(gl.checkready(currentGame.getPlayers(),currentGame.getGameMode())){
//						System.out.println("Все игроки готовы");
//					} else {
//						if (gl.checkready(currentGame.getPlayers())==false){
//							
//						}
//					}
					
				}
				
				
				
				
				
				
		}
		
		}
		
		
		
		if (update.hasCallbackQuery()){
			CallbackQuery callbackQuery = update.getCallbackQuery();
			String callbackData =  callbackQuery.getData();
					String[] ary = callbackData.split(" ");
				if (ary[0].equals("ng_c")) {
					List<Game> filteredList = activeGames.stream()
							.filter(i ->  i.getChatId()!= Long.parseLong(ary[1])).collect(Collectors.toList());
					 try {
						filteredList.add(new Game(Long.parseLong(ary[1])));
					} catch (NumberFormatException | CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 activeGames.addAll(filteredList);
						GameLogic gl = new GameLogic();
						System.out.println("0011");
						try {
							gl.afterNewGameMessage(this, new Game(Long.parseLong(ary[1])));
						} catch (NumberFormatException | CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 
				}
				
				if (ary[0].equals("ng_pvp") || ary[0].equals("ng_pvb") ) {
					if (ary[0].equals("ng_pvp")) {
						Game currentGame = getGameByid(Long.parseLong(ary[1]));
						currentGame.setGameMode(GameMode.PVP);
						 SendMessage message = new SendMessage();
					 message.setChatId(Long.parseLong(ary[1]))
	                       .setText("подтвердите готовность pvp");
					 
				    newSendMessage(message);
					}
					
					if (ary[0].equals("ng_pvb")) {
						Game currentGame = getGameByid(Long.parseLong(ary[1]));
						currentGame.setGameMode(GameMode.PVB);
						
						currentGame.addNewPlayer(new BotPlayer(1l,"Mike"));
						currentGame.addNewPlayer(new BotPlayer(2l,"John"));
						System.out.println("currentGame pl ="+currentGame.getPlayers().size()+"\n");//---------
						//System.out.println(currentGame.getPlayers().stream().count());
						 SendMessage message = new SendMessage();
					 message.setChatId(Long.parseLong(ary[1]))
	                       .setText("подтвердите готовность pvb");
					 
				    newSendMessage(message);
					}
					
					
					
					
					
					
					
					 
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
	
	public List<Game> getActiveGames() {
		return activeGames;
	}

	public void setActiveGames(List<Game> activeGames) {
		this.activeGames = activeGames;
	}

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
