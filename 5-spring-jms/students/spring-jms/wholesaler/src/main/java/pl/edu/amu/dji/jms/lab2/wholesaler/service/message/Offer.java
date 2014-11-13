package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

import javax.jms.Destination;

public class Offer {

	private double price;
	private Destination replyTo;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Destination getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(Destination replyTo) {
		this.replyTo = replyTo;
	}
	
}
