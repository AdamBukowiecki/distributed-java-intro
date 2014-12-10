package pl.edu.amu.dji.jms.lab4.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pl.edu.amu.dji.jms.lab4.PointOfSale;

public class PointOfSaleAppUI {

		private final String QUIT = "quit";
		private final String SELL = "sell";
		private final String PRINT_PRODUCTS = "print";
		
		private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	
		public void start() {
			
			prepareUI();
			
			String line = new String();
			boolean uiRunning = true;
			
			while( uiRunning ) {
		    	try {
		    		printPrompt();
					line = bufferedReader.readLine();
					switch(line) {
					case SELL:
						String productName = getProductName();
						PointOfSale.getInstance().sell(productName);
						break;
						
					case PRINT_PRODUCTS:
						System.out.println("Available products with prices:");
						System.out.println(PointOfSale.getInstance().toString());
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
		
		private void prepareUI() {
			System.out.println(" ------------------ Point of Sale ------------------");
			System.out.println("To sell the product, enter '" + SELL  + "'.");
			System.out.println("To print all products, enter '" + PRINT_PRODUCTS  + "'.");
			System.out.println("To exit, enter '" + QUIT  + "'.");
		}
		
		public static void print(final String message) {
			System.out.println(message);
		}
		
		private String getProductName() throws IOException {
			System.out.print("Product name: ");
			String name = new String();
			name = bufferedReader.readLine();
			if(!PointOfSale.getInstance().getProducts().containsKey(name))
				throw new IOException("There is no such product!");
			return name;
		}

		public void setPOSName() {
			System.out.print("POS name: ");
			String name = new String();
			try {
				name = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			PointOfSale.getInstance().setName(name);
		}
	
		private void printPrompt() {
			System.out.print("$ ");
			System.out.print(" ");
		}
	
}
