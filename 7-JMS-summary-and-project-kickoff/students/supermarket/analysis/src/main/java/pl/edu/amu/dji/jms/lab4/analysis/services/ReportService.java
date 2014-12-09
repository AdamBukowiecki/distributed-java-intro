package pl.edu.amu.dji.jms.lab4.analysis.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.amu.dji.jms.lab4.analysis.tools.ReportingUI;
import pl.edu.amu.dji.jms.lab4.messages.Report;

@Service("reportService")
public class ReportService {

	@Transactional
	public void report(Report report) {
		ReportingUI.print(report.getText());
	}
	
}
