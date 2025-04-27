package virtualMarket.items;

import java.util.ArrayList;
import java.util.Random;

import virtualMarket.enums.*;

public class ClothingItem extends Item{
		private ClothingType type;
		private ClothingSize size;
		private ClothingFabricType fabric;

		

		public ClothingItem(String name, double price, int amount, ClothingType type, ClothingSize size,
				ClothingFabricType fabric) {
			super(name, price, amount);
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
	        usedIDs.add(id);
	        return id;
	    }
		
		@Override
		public void calculateTaxedPrice() {
			this.price = price * TaxByType.GROCERY.getKdv();	
		}


		@Override
		public Item purchaseItem() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
}
