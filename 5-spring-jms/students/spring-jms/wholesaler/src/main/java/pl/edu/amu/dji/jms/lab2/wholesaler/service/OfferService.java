package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

public class OfferService {

    private JmsTemplate jmsTemplate;

    private Destination offerTopic;

    private Destination orderQueue;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setOfferTopic(Destination offerTopic) {
        this.offerTopic = offerTopic;
    }

    public void setOrderQueue(Destination orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Transactional
    public void sendOffer(final Double price) {
    	    	
    	MessageCreator creator = new MessageCreator() {

			public Message createMessage(Session arg0) throws JMSException {
				MapMessage mapMessage = arg0.createMapMessage();
				mapMessage.setDouble("price", price);
				mapMessage.setJMSReplyTo(orderQueue);
				return mapMessage;
			}
    		
    	};
    	
    	jmsTemplate.send(offerTopic, creator);
    	
        //throw new UnsupportedOperationException();
        
    }
}
