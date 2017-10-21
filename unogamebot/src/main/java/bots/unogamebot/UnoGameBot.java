package bots.unogamebot;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.inlinequery.ChosenInlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultPhoto;
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
		if (update.hasCallbackQuery()){
			System.out.println("----9----->");
			System.out.println("--------->"+update.getCallbackQuery().getMessage().getText());
			
		}
//		update.hasInlineQuery()){
//			InlineQuery query = update.getInlineQuery();
//			if (query.getQuery().equals("mycards")) {
//				
//			}
//		}
		
		
		
		
		
		if (update.hasInlineQuery()){
			InlineQuery query = update.getInlineQuery();
			
			if (query.getQuery().equals("sayuno")) {
				
			}
			
//			if (query.getQuery().equals("unoto")) {
//				
//			}
			
			
			if (query.getQuery().equals("mycards")) {
				
				String name = update.getInlineQuery().getFrom().getUserName();
				System.out.println("name = " + name);
				
				
				
				AnswerInlineQuery answer = new AnswerInlineQuery();
				answer.setPersonal(false);
				answer.setInlineQueryId(query.getId());
				
				
				
				
				
				
				//InlineKeyboardMarkup replyMarkup = createKeyboard("111");
				
				
				//SendPhoto sendPhoto = new SendPhoto();
				//sendPhoto.setNewPhoto(file)
				//this.sendPhoto(sendPhoto);
				InlineQueryResult rez = new InlineQueryResultPhoto();
			
				
				InlineQueryResultArticle article = new InlineQueryResultArticle();
				InlineQueryResultPhoto aaa= new InlineQueryResultPhoto ();
				aaa.setId("pic1");
				aaa.setPhotoUrl("https://pp.userapi.com/c639922/v639922579/52de6/8e3HgRiPAos.jpg");
				aaa.setThumbUrl("https://pp.userapi.com/c639922/v639922180/52071/UrQh8CU8IU4.jpg");
				InputTextMessageContent t2 = new InputTextMessageContent();
				t2.setMessageText("+test");
				aaa.setInputMessageContent(t2);
				
				//ChosenInlineQuery qq = new ChosenInlineQuery();
				
				article.setTitle("синяя 4 ");
				//article.setReplyMarkup(replyMarkup);
				article.setId("plug2");
				article.setThumbUrl("https://pp.userapi.com/c639922/v639922579/52de6/8e3HgRiPAos.jpg");
				
				InlineQueryResultArticle article2 = new InlineQueryResultArticle();
				article2.setTitle("красная 7 ");
				//article2.setReplyMarkup(replyMarkup);
				article2.setId("plug3");
				article2.setThumbUrl("https://pp.userapi.com/c639922/v639922579/52ded/FwNlMjYEnTM.jpg");
				
				
				InputTextMessageContent textMessageContent = new InputTextMessageContent();
				//textMessageContent.setParseMode("<a href=\"' + https://pp.userapi.com/c639922/v639922579/52ded/FwNlMjYEnTM.jpg + '\"></a>");
				textMessageContent.setMessageText("*Buttplug*\nConnecting...");
				article.setInputMessageContent(textMessageContent);
				///textMessageContent.
				//article.set
				InputTextMessageContent textMessageContent2 = new InputTextMessageContent();
				textMessageContent2.setMessageText("*Buttplug*\nConnecting...");
				article2.setInputMessageContent(textMessageContent2);
				
				List<InlineQueryResult>lst = new ArrayList<InlineQueryResult>();
				lst.add(article);lst.add(article2);
				lst.add(aaa);
				
				answer.setResults(lst);
				
				
				
				//final InlineKeyboardMarkup replyMarkup = createKeyboard(id);
				 
				
				
				
				
				answer.setCacheTime(0);
				
				try {
					answerInlineQuery(answer);
				} catch (final TelegramApiException e) {
					//logger.warn("Failed to send message: {}", answer, e);
				}
				List<InlineQueryResult> queryResults = new ArrayList<>();
				InlineQueryResultArticle in = new InlineQueryResultArticle();
				
				
				
				//BaseResponse response = bot.execute(new AnswerInlineQuery(inlineQuery.id(), r1, r2, r3, r4, r5));

//				queryResults.add(in
//	                    .parseMode(ParseMode.MARKDOWN)
//	                    .title("Custom Markdown")
//	                    .description("description")
//	                    .disableWebPagePreview(true)
//	                    .messageText("")
//	                    .build()
//	                    );
			}
		}
		
		
		if (update.hasMessage()) {
			if(update.getMessage().hasText()) {
				Message msg = update.getMessage();
				
				String msgtextLC = msg.getText().toLowerCase();
				
				System.out.println("--------->"+msgtextLC);
				String[] tmpr = msgtextLC.split(" ");
				////System.out.println(tmpr[0]);
				//System.out.println(tmpr[1]);
				if (msgtextLC.equals("new game") || msgtextLC.equals("новая игра") ) {
					long chat_id = msg.getChatId();
					SendMessage message = new SendMessage();
						if (!hasGame(chat_id)) {
							GameLogic gl = new GameLogic();
							
							try {
								
								gl.afterNewGameMessage(this, new Game(chat_id));
								
							} catch (CloneNotSupportedException e) {e.printStackTrace();}
							
							
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
				
				
				
				
				if ( (msgtextLC.equals("ready"))){
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
				
				
				
				
				
				
				
				
				if (tmpr[0].equals("ход") && tmpr[1].equals("игрока") ) {
					System.out.println("test z112");
					String[] tmp = msgtextLC.split(" ");
					Player nextPlayer = this.getGameByid(msg.getChatId()).getNextPlayer();
					//nextPlayer.getName() );
//					bot.newSendMessage(message);
					
					if (nextPlayer instanceof BotPlayer) {
						System.out.println("test z222");
						 nextPlayer.makeMove(this,this.getGameByid(msg.getChatId()));
				 		//currentGame.nextMove();
				 		SendMessage message = new SendMessage();
				 		message = new SendMessage();
					 	message.setChatId(msg.getChatId())
					 		.setText("Ход игрока {01} " + this.getGameByid(msg.getChatId()).nextMove().getName() );
				 		newSendMessage(message);
					}
					
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    private InlineKeyboardMarkup createKeyboard(String id) {
//		final InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
//		final List<List<InlineKeyboardButton>> rows = new ArrayList<>();
//		rows.add(new ArrayList<>());
//		rows.get(0).add(new InlineKeyboardButton().setText("💓 2s").setCallbackData(id + "|buzz"));
//		rows.get(0).add(
//				new InlineKeyboardButton().setText("💓 "  + "s").setCallbackData(id + "|sine"));
//		rows.add(new ArrayList<>());
//		rows.get(1).add(new InlineKeyboardButton().setText("∿ slower").setCallbackData(id + "|interval+"));
//		rows.get(1).add(new InlineKeyboardButton().setText("∿ faster").setCallbackData(id + "|interval-"));
//		rows.add(new ArrayList<>());
//		rows.get(2).add(new InlineKeyboardButton().setText("- 10 %").setCallbackData(id + "|amplitude-"));
//		rows.get(2).add(new InlineKeyboardButton().setText("+ 10 %").setCallbackData(id + "|amplitude+"));
//		replyMarkup.setKeyboard(rows);
//		return replyMarkup;
//	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
