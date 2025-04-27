package virtualMarket.items;

import virtualMarket.enums.*;

public class ClothingItem extends Item{
		private ClothingType type;
		private ClothingSize size;
		private ClothingFabricType fabric;

		

		public ClothingItem(int id, String name, double price, int amount, ClothingType type, ClothingSize size,
				ClothingFabricType fabric) {
			super(id, name, price, amount);
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
		public Item purchaseItem() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
}
