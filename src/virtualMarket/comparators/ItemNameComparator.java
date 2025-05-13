package virtualMarket.comparators;

import java.util.Comparator;

import virtualMarket.items.Item;

public class ItemNameComparator implements Comparator<Item>{
	
	
	
	@Override
	public int compare(Item o1, Item o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
}
