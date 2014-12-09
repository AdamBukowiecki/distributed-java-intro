package pl.edu.amu.dji.jms.lab4.messages;

public class PriceChange {

	private String productName;
	private Double price;
	
	public PriceChange(String pn, Double pr) {
		productName = pn;
		price = pr;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public Double getPrice() {
		return price;
	}
	
}
