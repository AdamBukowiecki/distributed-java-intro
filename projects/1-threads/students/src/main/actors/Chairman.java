package main.actors;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import main.models.Item;

public class Chairman {

	private static Chairman INSTANCE;
	
	private volatile Deque<Item> itemsQueue;
	private volatile Set<Recipient> currentRecipientsSet;
	
	private volatile ReentrantLock queueItemLock;
	private volatile ReentrantLock queueRecipientLock;
	
	private final int TIME_TO_END = 5000;
	private boolean isRegistrationOpen;
	
	protected Chairman() {
		itemsQueue = new ArrayDeque<Item>();
		currentRecipientsSet = new HashSet<>();
		queueItemLock = new ReentrantLock();
		queueRecipientLock = new ReentrantLock();
		isRegistrationOpen = false;
	}
	
	public static Chairman getInstance() {
		if(INSTANCE == null)
			INSTANCE = new Chairman();
		return INSTANCE;
	}
	
	public boolean addNewItem(Item i) {
		
		queueItemLock.lock();
		
		if(isItemQueueReady()) {
			itemsQueue.add(i);
			queueItemLock.unlock();
			return true;
		} else {
			queueItemLock.unlock();
			return false;
		}
		
	}
	
	public boolean isItemQueueReady() {
		return itemsQueue.size() < 10;
	}	

	public Set<Recipient> getcurrentRecipientsSet() {
		return currentRecipientsSet;
	}
	
	public void startAuction() {
		
		int emptyCounter = 0;
		while(emptyCounter != 10) {
			while(!itemsQueue.isEmpty()) {
				emptyCounter = 0;
				Item currentItem = itemsQueue.removeFirst();
				currentRecipientsSet.clear();
				registration(currentRecipientsSet);
				if(!currentRecipientsSet.isEmpty()) {
					Random rand = new Random();
					Recipient[] tempArray = new Recipient[currentRecipientsSet.size()];
					tempArray = currentRecipientsSet.toArray(tempArray);
					Recipient winner = tempArray[rand.nextInt(currentRecipientsSet.size())];
					winner.winNewItem(currentItem);
					System.out.println(winner.getName() + " won " + currentItem.getName());
					for(Recipient reci: currentRecipientsSet)
						reci.setInAuction(false);
				} else {
					System.out.println("There is no winner for " + currentItem.getName());
				}
				//notifyAll();
			}
			if(itemsQueue.isEmpty()) {
				emptyCounter++;
				try {
					Thread.sleep(TIME_TO_END/10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
				
		if(emptyCounter == 10)
			System.out.println("No auctions within 5 seconds. Closing the market");
		
		System.out.println("Chairman says good bye");
		MarketManager.getInstance().closeMarket();
		
	}

	private void registration(Set<Recipient> registeredRecipients) {
		setRegistrationOpen(true);
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setRegistrationOpen(false);
	}
	
	public boolean isRegistrationOpen() {
		return isRegistrationOpen && currentRecipientsSet.size() <= 10;
	}
	
	public void setRegistrationOpen(boolean b) {
		isRegistrationOpen = b;
	}
	
	public boolean register(String name) {
		
		if(!isRegistrationOpen()) 
			return false;
		System.out.println("Przed lockiem " + name);
		queueRecipientLock.lock();
		
		if(isRegistrationOpen()) {
			currentRecipientsSet.add(MarketManager.getInstance().getRecipientByName(name));
			MarketManager.getInstance().getRecipientByName(name).setInAuction(true);
			System.out.println("Registration " + name);
			queueRecipientLock.unlock();
			return true;
		} else {
			queueRecipientLock.unlock();
			return false;
		}
		
	}
	
	
}
