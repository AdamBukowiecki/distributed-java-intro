package pl.edu.amu.dji.jms.lab4.reporting.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.amu.dji.jms.lab4.messages.Report;
import pl.edu.amu.dji.jms.lab4.reporting.tools.ReportingUI;

@Service("reportService")
public class ReportService {

	@Transactional
	public void report(Report report) {
		ReportingUI.print(report.getText());
	}
	
}
