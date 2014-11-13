package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.transaction.annotation.Transactional;

public class OrderService implements MessageListener {

	@Transactional
    public void onMessage(Message message) {
		MapMessage mapMessage = (MapMessage)message;
		try {
			System.out.println(	"Quantity: " + mapMessage.getDouble("quantity") + " " + 
								"RetailerID: " + mapMessage.getString("retailerID"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
        //throw new UnsupportedOperationException();

    }
}
