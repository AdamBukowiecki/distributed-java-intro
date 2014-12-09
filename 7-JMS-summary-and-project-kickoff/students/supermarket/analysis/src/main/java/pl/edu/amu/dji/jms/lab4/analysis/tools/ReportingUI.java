package pl.edu.amu.dji.jms.lab4.analysis.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReportingUI {

	private final String QUIT = "quit";
	
	private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public void start() {
		
		prepareUI();
		
		String line = new String();
		boolean uiRunning = true;
		
		while( uiRunning ) {
	    	try {
				line = bufferedReader.readLine();
				switch(line) {
				
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
		System.out.println(" ------------------ Reporting System ------------------");
		System.out.println("To exit, enter '" + QUIT  + "'.");
	}
	
	public static void print(final String message) {
		System.out.println(message);
	}
	
}
