package virtualMarket.inventory;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import virtualMarket.items.*;

public class InventorySystem {

	public static ArrayList<Item> itemList = new ArrayList<Item>();
	private static ArrayList<String> usedIds = new ArrayList<>();

	// public static suppliers (map) ;

	public static boolean addItem(Item item) {
		return itemList.add(item);
	};

	public static String displayItems() {

		String out = "";
		for (Item i : itemList) {
			out += i.toString();
		}
		return out;

	};

	public static Item searchItem(String itemId) {

		for (Item i : itemList) {
			if (i.getId().equalsIgnoreCase(itemId)) {
				return i;
			}
		}
		return null;

	};

	public static boolean searchItemByName(String searchedName) {

		for (Item i : itemList) {
			if (i.getName().equalsIgnoreCase(searchedName)) {
				return true;
			}
		}
		return false;
	};

	public static void deleteItem(String itemId) {

		for (int i = 0; i < itemList.size(); i++) {

			if (itemList.get(i).getId().equalsIgnoreCase(itemId)) {
				itemList.remove(i);
			}
		}

	};

	public static double calculateInventoryValue() {
		double totalInventory = 0;
		for (Item item : itemList) {
			totalInventory += item.getAmount() * item.getPrice();
		}
		return totalInventory;
	};

	public static boolean loadUsedIDs() throws IOException {
		File file = new File("src/data/usedidset.json");

		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try (FileWriter writer = new FileWriter(file)) {
				writer.write("[]");
			}
			return true;
		}

		try (Reader reader = new FileReader(file)) {

			Gson gson = new Gson();

			String[] loadedIds = gson.fromJson(reader, String[].class);

			usedIds.clear();
			if (loadedIds != null) {
				usedIds.addAll(Arrays.asList(loadedIds));
			}
			return true;
		} catch (JsonSyntaxException e) {
			System.err.println("Error parsing JSON file: " + e.getMessage());
			return false;
		}
	}

}