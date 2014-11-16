package main.actors;

import java.util.HashSet;
import java.util.Set;

public class MarketManager {

	private static MarketManager INSTANCE;
	
	private Set<Recipient> recipients;
	private Set<Donor> donors;
	private boolean isMarketOpen;
	
	public Set<Recipient> getRecipients() {
		return recipients;
	}

	public Set<Donor> getDonors() {
		return donors;
	}

	public boolean isMarketOpen() {
		return isMarketOpen;
	}

	protected MarketManager() {
		recipients = new HashSet<>();
		donors = new HashSet<>();
		isMarketOpen = false;
	}
	
	public static MarketManager getInstance() {
		if(INSTANCE == null)
			INSTANCE = new MarketManager();
		return INSTANCE;
	}
	
	public void openMarket() {
		for(Donor donor: donors) donor.startAddingItems();
		for(Recipient recipient: recipients) recipient.startBuying();
		Chairman.getInstance().startAuction();
		isMarketOpen = true;
	}
	
	public void addNewRecipient(Recipient r) {
		recipients.add(r);
	}
	
	public void addNewDonor(Donor r) {
		donors.add(r);
	}
	
	public void closeMarket() {
		for(Donor donor: donors) donor.stopAddingItems();
		for(Recipient recipient: recipients) recipient.stopBuying();
		isMarketOpen = false;
	}
	
	public Recipient getRecipientByName(String name) {
		for(Recipient recipient: recipients) {
			if(recipient.getName().equals(name))
				return recipient;
		}
		return null;
	}
	
}
