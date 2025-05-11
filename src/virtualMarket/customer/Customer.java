package virtualMarket.customer;

import java.util.ArrayList;
import virtualMarket.order.*;

public class Customer {
	private int id;
	private String name, email, address;

	public Customer(int id, String name, String email, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public ArrayList<Order> getOrderHistory() { //Waiting for file operations before implemented
		return null;
	};
	
	
	public String toFileString() {
		return String.format("%d,%s,%s,%s\n", id, name, email, address);
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

}
