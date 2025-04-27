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

	public void placeOrder(Order order) {
	};

	public ArrayList<Order> getOrderHistory() {
		return null;
	};

}
