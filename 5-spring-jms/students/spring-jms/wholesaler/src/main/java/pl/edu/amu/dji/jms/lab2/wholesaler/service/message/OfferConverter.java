package pl.edu.amu.dji.jms.lab2.wholesaler.service.message;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

@Component("offerConverter")
public class OfferConverter implements MessageConverter {

	public Object fromMessage(Message arg0) throws JMSException,
			MessageConversionException {
		throw new UnsupportedOperationException();
	}

	public Message toMessage(Object arg0, Session arg1) throws JMSException,
			MessageConversionException {
		Preconditions.checkArgument(arg0 instanceof Offer);

		MapMessage map = arg1.createMapMessage();
		Offer offer = (Offer) arg0;
		map.setDouble("price", offer.getPrice());
		map.setJMSReplyTo(offer.getReplyTo());
		return map;
		
	}

	
	
}
