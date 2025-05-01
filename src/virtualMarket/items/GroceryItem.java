package virtualMarket.items;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import virtualMarket.enums.*;

public class GroceryItem extends Item{
	private LocalDateTime expiryDate;
	private GroceryType type;
	
    public GroceryItem(String name, double price, int amount, LocalDateTime expiryDate, GroceryType type) {
    	super(name, price, amount);
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
        usedIDs.add(id);
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
    
}