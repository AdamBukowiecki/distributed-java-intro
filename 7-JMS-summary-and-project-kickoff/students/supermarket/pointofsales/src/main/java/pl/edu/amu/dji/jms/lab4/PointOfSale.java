package pl.edu.amu.dji.jms.lab4;

import java.util.HashMap;
import java.util.Map;

import pl.edu.amu.dji.jms.lab4.messages.FullProductList;
import pl.edu.amu.dji.jms.lab4.messages.PriceChange;
import pl.edu.amu.dji.jms.lab4.services.AddPointOfSaleService;
import pl.edu.amu.dji.jms.lab4.services.ReportService;

public class PointOfSale {

	private final static PointOfSale INSTANCE = new PointOfSale();  
	
	private String name;
	private Map<String, Double> products;
	
	private ReportService reportService;
	private AddPointOfSaleService addService;	

	private PointOfSale() {
		products = new HashMap<>();
	}
	
	public void setAddService(AddPointOfSaleService addService) {
		this.addService = addService;
	}
	
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public static PointOfSale getInstance() {
		return INSTANCE;
	}
	
	public String getName() {
		return name;
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
	
	public void sendMessageToWarehouse() {
		addService.sendAddPointOfSale();
	}

	public void sell(String productName) {
		String message = name +  " sold " + productName + " for " + products.get(productName) + "."; 
		reportService.sendReport(message);		
	}

	public void setName(String n) {
		name = n;
	}
	
	@Override
	public String toString() {
		String result = new String();
		for(String name: products.keySet())
			result += name + " => " + products.get(name) + "\n" ;
		return result;
	}

}
