package virtualMarket.items;

import java.util.ArrayList;
import java.util.Random;

import virtualMarket.enums.*;

public class ClothingItem extends Item{
		private ClothingType type;
		private ClothingSize size;
		private ClothingFabricType fabric;

		
		public ClothingItem(String name, double price, int stock, ClothingType type, ClothingSize size,
				ClothingFabricType fabric) {
			super(name, price, stock);
			this.type = type;
			this.size = size;
			this.fabric = fabric;
		}
		
		
		public ClothingType getType() {
			return type;
		}

		public ClothingSize getSize() {
			return size;
		}

		public ClothingFabricType getFabric() {
			return fabric;
		}

		@Override
	    public String generateID(ArrayList<String> usedIDs) {
	        Random random = new Random();
	        String id;
	        
	        do {
	            int firstDigit = random.nextInt(3) + ItemType.CLOTHING.getIDStart();
	            
	            int remainingDigits = random.nextInt(1000);
	            
	            id = String.format("%d%03d", firstDigit, remainingDigits);
	            
	        } while (usedIDs.contains(id));
	        this.id = new String(id);
	        return id;
	    }
		
		@Override
		public void calculateTaxedPrice() {
			this.price = price + price * TaxByType.CLOTHING.getKdv();	
		}


		@Override
		public Item purchaseItem() {
			return null;
		}

		@Override
		public Item createCartCopy() {
			ClothingItem cartItem = new ClothingItem(this.name, this.price, 1, this.type, this.size, this.fabric);
			cartItem.setId(this.id);
			return cartItem;
		}

		@Override
		public String toFileString() {
			return String.format("%s;%s;%s;%f;%d;%s;%s;%s\n", 
					"clo",
					id,
					name, 
					price, 
					stock,
					type.name(),
					size.name(),
					fabric.name());
		}


		@Override
		public String getItemType() {
			return "Clothing";
		}
			
}
