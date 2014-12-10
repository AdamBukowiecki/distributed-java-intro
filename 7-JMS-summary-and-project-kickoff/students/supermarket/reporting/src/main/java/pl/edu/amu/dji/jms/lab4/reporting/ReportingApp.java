package pl.edu.amu.dji.jms.lab4.reporting;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import pl.edu.amu.dji.jms.lab4.reporting.tools.ReportingUI;

public class ReportingApp {
	
	private final static String contextPath = "/context.xml"; 
	
    public static void main(String[] args) {
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext(contextPath);
    	
    	ReportingUI ui = new ReportingUI();
    	ui.start();
    	    	
    	((AbstractApplicationContext)context).close();
    	
    	
    }
}
