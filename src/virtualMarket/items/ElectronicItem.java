package virtualMarket.items;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import virtualMarket.enums.*;
public class ElectronicItem extends Item {

	private int warrantyPeriod;
	private ElectronicsType type;
	private ElectronicsBrand brand;
	private LocalDateTime warrantyStart;
	private LocalDateTime warrantyEnd;
	
	
	
	public ElectronicItem(String name, double price, int amount, int warrantyPeriod,
			ElectronicsType type, ElectronicsBrand brand) {
		super(name, price, amount);
		this.warrantyPeriod = warrantyPeriod;
		this.type = type;
		this.brand = brand;
	}
	
	@Override
    public String generateID(ArrayList<String> usedIDs) {
        Random random = new Random();
        String id;
        
        do {
            int firstDigit = random.nextInt(3) + ItemType.ELECTRONIC.getIDStart();
            
            int remainingDigits = random.nextInt(1000);
            
            id = String.format("%d%03d", firstDigit, remainingDigits);
            
        } while (usedIDs.contains(id));
        usedIDs.add(id);
        return id;
    }
	
	@Override
	public void calculateTaxedPrice() {
		this.price = price * TaxByType.ELECTRONICS.getKdv();	
	}

	
	@Override
	public Item purchaseItem() {
		if (this.amount <= 0)
			return null;
		this.amount--;
		warrantyStart = LocalDateTime.now();
		warrantyEnd = warrantyStart.plusYears(warrantyPeriod);
		
		return this;
	}

}
