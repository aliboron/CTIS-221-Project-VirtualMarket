package virtualMarket.items;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import virtualMarket.enums.*;


public class ElectronicItem extends Item {

	private int warrantyPeriod;
	private ElectronicsType type;
	private ElectronicsBrand brand;
	private LocalDateTime warrantyStart;
	private LocalDateTime warrantyEnd; 

	public ElectronicItem(String name, double price, int stock, int warrantyPeriod, ElectronicsType type,
			ElectronicsBrand brand) {
		super(name, price, stock);
		this.warrantyPeriod = warrantyPeriod;
		this.type = type;
		this.brand = brand;
	}

	@Override
	public String generateID(ArrayList<String> usedIDs) {
		Random random = new Random();
		String id;

		do {
			int firstDigit = random.nextInt(3) + ItemType.ELECTRONIC.getIDStart();

			int remainingDigits = random.nextInt(1000);

			id = String.format("%d%03d", firstDigit, remainingDigits);

		} while (usedIDs.contains(id));
		this.id = id;
		return id;
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

	@Override 
	public void calculateTaxedPrice() {
		this.price = price + price * TaxByType.ELECTRONICS.getKdv();
	}

	@Override
	public Item purchaseItem() {

		if (this.stock <= 0)
			return null;

		warrantyStart = LocalDateTime.now();
		warrantyEnd = warrantyStart.plusYears(warrantyPeriod);

		ElectronicItem purchasedCopy = new ElectronicItem(this.name, this.price, 1, this.warrantyPeriod, this.type,
				this.brand);
		purchasedCopy.setId(this.id);
		purchasedCopy.warrantyStart = this.warrantyStart;
		purchasedCopy.warrantyEnd = this.warrantyEnd;
		return purchasedCopy;
	}

	@Override 
	public Item createCartCopy() {
		ElectronicItem cartItem = new ElectronicItem(this.name, this.price, 1, this.warrantyPeriod, this.type,
				this.brand);
		cartItem.setId(this.id);
		return cartItem;
	}

	@Override
	public String toFileString() {
		return String.format("%s;%s;%s;%f;%d;%d;%s;%s\n", "elc",
				id, name, price, stock, warrantyPeriod,
				type.name(), 
				brand.name()
		);
	}
	
	@Override
	public String getItemType() {
		return "Electronic";
	}

}
