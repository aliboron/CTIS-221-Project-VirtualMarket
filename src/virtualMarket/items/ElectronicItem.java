package virtualMarket.items;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import virtualMarket.enums.*;
// import virtualMarket.inventory.InventorySystem; // Bu import kullanilmiyor gibi

// Elektronik urunler icin sinif
public class ElectronicItem extends Item {

	private int warrantyPeriod; // Garanti suresi (yil olarak)
	private ElectronicsType type; // Elektronik urun tipi (TELEFON, BILGISAYAR vs.)
	private ElectronicsBrand brand; // Markasi (APPLE, SAMSUNG vs.)
	private LocalDateTime warrantyStart; // Garanti baslangic tarihi
	private LocalDateTime warrantyEnd; // Garanti bitis tarihi

	// Kurucu metot
	public ElectronicItem(String name, double price, int stock, int warrantyPeriod, ElectronicsType type,
			ElectronicsBrand brand) {
		super(name, price, stock); // Ust sinifin (Item) kurucusunu cagir
		this.warrantyPeriod = warrantyPeriod;
		this.type = type;
		this.brand = brand;
	}

	@Override // Item sinifindan
	public String generateID(ArrayList<String> usedIDs) {
		Random random = new Random();
		String id;

		do {
			// Ilk rakam ElectronicItem'a gore 7, 8 veya 9 olacak sekilde ayarlanir
			int firstDigit = random.nextInt(3) + ItemType.ELECTRONIC.getIDStart(); // ItemType enum'indan baslangic
																					// degeri

			int remainingDigits = random.nextInt(1000); // Geri kalan 3 rakam

			id = String.format("%d%03d", firstDigit, remainingDigits); // ID'yi formatla

		} while (usedIDs.contains(id)); // Kullanilmissa yeni ID uret
		this.id = id; // Urunun ID'sini ata
		return id; // ID'yi dondur
	}

	public ElectronicsType getType() {
		return type;
	}

	public ElectronicsBrand getBrand() {
		return brand;
	}

	public int getWarrantyPeriod() {
		return warrantyPeriod;
	}

	@Override // Item sinifindan
	public void calculateTaxedPrice() {
		// Elektronik urunler icin KDV orani TaxByType enum'indan alinir
		this.price = price * TaxByType.ELECTRONICS.getKdv();
	}

	@Override // Item sinifindan
	public Item purchaseItem() {
		// Bu metot urun satin alindiginda cagrilir.
		// Stok kontrolu burada yapilmiyor, o sepet veya odeme asamasinda olmali.
		// Garanti tarihlerini ayarlar.
		if (this.stock <= 0) // yine de bir kontrol ekleyelim
			return null;

		warrantyStart = LocalDateTime.now(); // Garanti bugun baslar
		warrantyEnd = warrantyStart.plusYears(warrantyPeriod); // Garanti suresi kadar yil eklenir

		// Satin alinan urun icin yeni bir kopya olusturulup dondurulur.
		// Bu, siparis detaylarinda veya gecmisinde saklanacak urun olabilir.
		ElectronicItem purchasedCopy = new ElectronicItem(this.name, this.price, 1, this.warrantyPeriod, this.type,
				this.brand);
		purchasedCopy.setId(this.id);
		purchasedCopy.warrantyStart = this.warrantyStart; // Ayarlanan garanti tarihlerini kopyaya da ver
		purchasedCopy.warrantyEnd = this.warrantyEnd;
		return purchasedCopy;
	}

	@Override // Item sinifindan
	public Item createCartCopy() {
		// Sepete eklemek icin bu elektronik urunun bir kopyasini olusturur.
		// Kopyanin stoku 1 olur. Garanti bilgileri bu asamada ayarlanmaz, satin almada
		// ayarlanir.
		ElectronicItem cartItem = new ElectronicItem(this.name, this.price, 1, this.warrantyPeriod, this.type,
				this.brand);
		cartItem.setId(this.id); // ID'si ana urunle ayni olur
		return cartItem;
	}

	@Override // Item sinifindan
	public String toFileString() {
		// Urun bilgilerini dosyaya yazilacak CSV formatina cevirir
		return String.format("%s;%s;%s;%f;%d;%d;%s;%s\n", "elc", // urun tipini belirten kisaltma (electronic)
				id, name, price, stock, warrantyPeriod, // Garanti suresi
				type.name(), // Elektronik tipi (enum'in adi)
				brand.name() // Marka (enum'in adi)
		);
	}

}
