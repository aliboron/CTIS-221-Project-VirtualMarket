package virtualMarket.main;

import javax.swing.UIManager;
import virtualMarket.ui.*;
import com.formdev.flatlaf.FlatLightLaf;



public class VirtualMarketMain{

	public static void main(String[] args) {
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
        
        VMarketMainFrame VMMwindow = new VMarketMainFrame();
        VMMwindow.setVisible(true);
	}

}
