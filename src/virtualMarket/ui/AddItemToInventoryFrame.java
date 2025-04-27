package virtualMarket.ui;
/*
import java.time.LocalDateTime;
import java.util.Date;

import virtualMarket.enums.*;
import java.awt.EventQueue;

import org.jdesktop.swingx.JXDatePicker;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class AddItemToInventoryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfItemName;
	private JTextField tfItemPrice;
	private JTextField tfItemAmount;
	private JTextField textField;
	public AddItemToInventoryFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblItemType = new JLabel("Item type:");
		lblItemType.setBounds(10, 26, 85, 14);
		contentPane.add(lblItemType);
		
		JComboBox cbItemType = new JComboBox();
		cbItemType.setModel(new DefaultComboBoxModel(new String[] {"Clothing", "Grocery", "Electronic"}));
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
		panelClothingItem.setBounds(10, 177, 319, 105);
		contentPane.add(panelClothingItem);
		panelClothingItem.setLayout(null);
		
		JLabel lblClothingType = new JLabel("Clothing Type:");
		lblClothingType.setBounds(10, 40, 85, 14);
		panelClothingItem.add(lblClothingType);
		
		JLabel lblF = new JLabel("Fabric Type:");
		lblF.setBounds(10, 10, 85, 14);
		panelClothingItem.add(lblF);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(100, 10, 203, 20);
		panelClothingItem.add(textField);
		
		JLabel lblItemAmount_1 = new JLabel("Item amount:");
		lblItemAmount_1.setBounds(10, 70, 85, 14);
		panelClothingItem.add(lblItemAmount_1);
		
		JComboBox<ClothingType> cbClothingType = new JComboBox(ClothingType.values());
		cbClothingType.setModel(new DefaultComboBoxModel(ClothingType.values()));
		cbClothingType.setSelectedIndex(0);
		cbClothingType.setBounds(100, 40, 203, 22);
		panelClothingItem.add(cbClothingType);
		
		JComboBox cbClothingType_1 = new JComboBox(new Object[]{});
		cbClothingType_1.setModel(new DefaultComboBoxModel(ClothingSize.values()));
		cbClothingType_1.setSelectedIndex(0);
		cbClothingType_1.setBounds(100, 70, 203, 22);
		panelClothingItem.add(cbClothingType_1);
		
		JLabel lblTypeOfItem = new JLabel("Clothing");
		lblTypeOfItem.setToolTipText("HAHAHAH");
		lblTypeOfItem.setBounds(10, 152, 85, 14);
		contentPane.add(lblTypeOfItem);
		
		JXDatePicker datePicker_1 = new JXDatePicker();
		datePicker_1.setBounds(20, 293, 112, 22);
		contentPane.add(datePicker_1);
	}
} */



import virtualMarket.enums.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.JButton;

public class AddItemToInventoryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfItemName;
	private JTextField tfItemPrice;
	private JTextField tfItemAmount;
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Create the frame.
	 */
	public AddItemToInventoryFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblItemType = new JLabel("Item type:");
		lblItemType.setBounds(10, 26, 85, 14);
		contentPane.add(lblItemType);
		
		JComboBox cbItemType = new JComboBox();
		cbItemType.setModel(new DefaultComboBoxModel(new String[] {"Clothing", "Grocery", "Electronic"}));
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
		panelClothingItem.setBounds(10, 177, 319, 105);
		contentPane.add(panelClothingItem);
		panelClothingItem.setLayout(null);
		
		JLabel lblClothingType = new JLabel("Clothing Type:");
		lblClothingType.setBounds(10, 40, 85, 14);
		panelClothingItem.add(lblClothingType);
		
		JLabel lblF = new JLabel("Fabric Type:");
		lblF.setBounds(10, 10, 85, 14);
		panelClothingItem.add(lblF);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(100, 10, 203, 20);
		panelClothingItem.add(textField);
		
		JLabel lblItemAmount_1 = new JLabel("Item Size:");
		lblItemAmount_1.setBounds(10, 70, 85, 14);
		panelClothingItem.add(lblItemAmount_1);
		
		JComboBox<ClothingType> cbClothingType = new JComboBox(ClothingType.values());
		cbClothingType.setModel(new DefaultComboBoxModel(ClothingType.values()));
		cbClothingType.setSelectedIndex(0);
		cbClothingType.setBounds(100, 40, 203, 22);
		panelClothingItem.add(cbClothingType);
		
		JComboBox cbClothingType_1 = new JComboBox(new Object[]{});
		cbClothingType_1.setModel(new DefaultComboBoxModel(ClothingSize.values()));
		cbClothingType_1.setSelectedIndex(0);
		cbClothingType_1.setBounds(100, 70, 203, 22);
		panelClothingItem.add(cbClothingType_1);
		
		JLabel lblTypeOfItem = new JLabel("Clothing");
		lblTypeOfItem.setToolTipText("HAHAHAH");
		lblTypeOfItem.setBounds(10, 152, 85, 14);
		contentPane.add(lblTypeOfItem);
		
		JPanel panelGroceryItem = new JPanel();
		panelGroceryItem.setLayout(null);
		panelGroceryItem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelGroceryItem.setBounds(10, 292, 319, 78);
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
		panelGroceryItem.add(datePicker);
		
		JPanel panelElectronicItem = new JPanel();
		panelElectronicItem.setLayout(null);
		panelElectronicItem.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelElectronicItem.setBounds(10, 381, 343, 105);
		contentPane.add(panelElectronicItem);
		
		JLabel lblWarrantyPeriod = new JLabel("Warranty Period");
		lblWarrantyPeriod.setBounds(10, 10, 85, 14);
		panelElectronicItem.add(lblWarrantyPeriod);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(105, 10, 203, 20);
		panelElectronicItem.add(textField_1);
		
		JLabel lblElectronicsType = new JLabel("Electronics Type:");
		lblElectronicsType.setBounds(10, 40, 85, 14);
		panelElectronicItem.add(lblElectronicsType);
		
		JComboBox cbGroceryType_1 = new JComboBox(new Object[]{});
		cbGroceryType_1.setModel(new DefaultComboBoxModel(ElectronicsType.values()));
		cbGroceryType_1.setSelectedIndex(0);
		cbGroceryType_1.setBounds(105, 40, 203, 22);
		panelElectronicItem.add(cbGroceryType_1);
		
		JLabel lblBrand = new JLabel("Brand:");
		lblBrand.setBounds(10, 70, 85, 14);
		panelElectronicItem.add(lblBrand);
		
		JComboBox cbGroceryType_1_1 = new JComboBox(new Object[]{});
		cbGroceryType_1_1.setModel(new DefaultComboBoxModel(ElectronicsBrand.values()));
		cbGroceryType_1_1.setSelectedIndex(0);
		cbGroceryType_1_1.setBounds(105, 70, 203, 22);
		panelElectronicItem.add(cbGroceryType_1_1);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(10, 497, 89, 23);
		contentPane.add(btnAddItem);
	}
}
