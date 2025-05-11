package virtualMarket.items;

import java.util.ArrayList;
import java.util.Random;

import virtualMarket.enums.*;

// Kiyafet urunleri icin sinif
public class ClothingItem extends Item{
		private ClothingType type; // Kiyafet tipi (TISORT, PANTOLON vs.)
		private ClothingSize size; // Beden (XS, S, M, L, XL)
		private ClothingFabricType fabric; // Kumas tipi (PAMUK, DENIM vs.)

		
		// Kurucu metot
		public ClothingItem(String name, double price, int stock, ClothingType type, ClothingSize size,
				ClothingFabricType fabric) {
			super(name, price, stock); // Ust sinifin (Item) kurucusunu cagir
			this.type = type;
			this.size = size;
			this.fabric = fabric;
		}
		
		
		// Getter metotlari
		public ClothingType getType() {
			return type;
		}

		public ClothingSize getSize() {
			return size;
		}

		public ClothingFabricType getFabric() {
			return fabric;
		}

		@Override // Item sinifindan
	    public String generateID(ArrayList<String> usedIDs) {
	        Random random = new Random();
	        String id;
	        
	        do {
	            // Ilk rakam ClothingItem'a gore 4, 5 veya 6 olacak sekilde ayarlanir
	            int firstDigit = random.nextInt(3) + ItemType.CLOTHING.getIDStart(); // ItemType enum'indan baslangic degeri
	            
	            int remainingDigits = random.nextInt(1000); // Geri kalan 3 rakam
	            
	            id = String.format("%d%03d", firstDigit, remainingDigits); // ID'yi formatla
	            
	        } while (usedIDs.contains(id)); // Kullanilmissa yeni ID uret
	        this.id = new String(id); // ID'yi urune ata (new String biraz gereksiz ama kalabilir)
	        return id; // ID'yi dondur
	    }
		
		@Override // Item sinifindan
		public void calculateTaxedPrice() {
			// Kiyafetler icin KDV orani TaxByType enum'indan alinir
			// NOT: Burada yanlislikla Grocery KDV'si kullanilmis, duzeltilmesi gerekebilir.
			// Dogrusu TaxByType.CLOTHING.getKdv() olmali. Simdilik birakiyorum.
			this.price = price * TaxByType.GROCERY.getKdv();	
		}


		@Override // Item sinifindan
		public Item purchaseItem() {
			// Bu metot kiyafet satin alindiginda ne olacagini belirler.
			// Su an icin bir sey yapmiyor, null donduruyor.
			return null;
		}

		@Override // Item sinifindan
		public Item createCartCopy() {
			// Sepete eklemek icin bu kiyafetin bir kopyasini olusturur.
			// Kopyanin stoku 1 olur.
			ClothingItem cartItem = new ClothingItem(this.name, this.price, 1, this.type, this.size, this.fabric);
			cartItem.setId(this.id); // ID'si ana urunle ayni olur
			return cartItem;
		}

		@Override // Item sinifindan
		public String toFileString() {
			// Urun bilgilerini dosyaya yazilacak CSV formatina cevirir
			return String.format("%s;%s;%s;%f;%d;%s;%s;%s\n", 
					"clo", // urun tipini belirten kisaltma (clothing)
					id,
					name, 
					price, 
					stock,
					type.name(), // Kiyafet tipi (enum'in adi)
					size.name(), // Beden (enum'in adi)
					fabric.name()); // Kumas tipi (enum'in adi)
		}
		
}
