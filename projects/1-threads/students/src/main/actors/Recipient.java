package main.actors;

import java.util.ArrayList;
import java.util.List;

import main.models.Item;

public class Recipient {

	private String name;
	private Thread buyingItems;
	private volatile List<Item> collectedItems;
	private volatile boolean inAuction;
	private volatile boolean wonLast;
	private volatile boolean isRunning;
	
	public boolean isInAuction() {
		return inAuction;
	}


	public void setInAuction(boolean inAuction) {
		this.inAuction = inAuction;
	}


	public Recipient(String n) {
		name = n;
		inAuction = false;
		isRunning = true;
		collectedItems = new ArrayList<>();
		
		buyingItems = new Thread() {
					
			@Override
			public void run() {
				
				while(isRunning) {
					
					if(Chairman.getInstance().isRegistrationOpen()) {
						Chairman.getInstance().register(name);	
					}
					
					while(inAuction);
					
					if(wonLast) {
						wonLast = false;
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							//e.printStackTrace();
						}
						
					}
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						//.printStackTrace();
					}
					
					
				}
				
				
			}
			
		};
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Recipient)) {
			return false;
		} else {
			return name.equals(((Recipient)obj).getName());
		}
	}

	public void stopBuying() {
		isRunning = false;
		buyingItems.interrupt();
		try {
			buyingItems.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String wonItems = new String();
		for(Item item: collectedItems)
			wonItems += " '" + item.getName() + "'";
		System.out.println(name + " says good bye leaving with items" + wonItems);
	}
	
	public void startBuying() {
		buyingItems.start();
	}

	public String getName() {
		return name;
	}
	
	public void winNewItem(Item i) {
		collectedItems.add(i);
		wonLast = true;
	}
	
}
