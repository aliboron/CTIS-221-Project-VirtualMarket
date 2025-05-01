package virtualMarket.main;

import java.io.IOException;

import javax.swing.UIManager;

import virtualMarket.inventory.InventorySystem;
import virtualMarket.ui.*;
import com.formdev.flatlaf.FlatLightLaf;



public class VirtualMarketMain{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/*
		 * DOKUNMAYIN BURAYA --- DONT TOUCH
		 */
		
		try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
		/*
		 * BU ARALIĞA DOKUNMAYIN
		 */
        InventorySystem.loadInventoryFromFile();
		InventorySystem.loadUsedIDs();
        VMarketMainFrame VMMwindow = new VMarketMainFrame();
        VMMwindow.setVisible(true);
	}

}
