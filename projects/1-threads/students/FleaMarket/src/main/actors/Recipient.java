package main.actors;

import java.util.ArrayList;
import java.util.List;

import main.models.Item;
import main.threads.RecipientThread;

public class Recipient {

	private String name;
	private RecipientThread buyingItems;
	private volatile List<Item> collectedItems;
	
	public Recipient(String n) {
		name = n;
		collectedItems = new ArrayList<>();
		buyingItems = new RecipientThread(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Recipient)) {
			return false;
		} else {
			return name.equals(((Recipient)obj).getName());
		}
	}

	public void sayGoodBye() {
		String wonItems = new String();
		for(Item item: collectedItems)
			wonItems += " '" + item.getName() + "'";
		System.out.println(name + " says good bye leaving with items" + wonItems);
	}
	
	public void sayWon() {
		System.out.println(getName() + " won " + collectedItems.get(collectedItems.size() - 1).getName() );
	}
	
	public void stopBuying() {
		buyingItems.setRunning(false);
	}
	
	public void startBuying() {
		buyingItems.start();
	}

	public String getName() {
		return name;
	}
	
	public void winNewItem(Item i) {
		buyingItems.setAuctionResult(true);
		collectedItems.add(i);
	}
	
	public void register() {
		buyingItems.setInAuction(true);
	}
	
	public void auctionIsFinished() {
		buyingItems.setInAuction(false);
	}
}
