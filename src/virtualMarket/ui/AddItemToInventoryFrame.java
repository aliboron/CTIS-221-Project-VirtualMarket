package virtualMarket.ui;

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

public class AddItemToInventoryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfItemName;
	private JTextField tfItemPrice;
	private JTextField tfItemAmount;
	private JTextField textField;


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
	}
}
