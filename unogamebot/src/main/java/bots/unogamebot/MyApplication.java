package bots.unogamebot;

import com.google.inject.Inject;

import enums.CardValue;
import inf.EnumService;

public class MyApplication {
	private final EnumService mega;
	 @Inject
    public MyApplication(EnumService mega) {
        this.mega = mega;
      
        System.out.println("MegaMain instance constructor");
    }
	 
	 
	 public void Test1(CardValue cv ){
		 mega.getCardValue(cv);
		 //System.out.println("MegaMain instance constructor")
		 }
	 
 
}
