package pl.edu.amu.dji.jms.lab4.converters;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import pl.edu.amu.dji.jms.lab4.messages.Report;

import com.google.common.base.Preconditions;

@Component("reportConverter")
public class ReportConverter implements MessageConverter {

	@Override
	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		
		Preconditions.checkArgument(object instanceof Report);
		
		Report report = (Report) object;
		TextMessage message = session.createTextMessage();
		message.setText(report.getText());
		
		return message;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		throw new UnsupportedOperationException();
	}

	
	
}
