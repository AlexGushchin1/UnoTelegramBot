package bots.unogamebot;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import enums.CardValue;
import inf.EnumService;

public class UnoGameBot extends TelegramLongPollingBot {
	
//	 private final EnumService mega;
//	  public UnoGameBot() {
//		  mega=null;
//		// TODO Auto-generated constructor stub
//	}
//	 @Inject
//	    public UnoGameBot(EnumService mega) {
//	        this.mega = mega;
//	        //this.puper = puper;
//	        System.out.println("MegaMain instance constructor");
//	    }
//	 
	
   


	@Override
    public void onUpdateReceived(Update update) {
		
		if (update.hasMessage() && update.getMessage().hasText()) {
			Message msg = update.getMessage();	
			if (msg.getText().equals("Hello")) {
				long chat_id = msg.getChatId();
				 SendMessage message = new SendMessage() 
                    .setChatId(chat_id)
                    .setText("Hello " + msg.getFrom().getFirstName());
	            try {
               sendMessage(message); 
	            } catch (TelegramApiException e) {
               e.printStackTrace();}
			}
		}
		
		
   //	 Injector injector = Guice.createInjector(new AppInjector());
    	//UnoGameBot main = injector.getInstance(UnoGameBot.class);
    	//MyApplication app = injector.getInstance(MyApplication.class);
    //	app.Test1(CardValue.NINE);
    	//app.
    	//main.va
    	//mega.getCardValue(CardValue.NINE);
    	
    	//CardValueInitializer cardValueInitializer;// = new CardValueInitializer() 
        // TODO
//    	
//            // Set variables
//            String message_text = update.getMessage().getText()
//            long chat_id = update.getMessage().getChatId()
//
//           
//            try {
//                sendMessage(message); // Sending our message object to user
//            } catch (TelegramApiException e) {
//                e.printStackTrace();}
//    	}
            
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
