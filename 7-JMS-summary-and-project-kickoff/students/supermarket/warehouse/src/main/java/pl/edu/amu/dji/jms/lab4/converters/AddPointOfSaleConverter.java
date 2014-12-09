package pl.edu.amu.dji.jms.lab4.converters;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import pl.edu.amu.dji.jms.lab4.messages.AddPointOfSale;

@Component("addPointOfSaleConverter")
public class AddPointOfSaleConverter implements MessageConverter {

	@Override
	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		throw new UnsupportedOperationException();
	}

	@Override
	public AddPointOfSale fromMessage(Message message) throws JMSException,
			MessageConversionException {
		
		Preconditions.checkArgument(message instanceof TextMessage);
		
		TextMessage textMessage = (TextMessage) message;
		
		return new AddPointOfSale(textMessage.getText());
	}

}
