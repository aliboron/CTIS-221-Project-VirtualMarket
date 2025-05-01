package virtualMarket.inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import virtualMarket.items.*;

public class InventorySystem {

	public static ArrayList<Item> inventory = new ArrayList<Item>();
	public static ArrayList<String> usedIds = new ArrayList<>();

	// public static suppliers (map) ;

	public static boolean addItem(Item item) {
		return inventory.add(item);
	};
	
	public static boolean writeInventoryToFile() throws IOException {
		File inventoryFile = new File("src/data/inventory.csv");
		
		// Create parent directories if they don't exist
		inventoryFile.getParentFile().mkdirs();
		
		// Use try-with-resources to ensure the writer is closed
		try (PrintWriter inventoryWriter = new PrintWriter(new FileWriter(inventoryFile, false))) { // false = overwrite
			
			for (Item item : inventory) {
				if(item instanceof GroceryItem) {
					inventoryWriter.printf("%s,%s,%s,%f,%d,%s,%s\n", 
						"grc", 
						item.getId(),
						item.getName(), 
						item.getPrice(), 
						item.getAmount(), 
						((GroceryItem) item).getExpiryDate().toString(), 
						((GroceryItem) item).getGroceryType().name());
				} else if (item instanceof ElectronicItem) {
					inventoryWriter.printf("%s,%s,%s,%f,%d,%s,%s\n", 
						"elc", // Fixed identifier
						item.getId(),
						item.getName(), 
						item.getPrice(), 
						item.getAmount(), 
						((ElectronicItem) item).getType().name(), 
						((ElectronicItem) item).getBrand().name());
				} else {
					inventoryWriter.printf("%s,%s,%s,%f,%d,%s,%s,%s\n", 
						"clo", // Changed to a clothing identifier
						item.getId(),
						item.getName(), 
						item.getPrice(), 
						item.getAmount(), 
						((ClothingItem) item).getType().name(), 
						((ClothingItem) item).getSize().name(), 
						((ClothingItem) item).getFabric().name());
				}
			}
			
			// Ensure data is written
			inventoryWriter.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

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
			totalInventory += item.getAmount() * item.getPrice();
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
	    
	    try (java.util.Scanner scanner = new java.util.Scanner(inventoryFile)) {
	        // Clear existing inventory
	        inventory.clear();
	        
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] parts = line.split(",");
	            
	            // Check if there are enough parts in the line
	            if (parts.length < 5) {
	                System.err.println("Invalid line format: " + line);
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
	                    // Create ClothingItem
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
	            
	            // Add the created item to inventory and set the ID
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