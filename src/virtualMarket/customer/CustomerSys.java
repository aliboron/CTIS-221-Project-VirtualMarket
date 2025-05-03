package virtualMarket.customer;

import java.util.ArrayList;

public class CustomerSys {
	ArrayList<Customer> customers = new ArrayList<>();
	ArrayList<Integer> usedIDs = new ArrayList<>();

	public void addCustomer(Customer customer){

	};

	public void removeCustomer(int id){

	};
	
	

	public static int generateCustomerID(ArrayList<Integer> usedIDs) {
		java.util.Random random = new java.util.Random();
		int id;
		
		do {
			id = 10000 + random.nextInt(90000);
		} while (usedIDs.contains(id));
		
		usedIDs.add(id);
		
		return id;
	}
}
