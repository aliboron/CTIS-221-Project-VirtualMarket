package virtualMarket.enums;

public enum TaxByType {
	CLOTHING(0.1),
	ELECTRONICS(0.2),
	GROCERY(0.01);
	
	
	private final double kdv;
	
	private TaxByType(double tax) {
		this.kdv = tax;
	}

	public double getKdv() {
		return kdv;
	}
	
	
}
