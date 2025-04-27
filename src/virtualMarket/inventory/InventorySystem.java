package virtualMarket.inventory;

import java.util.ArrayList;

import virtualMarket.items.*;

public class InventorySystem {

	public static ArrayList<Item> itemList = new ArrayList<Item>();

	// public static suppliers (map) ;
	public static void addItem(Item item) {
	};

	public static String displayItems() {

		String out = "";
		for (Item i : itemList) {
			out += i.toString();

		}
		return out;

	};

	public static Item searchItem(int itemId) {

		for (Item i : itemList) {
			if (i.getId() == itemId) {
				return i;

			}

		}

		return null;

	};

	public static void deleteItem(int itemId) {

		for (int i = 0; i < itemList.size(); i++) {

			if (itemList.get(i).getId() == itemId) {
				itemList.remove(i);
			}
		}

	};

	public static double calculateInventoryValue() {
		double totalInventory=0;
		for(Item item:itemList){
			totalInventory+=item.getAmount()*item.getPrice();		
			}
		return totalInventory;
	};

}