package pl.edu.amu.dji.jms.lab4.services;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.amu.dji.jms.lab4.messages.PriceChange;

@Service("priceChangeService")
public class PriceChangeService {

	@Autowired
	@Qualifier("priceChangeJMSTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("priceChangeTopic")
    private Destination priceTopic;
    
    @Transactional
    public void sendPriceChange(final String productName, final Double price) {
    	PriceChange priceChange = new PriceChange(productName, price);
        jmsTemplate.convertAndSend(priceChange);
    }
}
