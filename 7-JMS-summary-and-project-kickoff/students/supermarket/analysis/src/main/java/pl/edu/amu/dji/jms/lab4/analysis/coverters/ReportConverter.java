package pl.edu.amu.dji.jms.lab4.analysis.coverters;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import pl.edu.amu.dji.jms.lab4.messages.Report;

@Component("reportConverter")
public class ReportConverter implements MessageConverter {

	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {
		throw new UnsupportedOperationException();
	}

	public Report fromMessage(Message message) throws JMSException,
			MessageConversionException {
		
		Preconditions.checkArgument(message instanceof TextMessage);
		
		TextMessage textMessage = (TextMessage) message;
				
		return new Report(textMessage.getText());
	}

}
