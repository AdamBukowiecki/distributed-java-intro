package pl.edu.amu.dji.jms.lab4.converters;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import pl.edu.amu.dji.jms.lab4.messages.PriceChange;

import com.google.common.base.Preconditions;

@Component("priceChangeConverter")
public class PriceChangeConverter implements MessageConverter {

	@Override
	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		
		Preconditions.checkArgument(object instanceof PriceChange);
		
		PriceChange priceChange = (PriceChange) object;
		MapMessage message = session.createMapMessage();
        message.setDouble("price", priceChange.getPrice());
        message.setString("productName", priceChange.getProductName());
				
		return message;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		throw new UnsupportedOperationException();
	}

}
