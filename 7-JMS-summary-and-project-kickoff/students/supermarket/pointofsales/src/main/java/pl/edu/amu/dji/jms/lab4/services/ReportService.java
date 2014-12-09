package pl.edu.amu.dji.jms.lab4.services;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.amu.dji.jms.lab4.messages.Report;

@Service("reportService")
public class ReportService {
	
	@Autowired
	@Qualifier("reportingJmsTemplate")
	private JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("reportingTopic")
	private Destination reportingTopic;
	
	@Transactional
	public void sendReport(String saleMessage) {
		Report report = new Report(saleMessage);
		jmsTemplate.convertAndSend(report);
	}
	
}
