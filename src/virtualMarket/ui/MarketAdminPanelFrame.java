package virtualMarket.ui;

import java.awt.EventQueue;

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

public class MarketAdminPanelFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable inventoryTable;
	private AddItemToInventoryFrame aitiFrame = new AddItemToInventoryFrame(this);


	/**
	 * Create the frame.
	 */
	public MarketAdminPanelFrame(VMarketMainFrame vmmf) {
		MarketAdminPanelFrame frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("<--");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vmmf.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 250, 505);
		contentPane.add(scrollPane);
		
		inventoryTable = new JTable();
		updateInventoryTable(InventorySystem.inventory);
		scrollPane.setViewportView(inventoryTable);
		
		JButton btnAddItemtoInventory = new JButton("Add Item to Inventory");
		btnAddItemtoInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aitiFrame.setVisible(true);
				dispose();
			}
		});
		btnAddItemtoInventory.setBounds(406, 48, 191, 23);
		contentPane.add(btnAddItemtoInventory);
		
	}
	
	public void updateInventoryTable(ArrayList<Item> listItems) {
	    // Create a table model with the appropriate column names
	    DefaultTableModel tableModel = new DefaultTableModel() {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Make table read-only
	        }
	    };
	    
	    // Add columns
	    tableModel.addColumn("ID");
	    tableModel.addColumn("Item Name");
	    tableModel.addColumn("Stock");
	    
	    // Add row data from the listItems
	    for (Item item : listItems) {
	        Object[] rowData = new Object[3];
	        rowData[0] = item.getId();
	        rowData[1] = item.getName();
	        rowData[2] = item.getStock(); // Assuming Item has getStock() method
	        
	        tableModel.addRow(rowData);
	    }
	    
	    // Apply the model to your table
	    inventoryTable.setModel(tableModel);
	    
	    // Optional: Adjust column widths
	    inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(60);  // ID
	    inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Name
	    inventoryTable.getColumnModel().getColumn(2).setPreferredWidth(60);  // Stock
	    
	    // Optional: Sort functionality
	    inventoryTable.setAutoCreateRowSorter(true);
	}
}
