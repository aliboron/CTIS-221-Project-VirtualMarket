package virtualMarket.ui;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import virtualMarket.customer.Customer;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;


import javax.swing.JScrollPane;
import javax.swing.JComboBox;




public class CustomerPanelFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField tfEmail;
	private JTextField tfPassword;
	private JButton btnGoBack;
	private JPanel ShoppingPanel;
	private JLabel lblYourNameSurname;
	private JLabel lblNameSurname;
	private JLabel lblYourAddress;
	private JLabel lblAddress;
	private JButton btnAddToCart;
	
    private JButton btnReduceItemByOne;
    private JButton btnRemoveItem;
    private JScrollPane scrollPane_1;
    private JList listMarketItems;
    private JList listShoppingCart;
    private JComboBox cbCustomers= new JComboBox();
    ManageCustomerFrame mcf = new ManageCustomerFrame(this);
    
	/**
	 * Create the frame.
	 */
	
	public CustomerPanelFrame(VMarketMainFrame vmmf) {
		CustomerPanelFrame frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 550);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		btnGoBack = new JButton("<--");
		btnGoBack.setBounds(10, 477, 89, 23);
		contentPanel.add(btnGoBack);
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vmmf.setVisible(true);
				dispose();
			}
		});
		
		ShoppingPanel = new JPanel();
		ShoppingPanel.setBounds(0, 80, 584, 361);
		contentPanel.add(ShoppingPanel);
		ShoppingPanel.setLayout(null);
		
		lblYourNameSurname = new JLabel("Your name/surname:");
		lblYourNameSurname.setBounds(320, 11, 122, 14);
		ShoppingPanel.add(lblYourNameSurname);
		
		lblNameSurname = new JLabel("NAME SURNAME");
		lblNameSurname.setBounds(452, 11, 122, 14);
		ShoppingPanel.add(lblNameSurname);
		
		lblYourAddress = new JLabel("Your address:");
		lblYourAddress.setBounds(320, 36, 122, 14);
		ShoppingPanel.add(lblYourAddress);
		
		lblAddress = new JLabel("ADDRESS");
		lblAddress.setBounds(452, 36, 122, 14);
		ShoppingPanel.add(lblAddress);
		
		JLabel lblYourCart = new JLabel("Your Cart:");
		lblYourCart.setBounds(360, 57, 72, 14);
		ShoppingPanel.add(lblYourCart);
		
		JLabel lblItemList = new JLabel("Item List:");
		lblItemList.setBounds(10, 57, 72, 14);
		ShoppingPanel.add(lblItemList);
		
		btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBounds(245, 99, 100, 23);
		ShoppingPanel.add(btnAddToCart);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(360, 80, 215, 270);
		ShoppingPanel.add(scrollPane);
		
		listShoppingCart = new JList();
		scrollPane.setViewportView(listShoppingCart);
		
		btnReduceItemByOne = new JButton("Reduce One");
		btnReduceItemByOne.setBounds(245, 133, 100, 23);
		ShoppingPanel.add(btnReduceItemByOne);
		
		btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setBounds(245, 166, 100, 23);
		ShoppingPanel.add(btnRemoveItem);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 80, 215, 270);
		ShoppingPanel.add(scrollPane_1);
		
		listMarketItems = new JList();
		scrollPane_1.setViewportView(listMarketItems);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(245, 200, 100, 23);
		ShoppingPanel.add(btnCheckout);
		
		cbCustomers.setBounds(130, 11, 170, 22);
		contentPanel.add(cbCustomers);
		
		JLabel lblSelectCustomer = new JLabel("Select Customer:");
		lblSelectCustomer.setBounds(10, 15, 110, 14);
		contentPanel.add(lblSelectCustomer);
		
		JButton btnSelectCustomer = new JButton("Select");
		btnSelectCustomer.setBounds(310, 11, 89, 23);
		contentPanel.add(btnSelectCustomer);
		
		JButton btnNewButton = new JButton("Add Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mcf.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(410, 11, 121, 23);
		contentPanel.add(btnNewButton);
		
		disableShoppingPanel();
		
	}
	public JList getListMarketItems() {
		return listMarketItems;
	}

	private void disableShoppingPanel() {
		
		// OR Option 2: Disable all components individually
		Component[] components = ShoppingPanel.getComponents();
		for (Component component : components) {
			component.setEnabled(false);
		}
		listMarketItems.setEnabled(false);
		listShoppingCart.setEnabled(false);
	}
	
	

	public JComboBox getCbCustomers() {
		return cbCustomers;
	}
}
