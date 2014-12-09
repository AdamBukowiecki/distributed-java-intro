package pl.edu.amu.dji.jms.lab4.messages;

import java.util.Map;

public class FullProductList {

	private Map<String, Double> list;
	
	public FullProductList(Map<String, Double> l) {
		list = l;
	}

	public Map<String, Double> getList() {
		return list;
	}

}
