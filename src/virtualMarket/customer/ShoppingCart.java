package virtualMarket.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList; 
import java.time.LocalDateTime; 
import virtualMarket.order.*;
import virtualMarket.items.*;
import virtualMarket.inventory.InventorySystem;

public class ShoppingCart {
	private Customer customer; // bu sepet hangi musterinin
	private Map<String, Item> items; // sepetteki urunler, String urun ID'si, Item da urunun kendisi (kopyasi)

	public ShoppingCart(Customer customer) {
		super();
		this.customer = customer;
		this.items = new HashMap<>(); // baslangicta sepet bos
	};

	// Sepete urun ekleme metodu
	public void addItem(Item inventoryItem) {
		if (inventoryItem != null && inventoryItem.getStock() > 0) { // urun var mi ve stokta mi?
			Item cartItem = items.get(inventoryItem.getId()); // sepette bu urunden var mi diye bak
			if (cartItem != null) {
				// Sepette bu urun zaten var, miktarini (stok) bir artir
				cartItem.setStock(cartItem.getStock() + 1);
			} else {
				// Sepette bu urun yok, envanterdeki urunun bir kopyasini olusturup sepete ekle
				// createCartCopy() metodu kopyanin stokunu 1 yapar
				cartItem = inventoryItem.createCartCopy(); 
				items.put(inventoryItem.getId(), cartItem); // sepete (map'e) ekle
			}
			// Envanterdeki asil urunun stokunu bir azalt
			inventoryItem.setStock(inventoryItem.getStock() - 1); 
		}
	};

	/**
	 * Sepetteki bir urunun miktarini bir azaltir.
	 * Miktar 0 olursa urunu sepetten kaldirir.
	 * Ana envanterdeki stok bir artirilir.
	 * @param itemId Azaltilacak urunun ID'si.
	 * @return Islem basariliysa true, degilse false.
	 */
	public boolean reduceItemByOne(String itemId) {
		Item cartItem = items.get(itemId); // sepetteki urunu bul
		if (cartItem != null) { // urun sepette varsa
			Item inventoryItem = InventorySystem.searchItem(itemId); // envanterdeki karsiligini bul
			if (inventoryItem != null) { // envanterde de varsa (olmasi lazim)
				inventoryItem.setStock(inventoryItem.getStock() + 1); // envantere bir tane geri koy
				if (cartItem.getStock() > 1) { // sepette 1'den fazla varsa
					cartItem.setStock(cartItem.getStock() - 1); // miktarini bir azalt
				} else {
					items.remove(itemId); // sepette 1 tane varsa, sepetten tamamen kaldir
				}
				return true; // islem basarili
			}
		}
		return false; // bir sorun olduysa false don
	}

	/**
	 * Bir urunu miktarindan bagimsiz olarak sepetten tamamen kaldirir.
	 * O urunun sepetteki tum adetlerini ana envantere geri ekler.
	 * @param itemId Kaldirilacak urunun ID'si.
	 * @return Islem basariliysa true, degilse false.
	 */
	public boolean removeItemCompletely(String itemId) {
		Item cartItem = items.get(itemId); // sepetteki urunu bul
		if (cartItem != null) { // urun sepette varsa
			Item inventoryItem = InventorySystem.searchItem(itemId); // envanterdeki karsiligini bul
			if (inventoryItem != null) { // envanterde de varsa
				// sepetteki tum adedi (cartItem.getStock()) envantere geri ekle
				inventoryItem.setStock(inventoryItem.getStock() + cartItem.getStock()); 
			}
			items.remove(itemId); // urunu sepetten (map'ten) kaldir
			return true; // islem basarili
		}
		return false; // urun sepette yoksa false don
	}

	// Bu metot artik cok genel kaliyor, reduceItemByOne ve removeItemCompletely daha net.
	// Belki ileride refactor edilebilir veya kaldirilabilir.
	// Simdilik, bu ID'deki urunu tamamen kaldir anlamina gelsin.
	public void removeItem(Item item) {
		if (item != null) {
			removeItemCompletely(item.getId());
		}
	};

	// Sepetteki urunlerin bir koleksiyonunu (liste gibi) dondurur
	public List<Item> getItems() { 
		List list = new ArrayList();
		list.addAll(items.values());
		return list; // Map'in degerlerini (Item nesnelerini) dondur
	}

	// Sepeti temizler
	public void clearCart() {
		// Istege bagli: Sepeti temizlemeden once urunleri envantere geri ekleme mantigi eklenebilir
		// Mesela:
		// for (Item cartItem : items.values()) {
		//     Item inventoryItem = InventorySystem.searchItem(cartItem.getId());
		//     if (inventoryItem != null) {
		//         inventoryItem.setStock(inventoryItem.getStock() + cartItem.getStock());
		//     }
		// }
		items.clear(); // Map'i temizle, sepet bosalsin
	}

	// Odeme islemini yapar, bir Order nesnesi olusturur ve dondurur
	public Order checkOut() {
		if (items.isEmpty()) { // sepet bossa
			return null; // null don, siparis yok
		}

		int orderId = Order.generateOrderID(); // yeni bir siparis ID'si al
		
		// Siparis icin yeni bir urun listesi olustur.
		// Sepetteki urunlerin 'stock' degeri zaten miktarlarini tutuyor.
		ArrayList<Item> orderItems = new ArrayList<>(items.values());

		Order order = new Order(orderId, this.customer, orderItems, LocalDateTime.now());
		
		Order.addUsedOrderIDAndWrite(orderId); // Kullanilan siparis ID'sini kaydet

		this.clearCart(); // Odeme sonrasi sepeti temizle

		return order; // Olusan siparisi dondur
	};

}