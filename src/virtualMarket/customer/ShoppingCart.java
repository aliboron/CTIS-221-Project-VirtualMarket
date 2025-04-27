package virtualMarket.customer;

import java.util.ArrayList;
import virtualMarket.order.*;
import virtualMarket.items.*;

public class ShoppingCart {
	private Customer customer;
	private ArrayList<Item> items;

	public ShoppingCart(Customer customer) {
		super();
		this.customer = customer;
	};

	public void addItem(Item item) {
		
	};

	public void removeItem(Item item) {
		
	};

	public Order checkOut() {
		return null;
	};

}