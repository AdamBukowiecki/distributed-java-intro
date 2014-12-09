package pl.edu.amu.dji.jms.lab4.converters;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import pl.edu.amu.dji.jms.lab4.messages.AddPointOfSale;

import com.google.common.base.Preconditions;

@Component("addPointOfSaleConverter")
public class AddPointOfSaleConverter implements MessageConverter {

	@Override
	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		
		Preconditions.checkArgument(object instanceof AddPointOfSale);
		
		AddPointOfSale addPointOfSale = (AddPointOfSale) object;
		TextMessage message = session.createTextMessage();
		message.setText(addPointOfSale.getName());
				
		return message;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		throw new UnsupportedOperationException();
	}

}
