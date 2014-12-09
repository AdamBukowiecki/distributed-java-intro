package pl.edu.amu.dji.jms.lab4.messages;

public class AddPointOfSale {

	private String name;

	public AddPointOfSale(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
}
