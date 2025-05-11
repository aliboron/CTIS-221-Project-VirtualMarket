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
import javax.swing.JOptionPane;
import virtualMarket.inventory.InventorySystem;
import virtualMarket.items.Item;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import virtualMarket.order.Order;

public class CustomerPanelFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField tfEmail;
	private JTextField tfPassword;
	private JButton btnGoBack; // Geri butonu
	private JPanel ShoppingPanel; // Alışveriş paneli
	private JLabel lblYourNameSurname;
	private JLabel lblNameSurname; // Müşterinin adı soyadı
	private JLabel lblYourAddress;
	private JLabel lblAddress; // Müşterinin adresi
	private JButton btnAddToCart; // Sepete ekle butonu
	
    private JButton btnReduceItemByOne; // Sepetten bir azalt butonu
    private JButton btnRemoveItem; // Sepetten tamamen kaldır butonu
    private JScrollPane scrollPane_1; // Market listesi için kaydırma çubuğu
    private JList<Item> listMarketItems; // Market ürünlerinin listesi
    private JList<Item> listShoppingCart; // Alışveriş sepetinin listesi
    private JComboBox<Customer> cbCustomers = new JComboBox<>(); // Müşteri seçim kutusu
    private DefaultListModel<Item> shoppingCartListModel; // Sepet listesinin modeli
    
    ManageCustomerFrame mcf = new ManageCustomerFrame(this);
	
	public CustomerPanelFrame(VMarketMainFrame vmmf) { // Ana pencereyi parametre alıyor
		CustomerPanelFrame frame = this; // ActionListener içinde frame'e ulaşmak için
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Kapatınca program dursun
		setBounds(100, 100, 600, 550); // Pencere boyutu ve konumu
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Kenar boşlukları

		setContentPane(contentPanel);
		contentPanel.setLayout(null); // Layout manager kullanmıyoruz, her şeyi elle yerleştiriyoruz
		
		btnGoBack = new JButton("<--"); // Geri butonu
		btnGoBack.setBounds(10, 477, 89, 23);
		contentPanel.add(btnGoBack);
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vmmf.setVisible(true); // Ana pencereyi göster
				dispose(); // Bu pencereyi kapat
			}
		});
		
		ShoppingPanel = new JPanel(); // Alışveriş paneli
		ShoppingPanel.setBounds(0, 80, 584, 361);
		contentPanel.add(ShoppingPanel);
		ShoppingPanel.setLayout(null);
		
		lblYourNameSurname = new JLabel("Your Name/Surname:"); // İngilizce'ye çevrildi
		lblYourNameSurname.setBounds(320, 11, 140, 14); // Genişlik ayarlandı
		ShoppingPanel.add(lblYourNameSurname);
		
		lblNameSurname = new JLabel("NAME SURNAME"); // Buraya seçilen müşterinin adı gelecek
		lblNameSurname.setBounds(452, 11, 122, 14);
		ShoppingPanel.add(lblNameSurname);
		
		lblYourAddress = new JLabel("Your Address:"); // İngilizce'ye çevrildi
		lblYourAddress.setBounds(320, 36, 122, 14);
		ShoppingPanel.add(lblYourAddress);
		
		lblAddress = new JLabel("ADDRESS"); // Buraya seçilen müşterinin adresi gelecek
		lblAddress.setBounds(452, 36, 122, 14);
		ShoppingPanel.add(lblAddress);
		
		JLabel lblYourCart = new JLabel("Your Cart:"); // İngilizce'ye çevrildi
		lblYourCart.setBounds(360, 57, 72, 14);
		ShoppingPanel.add(lblYourCart);
		
		JLabel lblItemList = new JLabel("Item List:"); // İngilizce'ye çevrildi
		lblItemList.setBounds(10, 57, 72, 14);
		ShoppingPanel.add(lblItemList);
		
		btnAddToCart = new JButton("Add to Cart"); // İngilizce'ye çevrildi
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item selectedMarketItem = listMarketItems.getSelectedValue(); // Marketten seçilen ürün
				Customer selectedCustomer = (Customer) cbCustomers.getSelectedItem(); // Seçili müşteri

				if (selectedMarketItem != null && selectedCustomer != null) { // Ürün ve müşteri seçili mi?
					if (selectedMarketItem.getStock() > 0) { // Stokta var mı?
						selectedCustomer.getShoppingCart().addItem(selectedMarketItem); // Sepete ekle
						updateShoppingCartList(selectedCustomer); // Sepet listesini güncelle
						updateMarketItemList(); // Market listesini güncelle (stok değişimi için)
						
						try {
							InventorySystem.writeInventoryToFile(); // Envanteri dosyaya kaydet
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(frame, "Error saving inventory: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE); // İngilizce'ye çevrildi
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Product is out of stock.", "Stock Warning", JOptionPane.WARNING_MESSAGE); // İngilizce'ye çevrildi
					}
				} else {
					if (selectedCustomer == null) {
						JOptionPane.showMessageDialog(frame, "Please select a customer first.", "Customer Not Selected", JOptionPane.WARNING_MESSAGE); // İngilizce'ye çevrildi
					}
					if (selectedMarketItem == null) {
						JOptionPane.showMessageDialog(frame, "Please select an item from the market list.", "Item Not Selected", JOptionPane.WARNING_MESSAGE); // İngilizce'ye çevrildi
					}
				}
			}
		});
		btnAddToCart.setBounds(245, 99, 110, 23); // Genişlik ayarlandı
		ShoppingPanel.add(btnAddToCart);
		
		JScrollPane scrollPane = new JScrollPane(); // Sepet listesi için kaydırma çubuğu
		scrollPane.setBounds(360, 80, 215, 270);
		ShoppingPanel.add(scrollPane);
		
		shoppingCartListModel = new DefaultListModel<>(); // Modeli başlat
		listShoppingCart = new JList<>(shoppingCartListModel); // Modeli kullan
		scrollPane.setViewportView(listShoppingCart);
		
		btnReduceItemByOne = new JButton("Reduce One"); // İngilizce'ye çevrildi
		btnReduceItemByOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item selectedCartItem = listShoppingCart.getSelectedValue(); // Sepetten seçilen ürün
				Customer selectedCustomer = (Customer) cbCustomers.getSelectedItem();

				if (selectedCartItem != null && selectedCustomer != null) {
					boolean success = selectedCustomer.getShoppingCart().reduceItemByOne(selectedCartItem.getId()); // Sepetten bir azalt
					if (success) {
						updateShoppingCartList(selectedCustomer); // Sepeti güncelle
						updateMarketItemList(); // Marketi güncelle
						try {
							InventorySystem.writeInventoryToFile(); // Envanteri kaydet
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(frame, "Error saving inventory: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE); // İngilizce'ye çevrildi
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Could not reduce item. Item or its counterpart in inventory not found.", "Error", JOptionPane.ERROR_MESSAGE); // İngilizce'ye çevrildi
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please select an item from your cart and ensure a customer is selected.", "Selection Missing", JOptionPane.WARNING_MESSAGE); // İngilizce'ye çevrildi
				}
			}
		});
		btnReduceItemByOne.setBounds(245, 133, 110, 23); // Genişlik ayarlandı
		ShoppingPanel.add(btnReduceItemByOne);
		
		btnRemoveItem = new JButton("Remove Item"); // İngilizce'ye çevrildi
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item selectedCartItem = listShoppingCart.getSelectedValue(); // Sepetten seçilen ürün
				Customer selectedCustomer = (Customer) cbCustomers.getSelectedItem();

				if (selectedCartItem != null && selectedCustomer != null) {
					boolean success = selectedCustomer.getShoppingCart().removeItemCompletely(selectedCartItem.getId()); // Tamamen kaldır
					if (success) {
						updateShoppingCartList(selectedCustomer); // Sepeti güncelle
						updateMarketItemList(); // Marketi güncelle
						try {
							InventorySystem.writeInventoryToFile(); // Envanteri kaydet
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(frame, "Error saving inventory: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE); // İngilizce'ye çevrildi
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Could not remove item. Item not found in cart.", "Error", JOptionPane.ERROR_MESSAGE); // İngilizce'ye çevrildi
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please select an item from your cart and ensure a customer is selected.", "Selection Missing", JOptionPane.WARNING_MESSAGE); // İngilizce'ye çevrildi
				}
			}
		});
		btnRemoveItem.setBounds(245, 166, 110, 23); // Genişlik ayarlandı
		ShoppingPanel.add(btnRemoveItem);
		
		scrollPane_1 = new JScrollPane(); // Market listesi için kaydırma çubuğu
		scrollPane_1.setBounds(10, 80, 215, 270);
		ShoppingPanel.add(scrollPane_1);
		
		listMarketItems = new JList<>(); // Varsayılan modelle başlat veya daha sonra ayarla
		scrollPane_1.setViewportView(listMarketItems);
		
		JButton btnCheckout = new JButton("Checkout"); // İngilizce'ye çevrildi
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer selectedCustomer = (Customer) cbCustomers.getSelectedItem();
				if (selectedCustomer == null) {
					JOptionPane.showMessageDialog(frame, "Please select a customer first.", "Customer Not Selected", JOptionPane.WARNING_MESSAGE); // İngilizce'ye çevrildi
					return;
				}

				if (selectedCustomer.getShoppingCart().getItems().isEmpty()) { // Sepet boş mu?
					JOptionPane.showMessageDialog(frame, "Your shopping cart is empty.", "Empty Cart", JOptionPane.INFORMATION_MESSAGE); // İngilizce'ye çevrildi
					return;
				}

				Order order = selectedCustomer.getShoppingCart().checkOut(); // Ödeme yap

				if (order != null) { // Sipariş oluştu mu?
					boolean writeSuccess = Order.writeOrderToFile(order); // Siparişi dosyaya yaz
					if (writeSuccess) {
						JOptionPane.showMessageDialog(frame, "Checkout successful! Order ID: " + order.getId() + "\nOrder details saved.", "Checkout Complete", JOptionPane.INFORMATION_MESSAGE); // İngilizce'ye çevrildi
					} else {
						JOptionPane.showMessageDialog(frame, "Checkout successful but order details could not be saved to file. Order ID: " + order.getId(), "File Write Error", JOptionPane.WARNING_MESSAGE); // İngilizce'ye çevrildi
					}
					updateShoppingCartList(selectedCustomer); // Sepet listesini güncelle (boşalacak)
					updateMarketItemList(); // Market listesini güncelle
				} else {
					JOptionPane.showMessageDialog(frame, "Checkout failed. Cart might be empty or an error occurred.", "Checkout Failed", JOptionPane.ERROR_MESSAGE); // İngilizce'ye çevrildi
				}
			}
		});
		btnCheckout.setBounds(245, 200, 110, 23); // Genişlik ayarlandı
		ShoppingPanel.add(btnCheckout);
		
		cbCustomers.setBounds(130, 11, 170, 22);
		contentPanel.add(cbCustomers);
		
		JLabel lblSelectCustomer = new JLabel("Select Customer:"); // İngilizce'ye çevrildi
		lblSelectCustomer.setBounds(10, 15, 110, 14);
		contentPanel.add(lblSelectCustomer);
		
		JButton btnSelectCustomer = new JButton("Select"); // İngilizce'ye çevrildi
		btnSelectCustomer.setBounds(310, 11, 89, 23);
		contentPanel.add(btnSelectCustomer);
		
		JButton btnNewButton = new JButton("Add Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mcf.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(411, 11, 119, 23);
		contentPanel.add(btnNewButton);
		
		btnSelectCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer selectedCustomer = (Customer) cbCustomers.getSelectedItem(); // Seçilen müşteriyi al
				if (selectedCustomer != null) {
					lblNameSurname.setText(selectedCustomer.getName()); // İsim ve adresi güncelle
					lblAddress.setText(selectedCustomer.getAddress());
					updateShoppingCartList(selectedCustomer); // Müşterinin sepetini göster
					enableShoppingPanel(); // Alışveriş panelini aktif et
				}
			}
		});
		
		disableShoppingPanel(); // Başlangıçta alışveriş paneli pasif olsun
		
	}
	public JList<Item> getListMarketItems() {
		return listMarketItems;
	}

	// Alışveriş panelini ve içindekileri pasif yapar
	private void disableShoppingPanel() {
		ShoppingPanel.setEnabled(false);
		Component[] components = ShoppingPanel.getComponents();
		for (Component component : components) {
			component.setEnabled(false);
		}
		listMarketItems.setEnabled(false);
		listShoppingCart.setEnabled(false);
	}

	// Alışveriş panelini ve içindekileri aktif yapar
	private void enableShoppingPanel() {
		ShoppingPanel.setEnabled(true);
		Component[] components = ShoppingPanel.getComponents();
		for (Component component : components) {
			component.setEnabled(true);
		}
		listMarketItems.setEnabled(true);
		listShoppingCart.setEnabled(true);
	}

	// Müşterinin sepet listesini günceller
	private void updateShoppingCartList(Customer customer) {
		shoppingCartListModel.clear();
		if (customer != null && customer.getShoppingCart() != null) {
			Map<String, Item> aggregatedCartForDisplay = new HashMap<>();
			Collection<Item> itemsFromActualCart = customer.getShoppingCart().getItems();

			for (Item itemInstanceFromCart : itemsFromActualCart) {
				Item itemInDisplayAggregator = aggregatedCartForDisplay.get(itemInstanceFromCart.getId());

				if (itemInDisplayAggregator != null) {
					itemInDisplayAggregator.setStock(itemInDisplayAggregator.getStock() + itemInstanceFromCart.getStock());
				} else {
					Item newDisplayItem = itemInstanceFromCart.createCartCopy();
					newDisplayItem.setStock(itemInstanceFromCart.getStock()); 
					aggregatedCartForDisplay.put(newDisplayItem.getId(), newDisplayItem);
				}
			}

			for (Item displayItem : aggregatedCartForDisplay.values()) {
				shoppingCartListModel.addElement(displayItem);
			}
		}
	}


	private void updateMarketItemList() {
		if (listMarketItems.getModel() instanceof DefaultListModel) {
			DefaultListModel<Item> marketModel = (DefaultListModel<Item>) listMarketItems.getModel();
			listMarketItems.repaint();
		}
	}
	

	public JComboBox<Customer> getCbCustomers() {
		return cbCustomers;
	}
}
