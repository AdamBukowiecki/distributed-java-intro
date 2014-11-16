package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.actors.Donor;
import main.actors.MarketManager;
import main.actors.Recipient;

public class Starter {

	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = new String();
		try {
			while( (line = br.readLine()) != null) {
				if(line.isEmpty())
					break;
				String[] splitLine = line.split(" ");
				String name = new String();
				int i = 1;
				while(i < splitLine.length)
					name += splitLine[i++] + " ";
				if(splitLine[0].equals("R")) {
					MarketManager.getInstance().addNewRecipient(new Recipient(name));
				}
				if(splitLine[0].equals("D")) {
					MarketManager.getInstance().addNewDonor(new Donor(name));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MarketManager.getInstance().openMarket();
		
		try {
			Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(MarketManager.getInstance().isMarketOpen())
			MarketManager.getInstance().closeMarket();
		
	}

}
