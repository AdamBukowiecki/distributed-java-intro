package pl.edu.amu.dji.jms.lab4.models;

public class PointOfSale {

	private String id;

	public PointOfSale(String i) {
		id = i;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object arg) {
		if(!(arg instanceof PointOfSale))
			return false;
		else {
			PointOfSale argTemp = (PointOfSale) arg;
			return getId().equals(argTemp.getId());			
		}
	}
	
}
