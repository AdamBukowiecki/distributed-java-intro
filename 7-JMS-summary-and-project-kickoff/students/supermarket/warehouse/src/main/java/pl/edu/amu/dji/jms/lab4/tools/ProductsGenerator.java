package pl.edu.amu.dji.jms.lab4.tools;

import java.util.Map;

public class ProductsGenerator {

	private String[] names = {"Car", "Fridge", "Cat", "Banana", "Bike", "Tea", "Book", "Tank", "Submarine" };
	private Double[] prices = {9999.99, 149.95, 2.50, 4.99, 100.00, 0.99, 5.60, 1.45, 129.99};
	
	public void generateProducts(Map<String, Double> target) {
		for(int i = 0; i < (names.length < prices.length ? names.length : prices.length ); i++)
			target.put(names[i], prices[i]);
	}
	
}
