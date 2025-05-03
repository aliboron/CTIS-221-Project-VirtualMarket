package virtualMarket.order;

import virtualMarket.items.*;
import virtualMarket.enums.*;
import java.util.ArrayList;
import virtualMarket.customer.Customer;
import java.time.LocalDateTime;

public class Order {

	private int id;
	private Customer customer;
	private ArrayList<Item> itemList = new ArrayList<>();
	private double totalAmount = 0;
	private LocalDateTime orderDate;

	public Order(int id, Customer customer, ArrayList<Item> itemList, LocalDateTime orderDate) {
		super();
		this.id = id;
		this.customer = customer;
		this.itemList = itemList;
		this.orderDate = orderDate;
		this.totalAmount = calculateTotal();
	}

	public double calculateTotal() {

		double totalPrice = 0;

		for (Item i : itemList) {
			totalPrice += i.getStock() * i.getPrice();

		}

		return totalPrice;

	}

}