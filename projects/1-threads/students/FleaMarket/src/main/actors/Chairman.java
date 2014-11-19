package main.actors;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import main.models.Item;
import main.threads.ChairmanThread;
import main.tools.ConfigurationTool;

public class Chairman {

	// Singleton
	private static Chairman INSTANCE;
	
	private volatile Deque<Item> itemsQueue;
	private Set<Recipient> inCurrentAuctionRecipientsSet;
	
	private volatile ReentrantLock queueItemLock;
	private volatile ReentrantLock queueRecipientLock;
	
	private boolean isRegistrationOpen;
	private ChairmanThread auctionThread;
	
	private final int ITEMS_MAX_SIZE = Integer.parseInt(ConfigurationTool.getParamter("ITEMS_MAX_SIZE"));//10;
	
	protected Chairman() {
		itemsQueue = new ArrayDeque<Item>(ITEMS_MAX_SIZE);
		inCurrentAuctionRecipientsSet = new HashSet<>();
		queueItemLock = new ReentrantLock();
		queueRecipientLock = new ReentrantLock();
		isRegistrationOpen = false;
		auctionThread = new ChairmanThread(this);
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
	
	public boolean registerRecipient(String name) {
		
		
		queueRecipientLock.lock();
		
		if(isRegistrationOpen()) {
			inCurrentAuctionRecipientsSet.add(MarketManager.getInstance().getRecipientByName(name));
			MarketManager.getInstance().getRecipientByName(name).register();
			System.out.println("Registration " + name);
			queueRecipientLock.unlock();
			return true;
		} else {
			queueRecipientLock.unlock();
			return false;
		}
		
	}
	
	public boolean isItemQueueReady() {
		return itemsQueue.size() < ITEMS_MAX_SIZE;
	}
	
	public Deque<Item> getItemQueue() {
		return itemsQueue;
	}

	public Set<Recipient> getinCurrentAuctionRecipientsSet() {
		return inCurrentAuctionRecipientsSet;
	}
	
	public void startAuction() {
		auctionThread.start();
	}

	public void stopAuction() {
		auctionThread.setIsRunning(false);
	}
	
	public boolean isRegistrationOpen() {
		return isRegistrationOpen && inCurrentAuctionRecipientsSet.size() <= 10;
	}
	
	public void setRegistrationOpen(boolean b) {
		isRegistrationOpen = b;
	}
	
	
	
	
}
