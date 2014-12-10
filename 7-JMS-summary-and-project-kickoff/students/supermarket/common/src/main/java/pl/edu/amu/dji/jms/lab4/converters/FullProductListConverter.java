package pl.edu.amu.dji.jms.lab4.converters;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import pl.edu.amu.dji.jms.lab4.messages.FullProductList;

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
	public FullProductList fromMessage(Message message) throws JMSException,
			MessageConversionException {
		
		Preconditions.checkArgument(message instanceof MapMessage);
		
		Map<String, Double> productsList = new HashMap<>();
		MapMessage mapMessage = (MapMessage) message;
		
		Enumeration<String> keyEnum = mapMessage.getMapNames();
		
		for(String name: Collections.list(keyEnum))
			productsList.put(name, mapMessage.getDouble(name));		
		
		return new FullProductList(productsList);
	}

}
