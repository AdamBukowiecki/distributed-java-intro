package pl.edu.amu.dji.jms.lab4.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.amu.dji.jms.lab4.Warehouse;
import pl.edu.amu.dji.jms.lab4.messages.AddPointOfSale;
import pl.edu.amu.dji.jms.lab4.tools.WarehouseAppUI;

@Service("addPointOfSaleService")
public class AddPointOfSaleService {

	@Transactional
	public void addPointOfSale(AddPointOfSale addPoint) {
		Warehouse.getInstance().addNewPointOfSale(addPoint.getName());
        WarehouseAppUI.print("New POS (" + addPoint.getName() +") has been registered.");
	}

}
