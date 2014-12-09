package pl.edu.amu.dji.jms.lab4.converters;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import pl.edu.amu.dji.jms.lab4.models.FullProductList;

import com.google.common.base.Preconditions;

@Component("fullProductListConverter")
public class FullProductListConverter implements MessageConverter {

	@Override
	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		
		Preconditions.checkArgument(object instanceof FullProductList);
		
		FullProductList fullProductList = (FullProductList) object;
		MapMessage message = session.createMapMessage();
		for(String name: fullProductList.getList().keySet())
			message.setDouble(name, fullProductList.getList().get(name));
        
		return message;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		throw new UnsupportedOperationException();
	}

}
