package bots.unogamebot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class App 
{
    public static void main( String[] args )
    {
    	 ApiContextInitializer.init();

         TelegramBotsApi botsApi = new TelegramBotsApi();
         
         //Injector injector = Guice.createInjector(new AppInjector());
         
         // Register our bot
         try {
             botsApi.registerBot(new UnoGameBot());
         } catch (TelegramApiException e) {
             e.printStackTrace();
         }
    }
}
