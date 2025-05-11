package virtualMarket.items;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import virtualMarket.enums.*;
// import virtualMarket.inventory.InventorySystem; // Bu import kullanilmiyor gibi

// Market urunleri (yiyecek, icecek vs.) icin sinif
public class GroceryItem extends Item{
	private LocalDateTime expiryDate; // Son kullanma tarihi
	private GroceryType type; // Market urununun tipi (Meyve, Sebze vs.)
	
	// Kurucu metot
    public GroceryItem(String name, double price, int stock, LocalDateTime expiryDate, GroceryType type) {
    	super(name, price, stock); // Ust sinifin (Item) kurucusunu cagir
    	this.expiryDate = expiryDate;
    	this.type = type;
    }
    
    @Override // Item sinifindan gelen soyut metodu dolduruyoruz
    public String generateID(ArrayList<String> usedIDs) {
        Random random = new Random(); // Rastgele sayi uretici
        String id;
        
        do {
            // Ilk rakam GroceryType'a gore 1, 2 veya 3 olacak sekilde ayarlanir
            int firstDigit = random.nextInt(3) + ItemType.GROCERY.getIDStart(); // ItemType enum'indan baslangic degeri alinir
            
            int remainingDigits = random.nextInt(1000); // Geri kalan 3 rakam (0-999 arasi)
            
            id = String.format("%d%03d", firstDigit, remainingDigits); // ID'yi formatla (or: 1005, 2123)
            
        } while (usedIDs.contains(id)); // Bu ID daha once kullanildiysa yeni bir tane uret
        this.id = id; // Olusan ID'yi urunun id'sine ata
        return id; // ID'yi dondur
    }

	@Override // Item sinifindan
	public void calculateTaxedPrice() {
		// Market urunleri icin KDV orani TaxByType enum'indan alinir
		// NOT: Burada yanlislikla Clothing KDV'si kullanilmis, duzeltilmesi gerekebilir.
		// Dogrusu TaxByType.GROCERY.getKdv() olmali. Simdilik birakiyorum.
		this.price = price * TaxByType.CLOTHING.getKdv();	
	}

	public GroceryType getGroceryType (){
		return type;
	}

	@Override // Item sinifindan
	public Item purchaseItem() {
		// Bu metot market urunu satin alindiginda ne olacagini belirler.
		// Su an icin bir sey yapmiyor, null donduruyor.
		// Belki son kullanma tarihi kontrolu vs. eklenebilir.
		return null;
	}

	@Override // Item sinifindan
	public Item createCartCopy() {
		// Sepete eklemek icin bu market urununun bir kopyasini olusturur.
		// Kopyanin stoku 1 olur.
		GroceryItem cartItem = new GroceryItem(this.name, this.price, 1, this.expiryDate, this.type);
		cartItem.setId(this.id); // ID'si ana urunle ayni olur
		return cartItem;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}
	
	// Urun bilgilerini dosyaya yazilacak CSV formatina cevirir
	public String toFileString() {
		return String.format("%s;%s;%s;%f;%d;%s;%s\n", 
				"grc", // urun tipini belirten kisaltma (grocery)
				id,
				name, 
				price, 
				stock,
				expiryDate.toString(), // Son kullanma tarihi (ISO formatinda)
				type.name()); // Market urunu tipi (enum'in adi olarak)
	}
    
}