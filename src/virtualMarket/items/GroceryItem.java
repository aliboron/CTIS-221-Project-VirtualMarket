package virtualMarket.items;
import java.time.LocalDateTime;
import virtualMarket.enums.*;

public class GroceryItem extends Item{
	private LocalDateTime expiryDate;
	private GroceryType type;
	
    public GroceryItem(int id, String name, double price, int amount, LocalDateTime expiryDate) {
    	super(id, name, price, amount);
    	this.expiryDate = expiryDate;
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