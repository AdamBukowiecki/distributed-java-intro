package pl.edu.amu.dji.jms.lab4;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import pl.edu.amu.dji.jms.lab4.tools.PointOfSaleAppUI;

public class PointOfSalesApp {
	
	private final static String contextPath = "/context.xml"; 
	
    public static void main(String[] args) {
    	

    	ApplicationContext context = new ClassPathXmlApplicationContext(contextPath);
    	
    	PointOfSaleAppUI ui = new PointOfSaleAppUI();
    	ui.start();
    	    	
    	((AbstractApplicationContext)context).close();
    	
    	
    }
}
