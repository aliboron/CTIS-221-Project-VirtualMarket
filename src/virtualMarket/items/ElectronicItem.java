package virtualMarket.items;

import java.time.LocalDateTime;
import virtualMarket.enums.*;
public class ElectronicItem extends Item {

	private int warrantyPeriod;
	private ElectronicsType type;
	private ElectronicsBrand brand;
	private LocalDateTime warrantyStart;
	private LocalDateTime warrantyEnd;
	
	
	
	public ElectronicItem(int id, String name, double price, int amount, int warrantyPeriod,
			LocalDateTime warrantyStart, LocalDateTime warrantyEnd) {
		super(id, name, price, amount);
		this.warrantyPeriod = warrantyPeriod;
		this.warrantyStart = warrantyStart;
		this.warrantyEnd = warrantyEnd;
	}
	
	@Override
	public Item purchaseItem() {
		// TODO Auto-generated method stub
		return null;
	}

}
