package pl.edu.amu.dji.jms.lab4.services;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.amu.dji.jms.lab4.PointOfSale;
import pl.edu.amu.dji.jms.lab4.messages.AddPointOfSale;

@Service("addPointOfSaleService")
public class AddPointOfSaleService {

	@Autowired
	@Qualifier("addPointOfSaleJmsTemplate")
	private JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("addPointOfSaleQueue")
	private Destination addPointOfSaleQueue;
	
	@Transactional
	public void sendAddPointOfSale() {
		AddPointOfSale add = new AddPointOfSale(PointOfSale.getInstance().getName());
		jmsTemplate.convertAndSend(add);
	}
	
}
