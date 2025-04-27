package virtualMarket.items;

import java.util.Objects;

public class ShoppingItem {
	private String itemName;
	private int cartAmount = 0;
		
	
	public ShoppingItem(String itemName) {
		super();
		this.itemName = itemName;
	}
	
	public void increaseAmount() {
		cartAmount++;
	}

	public String getItemName() {
		return itemName;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingItem that = (ShoppingItem) o;
        return Objects.equals(this.itemName, that.getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.itemName);
    }

	
	@Override
	public String toString() {
		return itemName + " - Amount: " + cartAmount;
	}
}
