package main.threads;

import java.util.Random;

import main.actors.Chairman;
import main.actors.MarketManager;
import main.actors.Recipient;
import main.models.Item;
import main.tools.ConfigurationTool;

public class ChairmanThread extends Thread {

	private volatile Chairman parent;
	
	private static final int TIME_TO_END_WITHOUT_ITEMS = Integer.parseInt(ConfigurationTool.getParamter("TIME_TO_END_WITHOUT_ITEMS"));//5000;
	private static final int REGISTRATION_TIME = Integer.parseInt(ConfigurationTool.getParamter("REGISTRATION_TIME"));//10 * 1000;
	private volatile boolean isRunning;
	
	public ChairmanThread(Chairman c) {
		super();
		parent = c;
		isRunning = true;
	}
	
	@Override
	public void run() {
		
		int emptyCounter = 0;
		Random rand = new Random();
		while(emptyCounter != 10 && isRunning) {
			while(!parent.getItemQueue().isEmpty() && isRunning) {
				emptyCounter = 0;
				Item currentItem = parent.getItemQueue().removeFirst();
				parent.getinCurrentAuctionRecipientsSet().clear();
				registration();
				if(!parent.getinCurrentAuctionRecipientsSet().isEmpty()) {
					Recipient[] registeredRecipientsArray = new Recipient[parent.getinCurrentAuctionRecipientsSet().size()];
					registeredRecipientsArray = parent.getinCurrentAuctionRecipientsSet().toArray(registeredRecipientsArray);
					Recipient winner = registeredRecipientsArray[rand.nextInt(parent.getinCurrentAuctionRecipientsSet().size())];
					winner.winNewItem(currentItem);
					System.out.println("Winner for auction " + currentItem.getName() + " is " + winner.getName() + "!");
					for(Recipient reci: parent.getinCurrentAuctionRecipientsSet())
						reci.auctionIsFinished();
				} else {
					System.out.println("There is no winner for " + currentItem.getName());
				}
				//notifyAll();
			}
			if(parent.getItemQueue().isEmpty()) {
				emptyCounter++;
				try {
					sleep(TIME_TO_END_WITHOUT_ITEMS/10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
				
		if(emptyCounter == 10) {
			System.out.println("No auctions within 5 seconds. Closing the market");
			MarketManager.getInstance().closeMarket();
		}
		
		System.out.println("Chairman says good bye");

	}
	
	private void registration() {
		parent.setRegistrationOpen(true);
		try {
			sleep(REGISTRATION_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		parent.setRegistrationOpen(false);
	}
	
	public void setIsRunning(boolean b) {
		isRunning = b;
	}
	
}
