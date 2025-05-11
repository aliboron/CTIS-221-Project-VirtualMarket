package virtualMarket.order;

import virtualMarket.items.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
// import java.util.stream.Collectors; // stream'i kaldirdik, gerek kalmadi
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
	private ArrayList<Item> itemList; // urunler ve miktarlari (item.stock miktari tutuyor)
	private double totalAmount; // toplam tutar
	private LocalDateTime orderDate; // siparis tarihi

	private static ArrayList<Integer> usedOrderIDs = new ArrayList<>(); // kullanilan siparis id'leri
	private static final String ORDERS_FILE_PATH = "src/data/orders.csv"; // tum siparisler buraya
	private static final String USED_IDS_FILE_PATH = "src/data/usedorderids.csv"; // kullanilan id'ler buraya

	public Order(int id, Customer customer, ArrayList<Item> itemList, LocalDateTime orderDate) {
		super();
		this.id = id;
		this.customer = customer;
		this.itemList = new ArrayList<>(itemList); // yeni bir liste olusturalim, guvenli olsun
		this.orderDate = orderDate;
		this.totalAmount = calculateTotal(); // toplam tutari hesapla
	}

	public double calculateTotal() {
		double currentTotal = 0;
		for (Item i : itemList) {
			currentTotal += i.getStock() * i.getPrice(); // miktar * fiyat
		}
		return Math.round(currentTotal * 100.0) / 100.0; // kuruslari yuvarla
	}

	public int getId() {
		return id;
	}

	// Urunlerin ozet string'ini olusturur, ornegin "2 pcs Elma - 3 pcs Armut"
	private String getItemsSummaryString() {
		if (itemList == null || itemList.isEmpty()) {
			return ""; // sepet bossa bos string
		}
		// stream yerine normal for dongusu ve StringBuilder kullanalim
		StringBuilder summaryBuilder = new StringBuilder();
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			summaryBuilder.append(item.getStock()); // miktar (item.getStock() sepetteki adedi tutuyor)
			summaryBuilder.append(" pcs "); // "adet" yerine "pcs"
			summaryBuilder.append(item.getName());
			if (i < itemList.size() - 1) { // son urun degilse araya tire koy
				summaryBuilder.append(" - ");
			}
		}
		return summaryBuilder.toString();
	}

	public static int generateOrderID() {
		Random random = new Random();
		int newId;
		do {
			newId = 10000 + random.nextInt(90000); // 5 haneli ID olsun
		} while (usedOrderIDs.contains(newId)); // bu id daha once kullanilmis mi
		return newId;
	}

	public static void loadUsedOrderIDs() {
		File file = new File(USED_IDS_FILE_PATH);
		if (!file.exists()) { // dosya yoksa
			try {
				File parentDir = file.getParentFile();
				if (parentDir != null && !parentDir.exists()) {
					parentDir.mkdirs(); // klasoru olustur
				}
				file.createNewFile(); // dosyayi olustur
				// System.out.println("Empty used order ID file created: " + USED_IDS_FILE_PATH);
			} catch (IOException e) {
				System.out.println("Error creating used order ID file: " + e.getMessage());
				return; // hata varsa devam etme
			}
		}
		try (Scanner scan = new Scanner(file)) {
			usedOrderIDs.clear(); // listeyi temizle once
			while (scan.hasNextLine()) {
				String line = scan.nextLine().trim();
				if (!line.isEmpty()) { // bos satirsa gec
					try {
						usedOrderIDs.add(Integer.parseInt(line));
					} catch (NumberFormatException e) {
						System.out.println("Malformed ID in " + USED_IDS_FILE_PATH + ": " + line);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error loading IDs from " + USED_IDS_FILE_PATH + ": " + e.getMessage());
		}
	}

	public static boolean addUsedOrderIDAndWrite(int id) {
		if (usedOrderIDs.contains(id)) { // bu id zaten varsa
			// System.out.println("This order ID has already been added: " + id); 
			return false;
		}
		usedOrderIDs.add(id); // listeye ekle
		File file = new File(USED_IDS_FILE_PATH);
		File parentDir = file.getParentFile();
		if (parentDir != null && !parentDir.exists()) {
			parentDir.mkdirs(); // klasor yoksa olustur
		}
		try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) { // true = sonuna ekle
			pw.println(id); // dosyaya yaz
			return true;
		} catch (IOException ex) {
			System.out.println("Error writing ID to " + USED_IDS_FILE_PATH + ": " + ex.getMessage());
			return false;
		}
	}

	// Virgül, çift tırnak gibi CSV'yi bozabilecek karakterlerden kaçmak için basit bir metot
	private static String escapeCsv(String data) {
		if (data == null) return ""; // null gelirse bos string donsun
		String escapedData = data.replace("\"", "\"\""); // cift tirnaklari "" yap
		// eger data icinde virgul, cift tirnak veya yeni satir varsa, tum ifadeyi cift tirnak icine al
		if (escapedData.contains(",") || escapedData.contains("\"") || escapedData.contains("\n")) {
			return "\"" + escapedData + "\"";
		}
		return escapedData; // sorun yoksa oldugu gibi donsun
	}

	public static boolean writeOrderToFile(Order order) {
		File ordersFile = new File(ORDERS_FILE_PATH);
		DateTimeFormatter csvDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // standart tarih formati

		try {
			File ordersParentDir = ordersFile.getParentFile();
			if (ordersParentDir != null && !ordersParentDir.exists()) {
				ordersParentDir.mkdirs(); // klasor yoksa olustur
			}

			boolean fileExists = ordersFile.exists(); // dosya var mi diye bak
			try (PrintWriter pw = new PrintWriter(new FileWriter(ordersFile, true))) { // true = sonuna ekle
				if (!fileExists || ordersFile.length() == 0) { // dosya yoksa veya bossa baslik satirini yaz
					pw.println("OrderID,CustomerID,CustomerName,OrderDate,TotalAmount,ItemsSummary");
				}
				StringBuilder sb = new StringBuilder();
				sb.append(order.id).append(",");
				sb.append(order.customer.getId()).append(",");
				sb.append(escapeCsv(order.customer.getName())).append(",");
				sb.append(escapeCsv(order.orderDate.format(csvDateFormatter))).append(",");
				sb.append(String.format("%.2f", order.totalAmount).replace(",", ".")).append(","); // ondalik ayirac nokta olsun
				sb.append(escapeCsv(order.getItemsSummaryString())); // urun ozetini ekle
				pw.println(sb.toString()); // dosyaya yaz
			}
			return true;
		} catch (IOException ex) {
			System.out.println("Error writing order to CSV file " + ORDERS_FILE_PATH + ": " + ex.getMessage());
			// ex.printStackTrace(); // detayli hata icin
			return false;
		}
	}
}