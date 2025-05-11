package virtualMarket.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import virtualMarket.inventory.InventorySystem;
import virtualMarket.items.Item;

// Market admin paneli icin pencere sinifi
public class MarketAdminPanelFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // Ana panel
	private JTable inventoryTable; // Envanterin gosterilecegi tablo
	private ManageInventoryFrame aitiFrame; // Urun ekleme/duzenleme penceresi (aitiframe = Add Item To Inventory Frame)


	/**
	 * Pencereyi olusturur.
	 */
	public MarketAdminPanelFrame(VMarketMainFrame vmmf) { // Ana pencereyi parametre alir
		// aitiFrame'i burada initialize edelim ki null olmasin
		// this referansini constructor icinde kullanmak icin once this'in olusmasi lazim
		// Bu yuzden aitiFrame'i buton action'inda veya burada null check ile olusturabiliriz.
		// Simdilik constructor'da birakiyorum ama dikkatli olmak lazim.
		// En guzeli, aitiFrame'i ilk defa lazim oldugunda olusturmak (lazy initialization).
		// this.aitiFrame = new ManageInventoryFrame(this); // Bu satir ManageInventoryFrame'e this gonderdigi icin sorun olabilir.
		// Asagidaki gibi yapalim:
		if (this.aitiFrame == null) {
			this.aitiFrame = new ManageInventoryFrame(this);
		}


		MarketAdminPanelFrame frame = this; // ActionListener icinde frame'e ulasmak icin
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Kapatinca program dursun
		setBounds(100, 100, 900, 600); // Pencere boyutu ve konumu
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Kenar bosluklari

		setContentPane(contentPane);
		contentPane.setLayout(null); // Elle yerlesim
		
		JButton btnGoBack = new JButton("<--"); // Geri butonu
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vmmf.setVisible(true); // Ana pencereyi goster
				dispose(); // Bu pencereyi kapat
			}
		});
		btnGoBack.setBounds(10, 11, 89, 23);
		contentPane.add(btnGoBack);
		
		JScrollPane scrollPane = new JScrollPane(); // Tablo icin kaydirma cubugu
		scrollPane.setBounds(10, 45, 350, 505); // Genisligi artirdim biraz
		contentPane.add(scrollPane);
		
		inventoryTable = new JTable(); // Envanter tablosu
		updateInventoryTable(InventorySystem.inventory); // Tabloyu envanterdeki urunlerle doldur
		scrollPane.setViewportView(inventoryTable);
		
		JButton btnAddItemtoInventory = new JButton("Add/Edit Item in Inventory"); // İngilizce'ye çevrildi
		btnAddItemtoInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (aitiFrame == null) { // Eger yukarida olusturulmadiysa burada olustur
					aitiFrame = new ManageInventoryFrame(frame); // frame (this) referansini veriyoruz
				}
				aitiFrame.setVisible(true); // Urun ekleme penceresini goster
				// dispose(); // Bu pencereyi kapatmayalim, urun ekleme penceresi ayri calissin
				frame.setVisible(false); // Admin panelini gizleyelim, urun ekleme bitince geri donulebilir
			}
		});
		btnAddItemtoInventory.setBounds(400, 48, 220, 23); // Buton adina gore genislettim
		contentPane.add(btnAddItemtoInventory);
		
	}
	
	// Envanter tablosunu gunceller
	public void updateInventoryTable(ArrayList<Item> listItems) {
	    // Tablo modeli olustur, hucreler duzenlenemesin
	    DefaultTableModel tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L; // warning icin
			@Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Tablo sadece okunur olsun
	        }
	    };
	    
	    // Sutun basliklarini ekle
	    tableModel.addColumn("ID");
	    tableModel.addColumn("Product Name"); // İngilizce'ye çevrildi
	    tableModel.addColumn("Stock");
	    tableModel.addColumn("Price"); 
	    
	    // Satir verilerini urun listesinden ekle
	    if (listItems != null) { // liste null degilse
			for (Item item : listItems) {
				Object[] rowData = new Object[4]; // 4 sutun var artik
				rowData[0] = item.getId();
				rowData[1] = item.getName();
				rowData[2] = item.getStock(); 
				rowData[3] = String.format("%.2f ₺", item.getPrice()); // Fiyati formatli ekle
				
				tableModel.addRow(rowData);
			}
		}
	    
	    inventoryTable.setModel(tableModel); // Modeli tabloya uygula
	    
	    // Istege bagli: Sutun genisliklerini ayarla
	    inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
	    inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Product Name
	    inventoryTable.getColumnModel().getColumn(2).setPreferredWidth(40);  // Stock
		inventoryTable.getColumnModel().getColumn(3).setPreferredWidth(60);  // Price
	    
	    inventoryTable.setAutoCreateRowSorter(true); // Tabloya tiklayinca siralama ozelligi ekle
	}
}
