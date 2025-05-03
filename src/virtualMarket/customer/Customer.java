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

	public void placeOrder(Order order) { //Need to implement order
		
	};

	public ArrayList<Order> getOrderHistory() { //Waiting for file operations before implemented
		return null;
	};
	
	
	public String toFileString() {
		return String.format(""
	}


}
