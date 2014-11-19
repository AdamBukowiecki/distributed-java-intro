package main.threads;

import java.util.Random;

import main.actors.Chairman;
import main.actors.Recipient;

public class RecipientThread extends Thread {

	private volatile Recipient parent;
	private volatile boolean isRunning;
	private volatile boolean auctionResult;
	private volatile boolean inAuction;
	
	private Random rand;
	
	public RecipientThread(Recipient r) {
		super();
		rand = new Random();
		parent = r;
		isRunning = true;
	}
	
	@Override
	public void run() {
		
		while(isRunning) {
			
			// Waiting before registrating 1s - 5s
			waitTime((rand.nextInt(5) + 1) * 1000);
			
			auctionResult = false;
			
			// Try to register
			if(Chairman.getInstance().isRegistrationOpen()) {
				Chairman.getInstance().registerRecipient(parent.getName());
			}
			
			// Waiting for end of auction (one item)
			while(inAuction);
			
			// If won
			if(auctionResult == true) {
				parent.sayWon();
				// Waiting after won 5s - 15s
				waitTime(1000 * (rand.nextInt(11) + 5));
			}
			
		}
		
		parent.sayGoodBye();
		
	}
	
	public void setRunning(boolean b) {
		isRunning = b;
	}
	

	public boolean isInAuction() {
		return inAuction;
	}


	public void setInAuction(boolean inAuction) {
		this.inAuction = inAuction;
	}
	
	/**
	 * Waits using short periods of time what is useful for quick interrupt
	 */
	private void waitTime(int time) {
		int secCounter = 0;
		final int ITERATION = 100;
		while(isRunning && secCounter < ITERATION) {
			try {
				Thread.sleep(time/ITERATION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			secCounter++;
		}
	}

	public void setAuctionResult(boolean b) {
		auctionResult = true;
	}
	

}
