package main;

import main.actors.MarketManager;
import main.tools.ActorsFactory;
import main.tools.ConfigurationTool;

public class Starter {

	public static void main(String[] args) {
		
		// If path to config.txt file is incorrect, fix it!
		ConfigurationTool.loadConfiguration("config.txt");
		
		ActorsFactory.createActors();
		
		MarketManager.getInstance().openMarket();
		
		// Close market after OPEN_MARKET_TIME miliseconds
		try {
			int secCounter = 0;
			final int OPEN_MARKET_TIME = Integer.parseInt(ConfigurationTool.getParamter("OPEN_MARKET_TIME"));
			while(MarketManager.getInstance().isMarketOpen() && secCounter < 1000) {
				Thread.sleep(OPEN_MARKET_TIME / 1000);
				secCounter++;
			}
			if(secCounter == 1000)
				System.out.println("Times up! Closing the market!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(MarketManager.getInstance().isMarketOpen())
			MarketManager.getInstance().closeMarket();
		
	}

}
