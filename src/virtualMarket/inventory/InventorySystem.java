package virtualMarket.inventory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import virtualMarket.items.*;

public class InventorySystem {

	public static ArrayList<Item> inventory = new ArrayList<Item>();
	public static ArrayList<String> usedIds = new ArrayList<>();

	// public static suppliers (map) ;

	public static boolean addItem(Item item) {
		return inventory.add(item);
	};

	public static String displayItems() {

		String out = "";
		for (Item i : inventory) {
			out += i.toString();
		}
		return out;

	};

	public static Item searchItem(String itemId) {

		for (Item i : inventory) {
			if (i.getId().equalsIgnoreCase(itemId)) {
				return i;
			}
		}
		return null;

	};

	public static boolean searchItemByName(String searchedName) {

		for (Item i : inventory) {
			if (i.getName().equalsIgnoreCase(searchedName)) {
				return true;
			}
		}
		return false;
	};

	public static void deleteItem(String itemId) {

		for (int i = 0; i < inventory.size(); i++) {

			if (inventory.get(i).getId().equalsIgnoreCase(itemId)) {
				inventory.remove(i);
			}
		}

	};

	public static double calculateInventoryValue() {
		double totalInventory = 0;
		for (Item item : inventory) {
			totalInventory += item.getStock() * item.getPrice();
		}
		return totalInventory;
	};

	public static boolean loadInventoryFromFile() {
	    File inventoryFile = new File("src/data/inventory.csv");
	    
	    // Check if file exists
	    if (!inventoryFile.exists()) {
	        System.err.println("Inventory file not found");
	        return false;
	    }
	    
	    try (Scanner scanner = new Scanner(inventoryFile)) {
	        // Clear existing inventory
	        inventory.clear();
	        
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] parts = line.split(",");
	            
	            // Check if there are enough parts in the line
	            if (parts.length < 5) {
	                System.out.println("Invalid line format: " + line);
	                continue;
	            }
	            
	            // Check item type from first field
	            String itemType = parts[0];
	            
	            // Common properties for all items
	            String id = parts[1];           // ID is now at index 1
	            String name = parts[2];         // Name is now at index 2
	            double price = Double.parseDouble(parts[3]);
	            int amount = Integer.parseInt(parts[4]);
	            
	            Item item = null;
	            
	            switch (itemType) {
	                case "grc":
	                    if (parts.length < 7) {
	                        System.err.println("Invalid grocery item format: " + line);
	                        continue;
	                    }
	                    // Create GroceryItem
	                    java.time.LocalDateTime expiryDate = java.time.LocalDateTime.parse(parts[5]);
	                    virtualMarket.enums.GroceryType groceryType = 
	                        virtualMarket.enums.GroceryType.valueOf(parts[6]);
	                    item = new GroceryItem(name, price, amount, expiryDate, groceryType);
	                    break;
	                    
	                case "elc":
	                    if (parts.length < 7) {
	                        System.err.println("Invalid electronic item format: " + line);
	                        continue;
	                    }
	                    // Create ElectronicItem
	                    virtualMarket.enums.ElectronicsType electronicsType = 
	                        virtualMarket.enums.ElectronicsType.valueOf(parts[5]);
	                    virtualMarket.enums.ElectronicsBrand brand = 
	                        virtualMarket.enums.ElectronicsBrand.valueOf(parts[6]);
	                    // Default 12 month warranty
	                    item = new ElectronicItem(name, price, amount, 12, electronicsType, brand);
	                    break;
	                    
	                case "clo":
	                    if (parts.length < 8) {
	                        System.err.println("Invalid clothing item format: " + line);
	                        continue;
	                    }
	                    virtualMarket.enums.ClothingType clothingType = 
	                        virtualMarket.enums.ClothingType.valueOf(parts[5]);
	                    virtualMarket.enums.ClothingSize size = 
	                        virtualMarket.enums.ClothingSize.valueOf(parts[6]);
	                    virtualMarket.enums.ClothingFabricType fabric = 
	                        virtualMarket.enums.ClothingFabricType.valueOf(parts[7]);
	                    item = new ClothingItem(name, price, amount, clothingType, size, fabric);
	                    break;
	                    
	                default:
	                    System.err.println("Unknown item type: " + itemType);
	                    continue;
	            }
	            

	            if (item != null) {
	                // Assuming there's a setId method or you can set it in the constructor
	                item.setId(id);
	                inventory.add(item);
	                
	                // Add ID to used IDs list if not already there
	                if (!usedIds.contains(id)) {
	                    usedIds.add(id);
	                }
	            }
	        }
	        
	        return true;
	    } catch (Exception e) {
	        System.err.println("Error loading inventory: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static boolean writeInventoryToFile() throws IOException {
		File inventoryFile = new File("src/data/inventory.csv");
		
		if (!inventoryFile.exists())
			inventoryFile.getParentFile().mkdirs();
		

		try (PrintWriter inventoryWriter = new PrintWriter(new FileWriter(inventoryFile, false))) { // false = overwrite
			
			for (Item item : inventory) {
				inventoryWriter.print(item.toFileString());
			}
			
			inventoryWriter.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean loadUsedIDs() throws IOException {
		File file = new File("src/data/usedidsetItems.csv");

		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try (FileWriter writer = new FileWriter(file)) {
				writer.write("");
			}
			return true;
		}

		try (Scanner scan = new Scanner(file)) {
			while(scan.hasNext()) {
				String current = scan.nextLine().trim();
				usedIds.add(current);
			}
			return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean addUsedIDAndWrite(String id) {
		File file = new File("src/data/usedidsetItems.csv");
		if (!Item.isValidItemID(id)) {
			System.out.println("Wrong id format!");
			return false;
		}
		
		for (String str : usedIds) {
			if (str.equalsIgnoreCase(id))
				return false;
		}
		
		usedIds.add(id);
		
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(file, true));
			pw.printf("%s\n", id);
			pw.flush();
			pw.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return true;
			
	}

}