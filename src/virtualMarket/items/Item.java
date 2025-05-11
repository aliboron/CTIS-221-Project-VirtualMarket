package virtualMarket.items;

import java.util.ArrayList;

import virtualMarket.interfaces.DiscountableInterface;

// Tum urunlerin ana (soyut) sinifi
public abstract class Item implements DiscountableInterface {

    protected String id; // urun id'si, her urun icin benzersiz olmali
    protected String name; // urun adi
    protected double price; // urun fiyati
    protected int stock; // stok miktari

    // Kurucu metot (constructor)
    public Item(String name, double price, int stock) {
        this.name   = name;
        this.price  = price;
        this.stock = stock;
    }

    // Getter metotlari (private degiskenlere erismek icin)
    public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}
	
	// Setter metotlari (private degiskenleri degistirmek icin)
	public void setStock(int stock) { // Stok icin setter eklendi
		this.stock = stock;
	}
	
	public void setId(String id) { // ID icin setter
		this.id = id;
	}
	
	// Her alt sinif (GroceryItem, ElectronicItem vs.) kendi ID olusturma mantigini uygulayacak
	public abstract String generateID(ArrayList<String> UsedIDs); // Kullanilmis ID'leri alir ki ayni ID uretilmesin
	
	
    @Override // DiscountableInterface'den gelen metot
	public double calculateDiscount(double discountPercentage) {
    	// Indirimli fiyati hesapla ve kuruslari yuvarla
    	return Math.round((price - (price * discountPercentage)) * 100.0) / 100.0;
	}

	// Bu metot urun satin alindiginda cagrilacak, belki garanti baslatma gibi seyler yapar
	public abstract Item purchaseItem(); 
	
	// Sepete eklemek icin urunun bir kopyasini olusturur. Stok genellikle 1 olur bu kopyada.
	public abstract Item createCartCopy(); 

	// Vergili fiyati hesaplar (KDV ekleme gibi)
	public abstract void calculateTaxedPrice();
	
	// Urun bilgilerini dosyaya yazilacak formatta string'e cevirir
	public abstract String toFileString();

    // Urunu JList gibi yerlerde gostermek icin kullanilan string temsili
    public String toString() {
        return String.format("[%s] %s | %.2f ₺ | stock: %d", id, name, price, stock);
    }

	// Verilen bir ID'nin gecerli bir urun ID'si formatinda olup olmadigini kontrol eder
	public static boolean isValidItemID(String id) {
		if (id == null || id.length() != 4) { // ID null olamaz ve 4 karakter olmali
			return false;
		}
		
		try {
			Integer.parseInt(id); // Tamamen sayilardan mi olusuyor?
		} catch (NumberFormatException e) {
			return false; // Sayi degilse gecersiz
		}
		
		char firstDigit = id.charAt(0); // Ilk rakam
		if (firstDigit < '1' || firstDigit > '9') { // Ilk rakam 0 olamaz (1-9 arasi olmali)
			return false;
		}
		
		return true; // Tum kontrollerden gectiyse gecerli
	}

	// Verilen bir ID'den urun tipini tahmin etmeye calisir (Grocery, Clothing, Electronics)
	public static String getItemTypeFromID(String id) {
		if (!isValidItemID(id)) { // Once ID gecerli mi diye bak
			return "Bilinmiyor"; // Gecersizse "Bilinmiyor"
		}
		
		char firstDigit = id.charAt(0); // Ilk rakama gore tip belirle
		if (firstDigit >= '1' && firstDigit <= '3') {
			return "Market Ürünü"; // Grocery
		} else if (firstDigit >= '4' && firstDigit <= '6') {
			return "Kıyafet"; // Clothing
		} else if (firstDigit >= '7' && firstDigit <= '9') {
			return "Elektronik"; // Electronics
		}
		
		return "Bilinmiyor"; // Hicbirine uymuyorsa
	}
}