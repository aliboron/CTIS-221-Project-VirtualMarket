package virtualMarket.enums;

public enum ItemType {
	GROCERY(1),
	CLOTHING(4),
	ELECTRONIC(7);
	
	private int idStart;
	ItemType(int i){
		this.idStart = i;
	}
	
	public int getIDStart() {
		return this.idStart;
	}
}
