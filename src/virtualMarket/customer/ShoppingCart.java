package virtualMarket.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList; 
import java.time.LocalDateTime; 
import virtualMarket.order.*;
import virtualMarket.items.*;
import virtualMarket.inventory.InventorySystem;

public class ShoppingCart {
	private Customer customer;
	private Map<String, Item> items;

	public ShoppingCart(Customer customer) {
		super();
		this.customer = customer;
		this.items = new HashMap<>();
	}

	public void addItem(Item inventoryItem) {
		if (inventoryItem != null && inventoryItem.getStock() > 0) {
			Item cartItem = items.get(inventoryItem.getId());
			if (cartItem != null) {
				cartItem.setStock(cartItem.getStock() + 1);
			} else {
				cartItem = inventoryItem.createCartCopy(); 
				items.put(inventoryItem.getId(), cartItem);
			}
			inventoryItem.setStock(inventoryItem.getStock() - 1); 
		}
	}

	public boolean reduceItemByOne(String itemId) {
		Item cartItem = items.get(itemId);
		if (cartItem != null) {
			Item inventoryItem = InventorySystem.searchItem(itemId);
			if (inventoryItem != null) {
				inventoryItem.setStock(inventoryItem.getStock() + 1);
				if (cartItem.getStock() > 1) {
					cartItem.setStock(cartItem.getStock() - 1);
				} else {
					items.remove(itemId);
				}
				return true;
			}
		}
		return false;
	}

	public boolean removeItemCompletely(String itemId) {
		Item cartItem = items.get(itemId);
		if (cartItem != null) {
			Item inventoryItem = InventorySystem.searchItem(itemId);
			if (inventoryItem != null) {
				inventoryItem.setStock(inventoryItem.getStock() + cartItem.getStock()); 
			}
			items.remove(itemId);
			return true;
		}
		return false;
	}

	public void removeItem(Item item) {
		if (item != null) {
			removeItemCompletely(item.getId());
		}
	}

	public List<Item> getItems() { 
		return new ArrayList<>(items.values());
	}

	public void clearCart() {
		items.clear();
	}

	public Order checkOut() {
		if (items.isEmpty()) {
			return null;
		}

		int orderId = Order.generateOrderID();
		
		ArrayList<Item> orderItems = new ArrayList<>(items.values());

		Order order = new Order(orderId, this.customer, orderItems, LocalDateTime.now());
		
		Order.addUsedOrderIDAndWrite(orderId);

		this.clearCart();

		return order;
	}

}