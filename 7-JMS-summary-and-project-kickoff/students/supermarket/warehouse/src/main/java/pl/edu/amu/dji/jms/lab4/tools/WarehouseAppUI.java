package pl.edu.amu.dji.jms.lab4.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pl.edu.amu.dji.jms.lab4.Warehouse;

public class WarehouseAppUI {

	private final String QUIT = "quit";
	private final String PRICE_CHANGE = "pc";
	private final String SEND_PRODUCT_LIST = "spl";
	
	private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public void start() {
		
		prepareUI();
		
		String line = new String();
		boolean uiRunning = true;
		
		while( uiRunning ) {
	    	try {
				line = bufferedReader.readLine();
				switch(line) {
				case PRICE_CHANGE:
					String name = getProductNameToNewPrice();
					Double newPrice = getNewPrice(name);
					Warehouse.getInstance().changePriceOf(name, newPrice);
					Warehouse.getInstance().sendPriceChangeMessage(name, newPrice);
					break;
					
				case SEND_PRODUCT_LIST:
					Warehouse.getInstance().sendFullProductsMessage();
					break;
					
				case QUIT:
					bufferedReader.close();
					uiRunning = false;
					break;
					
				default:
					System.out.println("Unknown operation.");
					break;
				}
						
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
	    	
		}
		
	}
	
	private Double getNewPrice(final String name) throws IOException {
		Double currentPrice = Warehouse.getInstance().getProducts().get(name);
		Double newPrice = new Double(0);
		System.out.print("New price (current is: " + currentPrice + "): ");
		try {
			newPrice =  Double.valueOf(bufferedReader.readLine());
		} catch(IOException e) {
			throw new IOException("Wrong price syntax!");
		}
		return newPrice;
	}
	
	private String getProductNameToNewPrice() throws IOException {
		System.out.print("Products name: ");
		String name = new String();
		name = bufferedReader.readLine();
		if(!Warehouse.getInstance().getProducts().containsKey(name))
			throw new IOException("There is no such product!");
		return name;
	}
	
	private void prepareUI() {
		System.out.println(" ------------------ WarehouseApp ------------------");
		System.out.println("To change a price, enter '" + PRICE_CHANGE  + "'.");
		System.out.println("To send full product list, enter '" + SEND_PRODUCT_LIST  + "'.");
		System.out.println("To exit, enter '" + QUIT  + "'.");
	}
	
	public static void print(final String message) {
		System.out.println(message);
	}
	
}
