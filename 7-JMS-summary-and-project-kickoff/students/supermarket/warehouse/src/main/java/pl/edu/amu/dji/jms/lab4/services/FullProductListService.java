package pl.edu.amu.dji.jms.lab4.services;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.amu.dji.jms.lab4.Warehouse;
import pl.edu.amu.dji.jms.lab4.messages.FullProductList;

@Service("fullProductListService")
public class FullProductListService {
	
	@Autowired
	@Qualifier("fullProductListJmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("fullProductListTopic")
    private Destination fullProductListTopic;

	@Transactional
    public void sendFullProductList() {
    	FullProductList fullProductList = new FullProductList(Warehouse.getInstance().getProducts());
        jmsTemplate.convertAndSend(fullProductList);
        System.out.println("sendnalem fulla");
    }
    
}
