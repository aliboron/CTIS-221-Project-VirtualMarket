package virtualMarket.inventory;
import java.util.ArrayList;

import virtualMarket.items.*;

public class Supplier {
	
	private int id;
	private String name;
	private String contactInfo;
	private ArrayList<Item> suppliedItems=new ArrayList<Item>();
	
	public Supplier(int id, String name,String contactInfo)
	{
		
		this.id= id;
		this.name= name;
		this.contactInfo= contactInfo;
		
		
	}
	
	public ArrayList<Item> getSuppliedItems()
	{
		
		ArrayList<Item> list=new ArrayList<Item>();
		
		return list;
		
	}
}