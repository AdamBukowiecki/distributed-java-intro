package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

public class Order {

	private int quantity;
	private String retailerID;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getRetailerID() {
		return retailerID;
	}
	public void setRetailerID(String retailerID) {
		this.retailerID = retailerID;
	}
	
}
