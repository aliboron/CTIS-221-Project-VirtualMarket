package virtualMarket.main;

import java.io.IOException;

import javax.swing.UIManager;

import virtualMarket.customer.CustomerSys;
import virtualMarket.inventory.InventorySystem;
import virtualMarket.ui.*;
import com.formdev.flatlaf.FlatLightLaf;



public class VirtualMarketMain{

	public static void main(String[] args) throws IOException {
		
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        InventorySystem.loadInventoryFromFile();
		InventorySystem.loadUsedIDs();
		CustomerSys.loadCustomersFromFile();
		CustomerSys.loadUsedCustomerIDs();
        VMarketMainFrame VMMwindow = new VMarketMainFrame();
        VMMwindow.setVisible(true);
	}

}
