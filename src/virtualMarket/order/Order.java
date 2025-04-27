package virtualMarket.order;
import virtualMarket.items.*;
import java.util.ArrayList;
import virtualMarket.customer.Customer;
import java.time.LocalDateTime;

public class Order {

	private int id;
	private Customer customer;
	private ArrayList<Item> itemList=new ArrayList<>();
	private double totalAmount;
	private LocalDateTime orderDate;
	private String status;
	
	public Order(int id, Customer customer, ArrayList<Item> itemList, LocalDateTime orderDate, String status) {
		super();
		this.id = id;
		this.customer = customer;
		this.itemList = itemList;
		this.orderDate = orderDate;
		this.status = status;
	}
	
	public double calculateTotal() {
		return 0;
	}
	public String updateStatus() {
		return "";
	}
	
}