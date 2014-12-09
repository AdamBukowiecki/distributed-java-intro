package pl.edu.amu.dji.jms.lab4;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import pl.edu.amu.dji.jms.lab4.tools.PointOfSaleAppUI;
import pl.edu.amu.dji.jms.lab4.services.AddPointOfSaleService;
import pl.edu.amu.dji.jms.lab4.services.ReportService;

public class PointOfSalesApp {
	
	private final static String contextPath = "/context.xml"; 
	
    public static void main(String[] args) {

    	PointOfSaleAppUI ui = new PointOfSaleAppUI();
    	ui.setPOSName();   	
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext(contextPath);
    	PointOfSale.getInstance().setReportService( (ReportService) context.getBean("reportService") );
    	PointOfSale.getInstance().setAddService( (AddPointOfSaleService) context.getBean("addPointOfSaleService") );
    	PointOfSale.getInstance().sendMessageToWarehouse();
    	
    	ui.start();
    	    	
    	((AbstractApplicationContext)context).close();
    	
    	
    }
}
