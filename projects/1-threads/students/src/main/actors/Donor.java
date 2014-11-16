package main.actors;

import java.util.Random;

import main.models.Item;
import main.models.ItemsNameGenerator;

public class Donor {

	private String name;
	private Thread addingItems;
	private volatile boolean isRunning;
	
	public Donor(String n) {
		name = n;
		isRunning = true;
		addingItems = new Thread() {
			
			@Override
			public void run() {
				Random rand = new Random();
				Item item = null;
				while(isRunning) {
					try {
						item = new Item(ItemsNameGenerator.generateName());
						while(!Chairman.getInstance().addNewItem(item))
							Thread.sleep(5000);
						
						Thread.sleep(1000 * ((rand.nextInt() % 25) + 5));
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
			}
			
		};
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Donor)) {
			return false;
		} else {
			return name.equals(((Donor)obj).getName());
		}
	}

	public String getName() {
		return name;
	}
	
	public void stopAddingItems() {
		isRunning = false;
		addingItems.interrupt();
		try {
			addingItems.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + "  says good bye");
	}
	
	public void startAddingItems() {
		addingItems.start();
	}
	
}
