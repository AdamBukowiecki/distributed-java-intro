package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component("orderConverter")
public class OrderConverter implements MessageConverter {

	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		throw new UnsupportedOperationException();
	}

	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		Offer offer = new Offer();
		MapMessage map = (MapMessage) message;
		offer.setPrice(map.getDouble("price"));
		offer.setReplyTo(map.getJMSReplyTo());
		return offer;
	}

}
