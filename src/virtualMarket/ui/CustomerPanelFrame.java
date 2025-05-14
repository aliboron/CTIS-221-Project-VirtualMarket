package virtualMarket.ui;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import virtualMarket.comparators.*;
import virtualMarket.customer.Customer;
import virtualMarket.customer.CustomerSys;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import virtualMarket.inventory.InventorySystem;
import virtualMarket.items.Item;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import java.util.Collection;
import virtualMarket.order.Order;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.util.Date;
import java.util.TreeSet;
import java.util.Calendar;

public class CustomerPanelFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField tfEmail;
	private JTextField tfPassword;
	private JButton btnGoBack;
	private JPanel ShoppingPanel;
	private JLabel lblCustomerInfo;
	private JButton btnAddToCart;
	
    private JButton btnReduceItemByOne;
    private JButton btnRemoveItem;
    private JScrollPane scrollPane_1;
    private JList<Item> listMarketItems;
    private JList<Item> listShoppingCart;
    private JComboBox<Customer> cbCustomers = new JComboBox<>();
    private DefaultListModel<Item> shoppingCartListModel;
    
    ManageCustomerFrame mcf = new ManageCustomerFrame(this);
	
	public CustomerPanelFrame(VMarketMainFrame vmmf) {
		CustomerPanelFrame frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
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
		ShoppingPanel.setBounds(0, 80, 884, 361);
		contentPanel.add(ShoppingPanel);
		ShoppingPanel.setLayout(null);
		
		JLabel lblYourCart = new JLabel("User Cart:");
		lblYourCart.setBounds(529, 61, 72, 14);
		ShoppingPanel.add(lblYourCart);
		
		JLabel lblItemList = new JLabel("Item List:");
		lblItemList.setBounds(33, 61, 72, 14);
		ShoppingPanel.add(lblItemList);
		
		btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBounds(386, 85, 110, 23);
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item selectedMarketItem = listMarketItems.getSelectedValue();
				Customer selectedCustomer = (Customer) cbCustomers.getSelectedItem();

				if (selectedMarketItem != null && selectedCustomer != null) {
					if (selectedMarketItem.getStock() > 0) {
						selectedCustomer.getShoppingCart().addItem(selectedMarketItem);
						updateShoppingCartList(selectedCustomer); // Update cart list
						updateMarketItemList(); // Update market list (for stock changes)
						
						try {
							InventorySystem.writeInventoryToFile(); // Save inventory to file
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(frame, "Error saving inventory: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Product is out of stock.", "Stock Warning", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					if (selectedCustomer == null) {
						JOptionPane.showMessageDialog(frame, "Please select a customer first.", "Customer Not Selected", JOptionPane.WARNING_MESSAGE);
					}
					if (selectedMarketItem == null) {
						JOptionPane.showMessageDialog(frame, "Please select an item from the market list.", "Item Not Selected", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		ShoppingPanel.add(btnAddToCart);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(529, 80, 320, 270);
		ShoppingPanel.add(scrollPane);
		
		shoppingCartListModel = new DefaultListModel<>(); 
		listShoppingCart = new JList<>(shoppingCartListModel);
		scrollPane.setViewportView(listShoppingCart);
		
		btnReduceItemByOne = new JButton("Reduce");
		btnReduceItemByOne.setBounds(386, 119, 110, 23);
		btnReduceItemByOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item selectedCartItem = listShoppingCart.getSelectedValue(); // Selected item from cart
				Customer selectedCustomer = (Customer) cbCustomers.getSelectedItem();

				if (selectedCartItem != null && selectedCustomer != null) {
					boolean success = selectedCustomer.getShoppingCart().reduceItemByOne(selectedCartItem.getId());
					if (success) {
						updateShoppingCartList(selectedCustomer);
						updateMarketItemList(); // Update market
						try {
							InventorySystem.writeInventoryToFile(); // Save inventory
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(frame, "Error saving inventory: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Could not reduce item. Item or its counterpart in inventory not found.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please select an item from your cart and ensure a customer is selected.", "Selection Missing", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		ShoppingPanel.add(btnReduceItemByOne);
		
		btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setBounds(386, 153, 110, 23);
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item selectedCartItem = listShoppingCart.getSelectedValue(); // Selected item from cart
				Customer selectedCustomer = (Customer) cbCustomers.getSelectedItem();

				if (selectedCartItem != null && selectedCustomer != null) {
					boolean success = selectedCustomer.getShoppingCart().removeItemCompletely(selectedCartItem.getId()); // Remove completely
					if (success) {
						updateShoppingCartList(selectedCustomer); // Update cart
						updateMarketItemList(); // Update market
						try {
							InventorySystem.writeInventoryToFile(); // Save inventory
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(frame, "Error saving inventory: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Could not remove item. Item not found in cart.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please select an item from your cart and ensure a customer is selected.", "Selection Missing", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		ShoppingPanel.add(btnRemoveItem);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(33, 80, 320, 270);
		ShoppingPanel.add(scrollPane_1);
		
		listMarketItems = new JList<>();
		scrollPane_1.setViewportView(listMarketItems);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(386, 187, 110, 23);
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer selectedCustomer = (Customer) cbCustomers.getSelectedItem();
				if (selectedCustomer == null) {
					JOptionPane.showMessageDialog(frame, "Please select a customer first.");
					return;
				}

				if (selectedCustomer.getShoppingCart().getItems().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Your shopping cart is empty.");
					return;
				}

				Order order = selectedCustomer.getShoppingCart().checkOut();

				if (order != null) { // Was order created?
					boolean writeSuccess = Order.writeOrderToFile(order); 
					if (writeSuccess) {
						JOptionPane.showMessageDialog(frame, "Checkout successful! Order ID: " + order.getId() + "\nOrder details saved.");
					} else {
						JOptionPane.showMessageDialog(frame, "Checkout successful but order details could not be saved to file. Order ID: " + order.getId());
					}
					updateShoppingCartList(selectedCustomer);
					updateMarketItemList();
				} else {
					JOptionPane.showMessageDialog(frame, "Checkout failed. Cart might be empty or an error occurred.");
				}
			}
		});
		ShoppingPanel.add(btnCheckout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(0, 0, 884, 40);
		ShoppingPanel.add(panel);
		panel.setLayout(null);
		
		lblCustomerInfo = new JLabel("Customer Info:");
		lblCustomerInfo.setBounds(10, 11, 864, 18);
		panel.add(lblCustomerInfo);
		
		JButton btnSortByID = new JButton("Sort by ID");
		btnSortByID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreeSet<Item> tree = new TreeSet();
				tree.addAll(InventorySystem.inventory);
				DefaultListModel<Item> marketModel = new DefaultListModel();
				marketModel.addAll(tree);
				listMarketItems.setModel(marketModel);
			}
		});
		btnSortByID.setBounds(381, 252, 120, 23);
		ShoppingPanel.add(btnSortByID);
		
		JButton btnSortByName = new JButton("Sort by Name");
		btnSortByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreeSet<Item> tree = new TreeSet(new ItemNameComparator());
				tree.addAll(InventorySystem.inventory);
				DefaultListModel<Item> marketModel = new DefaultListModel();
				marketModel.addAll(tree);
				listMarketItems.setModel(marketModel);
			}
		});
		btnSortByName.setBounds(381, 286, 120, 23);
		ShoppingPanel.add(btnSortByName);
		
		JButton btnSortByType = new JButton("Sort By Type");
		btnSortByType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreeSet<Item> tree = new TreeSet(new ItemTypeComparator());
				tree.addAll(InventorySystem.inventory);
				DefaultListModel<Item> marketModel = new DefaultListModel();
				marketModel.addAll(tree);
				listMarketItems.setModel(marketModel);
			}
		});
		btnSortByType.setBounds(381, 320, 120, 23);
		ShoppingPanel.add(btnSortByType);
		
		cbCustomers.setBounds(130, 11, 170, 22);
		contentPanel.add(cbCustomers);
		
		JLabel lblSelectCustomer = new JLabel("Select Customer:");
		lblSelectCustomer.setBounds(10, 15, 110, 14);
		contentPanel.add(lblSelectCustomer);
		
		JButton btnSelectCustomer = new JButton("Select");
		btnSelectCustomer.setBounds(310, 11, 89, 23);
		contentPanel.add(btnSelectCustomer);
		
		JButton btnAddButton = new JButton("Add Customer");
		btnAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mcf.setVisible(true);
				dispose();
			}
		});
		btnAddButton.setBounds(411, 11, 119, 23);
		contentPanel.add(btnAddButton);
		
		btnSelectCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer selectedCustomer = (Customer) cbCustomers.getSelectedItem(); 
				if (selectedCustomer != null) {
					lblCustomerInfo.setText("Customer Info: " + selectedCustomer.displayInfoUI());
					updateShoppingCartList(selectedCustomer);
					enableShoppingPanel();
				}
			}
		});
		
		disableShoppingPanel();
		
	}
	public JList<Item> getListMarketItems() {
		return listMarketItems;
	}

	// Disables the shopping panel and its components
	private void disableShoppingPanel() {
		ShoppingPanel.setEnabled(false);
		Component[] components = ShoppingPanel.getComponents();
		for (Component component : components) {
			component.setEnabled(false);
		}
		listMarketItems.setEnabled(false);
		listShoppingCart.setEnabled(false);
	}

	// Enables the shopping panel and its components
	private void enableShoppingPanel() {
		ShoppingPanel.setEnabled(true);
		Component[] components = ShoppingPanel.getComponents();
		for (Component component : components) {
			component.setEnabled(true);
		}
		listMarketItems.setEnabled(true);
		listShoppingCart.setEnabled(true);
	}

	private void updateShoppingCartList(Customer customer) {
		shoppingCartListModel.clear();
		if (customer != null && customer.getShoppingCart() != null) {
			Collection<Item> itemsFromCart = customer.getShoppingCart().getItems();

			for (Item displayItem : itemsFromCart) {
				shoppingCartListModel.addElement(displayItem);
			}
		}
	}

	private void updateMarketItemList() {
		if (listMarketItems.getModel() instanceof DefaultListModel) {
			DefaultListModel<Item> marketModel = new DefaultListModel<Item>();
			if (InventorySystem.inventory != null) {
				marketModel.addAll(InventorySystem.inventory);
			}
			listMarketItems.setModel(marketModel);
		}
	}
	

	public JComboBox<Customer> getCbCustomers() {
		return cbCustomers;
	}
	
	public void fillCustomerFrameItemList() {
		DefaultListModel<Item> listModel = new DefaultListModel<>();
		listModel.addAll(InventorySystem.inventory);
		getListMarketItems().setModel(listModel);
	}
	
	public void fillCustomerFrameCustomerCB() {
		DefaultComboBoxModel<Customer> model = new DefaultComboBoxModel();
		model.addAll(CustomerSys.customers);
		getCbCustomers().setModel(model);
	}
}