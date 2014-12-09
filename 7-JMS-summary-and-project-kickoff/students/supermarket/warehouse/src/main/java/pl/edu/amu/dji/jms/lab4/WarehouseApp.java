package pl.edu.amu.dji.jms.lab4;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import pl.edu.amu.dji.jms.lab4.tools.WarehouseAppUI;

public class WarehouseApp {
	
	private final static String contextPath = "/context.xml"; 
	
    public static void main(String[] args) {
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext(contextPath);
    	
    	WarehouseAppUI ui = new WarehouseAppUI();
    	ui.start();
    	    	
    	((AbstractApplicationContext)context).close();
    	
    }
    
}
