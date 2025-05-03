package virtualMarket.items;

import java.util.ArrayList;

import virtualMarket.interfaces.DiscountableInterface;

public abstract class Item implements DiscountableInterface {

    protected String id;
    protected String name;
    protected double price;
    protected int stock;

    public Item(String name, double price, int stock) {
        this.name   = name;
        this.price  = price;
        this.stock = stock;
    }

    public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	public abstract String generateID(ArrayList<String> UsedIDs);
	
	
    @Override
	public double calculateDiscount(double discountPercentage) {
    	return Math.round((price - (price * discountPercentage)) * 100.0) / 100.0;
	}

	public abstract Item purchaseItem();
	

	public abstract void calculateTaxedPrice();
	
	public abstract String toFileString();

    public String toString() {
        return String.format("[%s] %s | %.2f ₺ | stock: %d", id, name, price, stock);
    }

	public static boolean isValidItemID(String id) {
		if (id == null || id.length() != 4) {
			return false;
		}
		
		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return false;
		}
		
		char firstDigit = id.charAt(0);
		if (firstDigit < '1' || firstDigit > '9') {
			return false;
		}
		
		return true;
	}

	public static String getItemTypeFromID(String id) {
		if (!isValidItemID(id)) {
			return "Unknown";
		}
		
		char firstDigit = id.charAt(0);
		if (firstDigit >= '1' && firstDigit <= '3') {
			return "Grocery";
		} else if (firstDigit >= '4' && firstDigit <= '6') {
			return "Clothing";
		} else if (firstDigit >= '7' && firstDigit <= '9') {
			return "Electronics";
		}
		
		return "Unknown";
	}
}