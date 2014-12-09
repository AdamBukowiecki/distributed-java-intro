package pl.edu.amu.dji.jms.lab4;

import java.util.HashMap;
import java.util.Map;

import pl.edu.amu.dji.jms.lab4.messages.FullProductList;
import pl.edu.amu.dji.jms.lab4.messages.PriceChange;
import pl.edu.amu.dji.jms.lab4.services.ReportService;

public class PointOfSale {

	private final static PointOfSale INSTANCE = new PointOfSale();  
	
	private String id;
	private Map<String, Double> products;
	
	private ReportService reportService;

	private PointOfSale() {
		id = new String();
		products = new HashMap<>();
		reportService = new ReportService();
	}
	
	public static PointOfSale getInstance() {
		return INSTANCE;
	}
	
	public String getId() {
		return id;
	}

	public Map<String, Double> getProducts() {
		return products;
	}

	public void updateProductList(FullProductList fpl) {
		products = fpl.getList();		
	}

	public void updatePrice(PriceChange pc) {
		products.put(pc.getProductName(), pc.getPrice());		
	}

	public void sell(String productName) {
		String message = productName + " sold for " + products.get(productName) + "."; 
		reportService.sendReport(message);		
	}

}
