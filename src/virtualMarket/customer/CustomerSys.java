package virtualMarket.customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CustomerSys {
	public static ArrayList<Customer> customers = new ArrayList<>();
	static ArrayList<Integer> usedIDs = new ArrayList<>();

	public static void addCustomer(String name, String email, String address){
		int newId = generateCustomerID();
		Customer customer = new Customer(newId, name, email, address);
		customers.add(customer);
		try {
			writeCustomersToFile();
		} catch (IOException e) {
			System.out.println("Error writing customers file after adding: " + e.getMessage());
			e.printStackTrace();
		}
	};

	public static boolean removeCustomer(int id){
		boolean removed = customers.removeIf(customer -> customer.getId() == id);
		if (removed) {
			for (Integer i : usedIDs)
				if (i == id)
					usedIDs.remove(i);
		}
		return removed;
	};
	
	public static int generateCustomerID() {
		Random random = new Random();
		int id;
		
		do {
			id = 10000 + random.nextInt(90000);
		} while (usedIDs.contains(id));
		
		return id;
	}


	public static boolean loadCustomersFromFile() {
		File customerFile = new File("src/data/customers.csv");
		
		if (!customerFile.exists()) {
			System.err.println("Customers file not found: src/data/customers.csv");
			File parentDir = customerFile.getParentFile();
			if (parentDir != null && !parentDir.exists()) {
				parentDir.mkdirs();
			}
			try {
				customerFile.createNewFile();
				System.out.println("Created empty customers file: src/data/customers.csv");
				return true; 
			} catch (IOException e) {
				System.err.println("Could not create customers file: " + e.getMessage());
				return false;
			}
		}
		
		try (Scanner scanner = new Scanner(customerFile)) {
			customers.clear();
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				
				if (parts.length < 4) {
					System.err.println("Invalid customer line format: " + line);
					continue;
				}
				
				try {
					int id = Integer.parseInt(parts[0]);
					String name = parts[1];
					String email = parts[2];
					String address = parts[3];
					
					Customer customer = new Customer(id, name, email, address);
					customers.add(customer);
					
					if (!usedIDs.contains(id)) {
						usedIDs.add(id);
					}
				} catch (NumberFormatException e) {
					System.err.println("Invalid customer ID format in line: " + line);
				}
			}
			return true;
		} catch (IOException e) {
			System.err.println("Error loading customers: " + e.getMessage());
			e.printStackTrace();
			System.out.println(customers.toString());
			return false;
		}
		
	}

	public static boolean writeCustomersToFile() throws IOException {
		File customerFile = new File("src/data/customers.csv");
		
		File parentDir = customerFile.getParentFile();
		if (parentDir != null && !parentDir.exists()) {
			parentDir.mkdirs();
		}

		try (PrintWriter customerWriter = new PrintWriter(new FileWriter(customerFile, false))) { // false = overwrite
			for (Customer customer : customers) {
				customerWriter.print(customer.toFileString()); 
			}
			customerWriter.flush();
			return true;
		} catch (IOException e) {
			System.err.println("Error writing customers to file: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public static boolean loadUsedCustomerIDs() throws IOException {
		File file = new File("src/data/usedidsetCustomers.csv");

		if (!file.exists()) {
			File parentDir = file.getParentFile();
			if (parentDir != null && !parentDir.exists()) {
				parentDir.mkdirs();
			}
			try {
				file.createNewFile();
				System.out.println("Created empty used customer IDs file: src/data/usedidsetCustomers.csv");
				return true; // Start with empty list if file was created
			} catch (IOException e) {
				System.err.println("Could not create used customer IDs file: " + e.getMessage());
				return false;
			}
		}

		try (Scanner scan = new Scanner(file)) {
			usedIDs.clear(); // Clear existing list
			while(scan.hasNextLine()) { // Read line by line
				String line = scan.nextLine().trim();
				if (!line.isEmpty()) {
					try {
						int currentId = Integer.parseInt(line);
						if (!usedIDs.contains(currentId)) { // Avoid duplicates
							usedIDs.add(currentId);
						}
					} catch (NumberFormatException e) {
						System.err.println("Invalid ID format in usedidsetCustomers.csv: " + line);
					}
				}
			}
			return true;
		} catch (IOException e) {
			System.err.println("Error loading used customer IDs: " + e.getMessage());
			return false;
		}
	}
	
	public static boolean writeUsedCustomerIDs() {
		File file = new File("src/data/usedidsetCustomers.csv");
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
			for (Integer i: usedIDs)
				pw.println(i);
			pw.println();
			pw.flush();
			return true;
		} catch(IOException ex) {
			System.err.println("Error writing used customer IDs to file: " + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}
	
	public static String display()
	{
		String out="Customer Informations\n";
		for(Customer c:customers)
		{
			out += c.displayInfo();
		}
		
		return out;
	}
}