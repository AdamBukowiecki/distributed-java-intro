package pl.edu.amu.dji.jms.lab4;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import pl.edu.amu.dji.jms.lab4.services.FullProductListService;
import pl.edu.amu.dji.jms.lab4.services.PriceChangeService;
import pl.edu.amu.dji.jms.lab4.tools.WarehouseAppUI;

public class WarehouseApp {
	
	private final static String contextPath = "/context.xml"; 
	
    public static void main(String[] args) {
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext(contextPath);
    	Warehouse.getInstance().setFullProductListService( (FullProductListService) context.getBean("fullProductListService"));
    	Warehouse.getInstance().setPriceChangeService( (PriceChangeService) context.getBean("priceChangeService"));
    	
    	WarehouseAppUI ui = new WarehouseAppUI();
    	ui.start();
    	    	
    	((AbstractApplicationContext)context).close();
    	
    }
    
}
