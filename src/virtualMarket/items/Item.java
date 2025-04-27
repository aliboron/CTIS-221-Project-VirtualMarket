package virtualMarket.items;

import virtualMarket.interfaces.DiscountableInterface;

public abstract class Item implements DiscountableInterface {

    protected int id;
    protected String name;
    protected double price;
    protected int amount;

    public Item(int id, String name, double price, int amount) {
        this.id     = id;
        this.name   = name;
        this.price  = price;
        this.amount = amount;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
    @Override
	public double calculateDiscount(double discountPercentage) {
    	return Math.round((price - (price * discountPercentage)) * 100.0) / 100.0;
	}

	public abstract Item purchaseItem();

    public String toString() {
        return String.format("[%d] %s | %.2f ₺ | stock: %d test", id, name, price, amount);
    }
}