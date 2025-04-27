package virtualMarket.items;

public class ClothingItem extends Item{
		private String fabric, size, type;

		public ClothingItem(int id, String name, double price, int amount, String fabric, String size, String type) {
			super(id, name, price, amount);
			this.fabric = fabric;
			this.size = size;
			this.type = type;
		}

		public String getFabric() {
			return fabric;
		}

		public String getSize() {
			return size;
		}

		public String getType() {
			return type;
		}

		@Override
		public Item purchaseItem() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
}
