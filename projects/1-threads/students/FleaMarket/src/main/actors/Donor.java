package main.actors;

import main.threads.DonorThread;

public class Donor {

	private String name;
	private DonorThread addingItems;
	
	public Donor(String n) {
		name = n;
		addingItems = new DonorThread(this);		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Donor)) {
			return false;
		} else {
			return name.equals(((Donor)obj).getName());
		}
	}

	public void sayGoodBye() {
		System.out.println(name + "  says good bye");
	}
	
	public String getName() {
		return name;
	}
	
	public void stopAddingItems() {
		addingItems.setRunning(false);
	}
	
	public void startAddingItems() {
		addingItems.start();
	}
	
}
