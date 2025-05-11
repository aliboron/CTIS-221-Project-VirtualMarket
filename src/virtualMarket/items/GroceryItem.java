package virtualMarket.items;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import virtualMarket.enums.*;
import virtualMarket.inventory.InventorySystem;

public class GroceryItem extends Item{
	private LocalDateTime expiryDate;
	private GroceryType type;
	
    public GroceryItem(String name, double price, int stock, LocalDateTime expiryDate, GroceryType type) {
    	super(name, price, stock);
    	this.expiryDate = expiryDate;
    	this.type = type;
    }
    
    @Override
    public String generateID(ArrayList<String> usedIDs) {
        Random random = new Random();
        String id;
        
        do {
            int firstDigit = random.nextInt(3) + ItemType.GROCERY.getIDStart();
            
            int remainingDigits = random.nextInt(1000);
            
            id = String.format("%d%03d", firstDigit, remainingDigits);
            
        } while (usedIDs.contains(id));
        this.id = id;
        return id;
    }

	@Override
	public void calculateTaxedPrice() {
		this.price = price * TaxByType.CLOTHING.getKdv();	
	}

	public GroceryType getGroceryType (){
		return type;
	}

	@Override
	public Item purchaseItem() {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}
	
	public String toFileString() {
		return String.format("%s,%s,%s,%f,%d,%s,%s\n", 
				"grc", 
				id,
				name, 
				price, 
				stock,
				expiryDate.toString(), 
				type.name());
	}
    
}