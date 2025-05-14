package virtualMarket.items;

import java.util.ArrayList;
import java.util.Objects;

import virtualMarket.interfaces.*;


public abstract class Item implements DiscountableInterface, Comparable<Item>, Taxable{

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
	
	public void setStock(int stock) {
		this.stock = stock;
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
	

	public abstract Item createCartCopy(); 
	

	public abstract String toFileString();


    public String toString() {
        return String.format("[%s] %s | %.2f ₺ | stock: %d", id, name, price, stock);
    }

    

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price, stock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && stock == other.stock;
	}
	
	
	//Comparison based on ItemID
	@Override
	public int compareTo(Item o) {
		return this.id.compareTo(o.getId());
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

	public abstract String getItemType();

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
			return "Electronic";
		}
		
		return "Invalid";
	}
}