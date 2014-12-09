package pl.edu.amu.dji.jms.lab4.converters;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
		throw new UnsupportedOperationException();
	}

	@Override
	public FullProductList fromMessage(Message message) throws JMSException,
			MessageConversionException {
		
		Preconditions.checkArgument(message instanceof MapMessage);
		
		Map<String, Double> productsList = new HashMap<>();
		MapMessage mapMessage = (MapMessage) message;
		Set<String> namesSet = new TreeSet<>();
		
		while(mapMessage.getMapNames().hasMoreElements())
			namesSet.add( (String) mapMessage.getMapNames().nextElement() );
		
		for(String name: namesSet)
			productsList.put(name, mapMessage.getDouble(name));		
		
		return new FullProductList(productsList);
	}

}
