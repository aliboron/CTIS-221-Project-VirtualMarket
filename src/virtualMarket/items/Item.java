package virtualMarket.items;

import java.util.ArrayList;

import virtualMarket.interfaces.DiscountableInterface;

public abstract class Item implements DiscountableInterface {

    protected String id;
    protected String name;
    protected double price;
    protected int amount;

    public Item(String name, double price, int amount) {
        this.name   = name;
        this.price  = price;
        this.amount = amount;
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

	public int getAmount() {
		return amount;
	}
	
	public abstract String generateID(ArrayList<String> UsedIDs);
	
	
    @Override
	public double calculateDiscount(double discountPercentage) {
    	return Math.round((price - (price * discountPercentage)) * 100.0) / 100.0;
	}

	public abstract Item purchaseItem();
	
	public abstract void calculateTaxedPrice();

    public String toString() {
        return String.format("[%d] %s | %.2f ₺ | stock: %d test", id, name, price, amount);
    }
}