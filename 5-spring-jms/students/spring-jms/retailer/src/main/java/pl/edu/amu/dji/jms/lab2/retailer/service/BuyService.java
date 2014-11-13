package pl.edu.amu.dji.jms.lab2.retailer.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

public class BuyService implements MessageListener {

    private JmsTemplate jmsTemplate;

    private Double maxPrice;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void onMessage(Message message) {
    	MapMessage mapMessage = (MapMessage) message;
    	try {
			Double price = mapMessage.getDouble("price");
			if(maxPrice.compareTo(price)==1) {
				MessageCreator creator = new MessageCreator() {

					public Message createMessage(Session arg0)
							throws JMSException {
						MapMessage mapMessage = arg0.createMapMessage();
						mapMessage.setString("retailerID", getClass().getName());
						mapMessage.setDouble("quantity", 9.5);
						return null;
					}
					
				};
				jmsTemplate.send(mapMessage.getJMSReplyTo(), creator);
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
