package pl.edu.amu.dji.jms.lab4.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.amu.dji.jms.lab4.PointOfSale;
import pl.edu.amu.dji.jms.lab4.messages.FullProductList;
import pl.edu.amu.dji.jms.lab4.tools.PointOfSaleAppUI;

@Service("fullProductListService")
public class FullProductListService {

	@Transactional
	public void fullProductList(FullProductList fpl) {
		PointOfSale.getInstance().updateProductList(fpl);
		PointOfSaleAppUI.print("FullProductList has been brought.");
	}
	
}
