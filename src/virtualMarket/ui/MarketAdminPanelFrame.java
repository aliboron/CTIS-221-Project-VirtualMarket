package virtualMarket.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import virtualMarket.inventory.InventorySystem;
import virtualMarket.items.Item;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class MarketAdminPanelFrame extends JFrame {

	private JPanel contentPane;
	private JTable inventoryTable;
	private ManageInventoryFrame mif;
	private JTextField tfStock;
	MarketAdminPanelFrame thisFrame;

	/**
	 * Pencereyi olusturur.
	 */
	public MarketAdminPanelFrame(VMarketMainFrame vmmf) {

		thisFrame = this;
		mif =new ManageInventoryFrame(this);

		MarketAdminPanelFrame frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 740, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGoBack = new JButton("<--");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vmmf.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setBounds(10, 11, 89, 23);
		contentPane.add(btnGoBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 452, 505);
		contentPane.add(scrollPane);
		
		inventoryTable = new JTable();
		updateInventoryTable(InventorySystem.inventory);
		scrollPane.setViewportView(inventoryTable);
		
		JButton btnAddItemtoInventory = new JButton("Add Item to Inventory");
		btnAddItemtoInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mif == null) {
					mif = new ManageInventoryFrame(frame);
				}
				mif.setVisible(true);
				dispose();
			}
		});
		btnAddItemtoInventory.setBounds(472, 45, 220, 23);
		contentPane.add(btnAddItemtoInventory);
		
		JTextArea taFeedback = new JTextArea();
		taFeedback.setLineWrap(true);
		taFeedback.setBackground(UIManager.getColor("Label.background"));
		taFeedback.setEditable(false);
		taFeedback.setBounds(472, 144, 242, 100);
		contentPane.add(taFeedback);
		
		JButton btnAddStock = new JButton("Add stock");
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int stockToAdd;
				try {
					stockToAdd = Integer.parseInt(tfStock.getText());
				} catch (NumberFormatException ex) {
					taFeedback.setForeground(new Color(180,0,0));
					taFeedback.setText("Please be sure to fill all fields with appropriate content!\nCause: " + ex.getMessage());
					return;
				}
				String itemId;
				try {
					itemId = (String) inventoryTable.getModel().getValueAt(inventoryTable.getSelectedRow(), 0);
				} catch(Exception ex) {
					taFeedback.setForeground(new Color(180,0,0));
					taFeedback.setText("Please choose an item from the list! "  + ex.getMessage());
					return;
				}
					
				Item item = InventorySystem.searchItem(itemId);
				if (item != null)
					item.setStock(item.getStock() + stockToAdd);
				
				updateInventoryTable(InventorySystem.inventory);
				taFeedback.setForeground(new Color(0,180,0));
				taFeedback.setText(item.getName() + "'s stock has been set successfully!");
			}
		});
		btnAddStock.setBounds(472, 110, 220, 23);
		contentPane.add(btnAddStock);
		
		JLabel lblAddStock = new JLabel("Add Stock:");
		lblAddStock.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddStock.setBounds(472, 79, 81, 20);
		contentPane.add(lblAddStock);
		
		tfStock = new JTextField();
		tfStock.setBounds(556, 79, 136, 20);
		contentPane.add(tfStock);
		tfStock.setColumns(10);
		

		
	}
	
	public void updateInventoryTable(List<Item> listItems) {
	    DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	    

	    tableModel.addColumn("ID");
	    tableModel.addColumn("Product Name"); 
	    tableModel.addColumn("Stock");
	    tableModel.addColumn("Price"); 
	    
	    if (listItems != null) {
			for (Item item : listItems) {
				Object[] rowData = new Object[4];
				rowData[0] = item.getId();
				rowData[1] = item.getName();
				rowData[2] = item.getStock(); 
				rowData[3] = String.format("%.2f ₺", item.getPrice());
				
				tableModel.addRow(rowData);
			}
		}
	    
	    inventoryTable.setModel(tableModel);
	    
	    inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(50);
	    inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(150);
	    inventoryTable.getColumnModel().getColumn(2).setPreferredWidth(40);
		inventoryTable.getColumnModel().getColumn(3).setPreferredWidth(60);
	    
	    inventoryTable.setAutoCreateRowSorter(true);
	}
}
