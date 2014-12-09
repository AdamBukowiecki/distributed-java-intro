package pl.edu.amu.dji.jms.lab4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.edu.amu.dji.jms.lab4.models.PointOfSale;
import pl.edu.amu.dji.jms.lab4.services.FullProductListService;
import pl.edu.amu.dji.jms.lab4.services.PriceChangeService;
import pl.edu.amu.dji.jms.lab4.tools.ProductsGenerator;

public class Warehouse {

	private static final Warehouse INSTANCE = new Warehouse();
	
	private Map<String, Double> products;
	private Set<PointOfSale> pointsOfSale;
	
	private PriceChangeService priceChangeService;
	private FullProductListService fullProductListService;
	
	private Warehouse() {
		products = new HashMap<>();	
		pointsOfSale = new HashSet<>();
		
		generateProducts();
		
	}
	
	public static Warehouse getInstance() {
		return INSTANCE;		
	}
	
	public Map<String, Double> getProducts() {
		return products;
	}
	
	public Set<PointOfSale> getPointsOfSale() {
		return pointsOfSale;
	}

	public void changePriceOf(String name, double newPrice) {
		if(products.containsKey(name))
			products.put(name, newPrice);
	}
	
	public void sendPriceChangeMessage(final String productName, final Double price) {
		priceChangeService.sendPriceChange(productName, price);
	}
	
	public void sendFullProductsMessage() {
		fullProductListService.sendFullProductList();
	}
	
	public void addNewPointOfSale(String name) {
		pointsOfSale.add(new PointOfSale(name));
	}
	
	@Override
	public String toString() {
		String result = new String();
		for(String name: products.keySet())
			result += name + " => " + products.get(name) + "\n" ;
		return result;
	}
	
	private void generateProducts() {
		ProductsGenerator generator = new ProductsGenerator();
		generator.generateProducts(products);
	}
	
	public void setPriceChangeService(PriceChangeService priceChangeService) {
		this.priceChangeService = priceChangeService;
	}

	public void setFullProductListService(
			FullProductListService fullProductListService) {
		this.fullProductListService = fullProductListService;
	}

	
}
