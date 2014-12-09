package pl.edu.amu.dji.jms.lab4.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.amu.dji.jms.lab4.PointOfSale;
import pl.edu.amu.dji.jms.lab4.messages.PriceChange;
import pl.edu.amu.dji.jms.lab4.tools.PointOfSaleAppUI;

@Service("priceChangeService")
public class PriceChangeService {

	@Transactional
	public void priceChange(PriceChange pc) {
		PointOfSale.getInstance().updatePrice(pc);
		PointOfSaleAppUI.print("Price of " + pc.getProductName() + " has been changed to " + pc.getPrice());
	}
	
}
