package virtualMarket.order;

import virtualMarket.items.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import virtualMarket.customer.Customer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Order {

	private int id;
	private Customer customer;
	private ArrayList<Item> itemList;
	private double totalAmount;
	private LocalDateTime orderDate;

	private static ArrayList<Integer> usedOrderIDs = new ArrayList<>();
	private static final String ORDERS_FILE_PATH = "src/data/orders.csv";
	private static final String USED_IDS_FILE_PATH = "src/data/usedorderids.csv";

	public Order(int id, Customer customer, ArrayList<Item> itemList, LocalDateTime orderDate) {
		super();
		this.id = id;
		this.customer = customer;
		this.itemList = new ArrayList<>(itemList);
		this.orderDate = orderDate;
		this.totalAmount = calculateTotal();
	}

	public double calculateTotal() {
		double currentTotal = 0;
		for (Item i : itemList) {
			currentTotal += i.getStock() * i.getPrice();
		}
		return Math.round(currentTotal * 100.0) / 100.0;
	}

	public int getId() {
		return id;
	}

	private String getItemsSummaryString() {
		if (itemList == null || itemList.isEmpty()) {
			return "";
		}
		StringBuilder summary = new StringBuilder();
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			summary.append(item.getStock());
			summary.append(" pcs ");
			summary.append(item.getName());
			if (i < itemList.size() - 1) {
				summary.append(" - ");
			}
		}
		return summary.toString();
	}

	public static int generateOrderID() {
		Random random = new Random();
		int newId;
		do {
			newId = 10000 + random.nextInt(90000);
		} while (usedOrderIDs.contains(newId));
		return newId;
	}

	public static void loadUsedOrderIDs() {
		File file = new File(USED_IDS_FILE_PATH);
		if (!file.exists()) {
			try {
				File parentDir = file.getParentFile();
				if (parentDir != null && !parentDir.exists()) {
					parentDir.mkdirs();
				}
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Error creating used order ID file: " + e.getMessage());
				return;
			}
		}
		try (Scanner scan = new Scanner(file)) {
			usedOrderIDs.clear();
			while (scan.hasNextLine()) {
				String line = scan.nextLine().trim();
				if (!line.isEmpty()) {
					try {
						usedOrderIDs.add(Integer.parseInt(line));
					} catch (NumberFormatException e) {
						System.out.println("Invalid ID in " + USED_IDS_FILE_PATH + ": " + line);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error loading IDs from " + USED_IDS_FILE_PATH + ": " + e.getMessage());
		}
	}

	public static boolean addUsedOrderIDAndWrite(int id) {
		if (usedOrderIDs.contains(id)) {
			return false;
		}
		usedOrderIDs.add(id);
		File file = new File(USED_IDS_FILE_PATH);
		try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
			pw.println(id);
			return true;
		} catch (IOException ex) {
			System.out.println("Error writing ID to " + USED_IDS_FILE_PATH + ": " + ex.getMessage());
			return false;
		}
	}

	public static boolean writeOrderToFile(Order order) {
		File ordersFile = new File(ORDERS_FILE_PATH);

		try {

			boolean fileExists = ordersFile.exists();
			try (PrintWriter pw = new PrintWriter(new FileWriter(ordersFile, true))) {
				String lineToWrite = order.id + ";" +
				                   order.customer.getId() + ";" +
				                   order.customer.getName() + ";" +
				                   order.orderDate.toString() + ";" +
				                   String.format("%.2f", order.totalAmount).replace(",", ".") + ";" +
				                   order.getItemsSummaryString();
				pw.println(lineToWrite);
			}
			return true;
		} catch (IOException ex) {
			System.out.println("Error writing order to CSV file " + ORDERS_FILE_PATH + ": " + ex.getMessage());
			return false;
		}
	}
}