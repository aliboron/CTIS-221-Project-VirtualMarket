package virtualMarket.customer;

import java.util.ArrayList;
import virtualMarket.order.*;

public class Customer {
	private int id;
	private String name, email, address;
	private ShoppingCart shoppingCart = new ShoppingCart(this);; // her musterinin bir alisveris sepeti var

	public Customer(int id, String name, String email, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public ArrayList<Order> getOrderHistory() { 
		System.out.println("getOrderHistory method is not fully implemented yet.");
		return null; // Simdilik null donduruyor
	};
	
	
	public String toFileString() {
		return String.format("%d;%s;%s;%s\n", id, name, email, address);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}
	
	@Override 
	public String toString() {
		return name;
	}
	
	public String displayInfo() {
		return String.format("ID: %d\nName: %s\nEmail: %s\nAddress: %s\n", id, name, email, address);
	}
	
	public String displayInfoUI() {
		return String.format(" ID: %d | Name: %s  |  Email: %s  |  Address: %s ", id, name, email, address);
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	
}
