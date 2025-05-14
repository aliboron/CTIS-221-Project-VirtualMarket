package virtualMarket.ui;

import virtualMarket.enums.*;
import virtualMarket.inventory.InventorySystem;
import virtualMarket.items.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class ManageInventoryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfItemName;
	private JTextField tfItemPrice;
	private JTextField tfItemAmount;
	private JTextField tfWarrantyPeriod;


	/**
	 * Create the frame.
	 */
	public ManageInventoryFrame(MarketAdminPanelFrame mapf) {
		ManageInventoryFrame thisFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblItemType = new JLabel("Item type:");
		lblItemType.setBounds(10, 26, 85, 14);
		contentPane.add(lblItemType);
		
		JComboBox cbItemType = new JComboBox();
		cbItemType.setModel(new DefaultComboBoxModel(ItemType.values()));
		cbItemType.setSelectedIndex(0);
		cbItemType.setBounds(105, 22, 203, 22);
		contentPane.add(cbItemType);
		
		JLabel lblItemName = new JLabel("Item name:");
		lblItemName.setBounds(10, 55, 85, 14);
		contentPane.add(lblItemName);
		
		tfItemName = new JTextField();
		tfItemName.setBounds(105, 55, 203, 20);
		contentPane.add(tfItemName);
		tfItemName.setColumns(10);
		
		JLabel lblItemPrice = new JLabel("Item price:");
		lblItemPrice.setBounds(10, 86, 85, 14);
		contentPane.add(lblItemPrice);
		
		tfItemPrice = new JTextField();
		tfItemPrice.setColumns(10);
		tfItemPrice.setBounds(105, 86, 203, 20);
		contentPane.add(tfItemPrice);
		
		JLabel lblItemAmount = new JLabel("Item amount:");
		lblItemAmount.setBounds(10, 118, 85, 14);
		contentPane.add(lblItemAmount);
		
		tfItemAmount = new JTextField();
		tfItemAmount.setColumns(10);
		tfItemAmount.setBounds(105, 118, 203, 20);
		contentPane.add(tfItemAmount);
		
		JPanel panelClothingItem = new JPanel();
		panelClothingItem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelClothingItem.setBounds(10, 180, 350, 105);
		contentPane.add(panelClothingItem);
		panelClothingItem.setLayout(null);
		panelClothingItem.setVisible(false);
		
		JLabel lblClothingType = new JLabel("Clothing Type:");
		lblClothingType.setBounds(10, 40, 85, 14);
		panelClothingItem.add(lblClothingType);
		
		JLabel lblFabricType = new JLabel("Fabric Type:");
		lblFabricType.setBounds(10, 10, 85, 14);
		panelClothingItem.add(lblFabricType);
		
		JLabel lblItemAmount_1 = new JLabel("Item Size:");
		lblItemAmount_1.setBounds(10, 70, 85, 14);
		panelClothingItem.add(lblItemAmount_1);
		
		JComboBox<ClothingType> cbClothingType = new JComboBox(ClothingType.values());
		cbClothingType.setModel(new DefaultComboBoxModel(ClothingType.values()));
		cbClothingType.setSelectedIndex(0);
		cbClothingType.setBounds(100, 40, 203, 22);
		panelClothingItem.add(cbClothingType);
		
		JComboBox cbClothingSize = new JComboBox(new Object[]{});
		cbClothingSize.setModel(new DefaultComboBoxModel(ClothingSize.values()));
		cbClothingSize.setSelectedIndex(0);
		cbClothingSize.setBounds(100, 70, 203, 22);
		panelClothingItem.add(cbClothingSize);
		
		JComboBox cbClothingFabric = new JComboBox(new Object[]{});
		cbClothingFabric.setModel(new DefaultComboBoxModel(ClothingFabricType.values()));
		cbClothingFabric.setSelectedIndex(0);
		cbClothingFabric.setBounds(100, 7, 203, 22);
		panelClothingItem.add(cbClothingFabric);
		
		JLabel lblTypeOfItem = new JLabel("Clothing");
		lblTypeOfItem.setToolTipText("HAHAHAH");
		lblTypeOfItem.setBounds(10, 152, 85, 14);
		contentPane.add(lblTypeOfItem);
		
		JPanel panelGroceryItem = new JPanel();
		panelGroceryItem.setLayout(null);
		panelGroceryItem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelGroceryItem.setBounds(10, 180, 350, 80);
		contentPane.add(panelGroceryItem);
		
		JLabel lblGroceryType = new JLabel("Grocery Type:");
		lblGroceryType.setBounds(10, 40, 85, 14);
		panelGroceryItem.add(lblGroceryType);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date:");
		lblExpiryDate.setBounds(10, 10, 85, 14);
		panelGroceryItem.add(lblExpiryDate);
		
		JComboBox cbGroceryType = new JComboBox(new Object[]{});
		cbGroceryType.setModel(new DefaultComboBoxModel(GroceryType.values()));
		cbGroceryType.setSelectedIndex(0);
		cbGroceryType.setBounds(100, 40, 203, 22);
		panelGroceryItem.add(cbGroceryType);
		
		JXDatePicker datePicker = new JXDatePicker();
		datePicker.setBounds(100, 10, 120, 24);
		datePicker.setDate(new Date());
		panelGroceryItem.add(datePicker);
		
		JPanel panelElectronicItem = new JPanel();
		panelElectronicItem.setVisible(false);
		panelElectronicItem.setLayout(null);
		panelElectronicItem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelElectronicItem.setBounds(10, 180, 350, 105);
		contentPane.add(panelElectronicItem);
		
		JLabel lblWarrantyPeriod = new JLabel("Warranty Period");
		lblWarrantyPeriod.setBounds(10, 10, 85, 14);
		panelElectronicItem.add(lblWarrantyPeriod);
		
		tfWarrantyPeriod = new JTextField();
		tfWarrantyPeriod.setColumns(10);
		tfWarrantyPeriod.setBounds(105, 10, 203, 20);
		panelElectronicItem.add(tfWarrantyPeriod);
		
		JLabel lblElectronicsType = new JLabel("Electronics Type:");
		lblElectronicsType.setBounds(10, 40, 85, 14);
		panelElectronicItem.add(lblElectronicsType);
		
		JComboBox cbElectronicsType = new JComboBox(new Object[]{});
		cbElectronicsType.setModel(new DefaultComboBoxModel(ElectronicsType.values()));
		cbElectronicsType.setSelectedIndex(0);
		cbElectronicsType.setBounds(105, 40, 203, 22);
		panelElectronicItem.add(cbElectronicsType);
		
		JLabel lblBrand = new JLabel("Brand:");
		lblBrand.setBounds(10, 70, 85, 14);
		panelElectronicItem.add(lblBrand);
		
		JComboBox cbElectronicsBrand = new JComboBox(new Object[]{});
		cbElectronicsBrand.setModel(new DefaultComboBoxModel(ElectronicsBrand.values()));
		cbElectronicsBrand.setSelectedIndex(0);
		cbElectronicsBrand.setBounds(105, 70, 203, 22);
		panelElectronicItem.add(cbElectronicsBrand);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(10, 370, 140, 23);
		contentPane.add(btnAddItem);
		
		JButton btnGoBack = new JButton("<--");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapf.updateInventoryTable(InventorySystem.inventory);
				mapf.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setBounds(335, 370, 89, 23);
		contentPane.add(btnGoBack);
		
		JTextArea taFeedback = new JTextArea();
		taFeedback.setForeground(new Color(0, 120, 215));
		taFeedback.setBackground(SystemColor.control);
		taFeedback.setEditable(false);
		taFeedback.setBounds(10, 296, 414, 63);
		contentPane.add(taFeedback);
		
		cbItemType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ItemType.CLOTHING==cbItemType.getSelectedItem()) {
					lblTypeOfItem.setText("Clothing");
					panelClothingItem.setVisible(true);
					panelElectronicItem.setVisible(false);
					panelGroceryItem.setVisible(false);
					
				}
				else if(ItemType.ELECTRONIC==cbItemType.getSelectedItem()) {
					lblTypeOfItem.setText("Electronic");
					panelClothingItem.setVisible(false);
					panelElectronicItem.setVisible(true);
					panelGroceryItem.setVisible(false);
				}
				else if(ItemType.GROCERY==cbItemType.getSelectedItem()) {
					lblTypeOfItem.setText("Grocery");
					panelClothingItem.setVisible(false);
					panelElectronicItem.setVisible(false);
					panelGroceryItem.setVisible(true);
				}
				
			}
		});
		
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (InventorySystem.searchItemByName(tfItemName.getText())) {
					int reply = JOptionPane.showConfirmDialog(thisFrame, "There is already an item with the same name! You could increase the stock in the previous window. Do you want to continue?", "Duplicate Name Warning", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.NO_OPTION)
						return;
				}
				
				Item item;
				try {
					if (cbItemType.getSelectedItem() == ItemType.GROCERY) {
						ZoneId zoneId = ZoneId.systemDefault();
						LocalDateTime ldt= LocalDateTime.ofInstant(datePicker.getDate().toInstant(), zoneId);
						
						item = new GroceryItem(tfItemName.getText(), Double.parseDouble(tfItemPrice.getText()), Integer.parseInt(tfItemAmount.getText()), ldt, (GroceryType) cbGroceryType.getSelectedItem());

					} else if (cbItemType.getSelectedItem() == ItemType.CLOTHING) {
						item = new ClothingItem(tfItemName.getText(), Double.parseDouble(tfItemPrice.getText()), Integer.parseInt(tfItemAmount.getText()), (ClothingType) cbClothingType.getSelectedItem(), (ClothingSize) cbClothingSize.getSelectedItem(), (ClothingFabricType) cbClothingFabric.getSelectedItem());
					} else {
						item = new ElectronicItem(tfItemName.getText(), Double.parseDouble(tfItemPrice.getText()), Integer.parseInt(tfItemAmount.getText()), Integer.parseInt(tfWarrantyPeriod.getText()), (ElectronicsType) cbElectronicsType.getSelectedItem(), (ElectronicsBrand) cbElectronicsBrand.getSelectedItem());
					}
					if (InventorySystem.addUsedIDAndWrite(item.generateID(InventorySystem.usedIds)))
						JOptionPane.showMessageDialog(thisFrame, "Generated id written to file successfuly!");
					else
						JOptionPane.showMessageDialog(thisFrame, "Unsuccessful id operation");
				} catch(NumberFormatException ex) {
					taFeedback.setForeground(new Color(255,0,0));
					taFeedback.setText("Please be sure to fill all fields with appropriate content!\nCause: " + ex.getMessage());
					return;
				}
				
				if(InventorySystem.addItem(item)) {
					taFeedback.setForeground(new Color(0,190,0));
					taFeedback.setText("New item added succesfully!\n"+item.toString());					
				}
				else {					
					taFeedback.setForeground(new Color(255,0,0));
					taFeedback.setText("Failed to add new item!");
				}
				try {
					InventorySystem.writeInventoryToFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}