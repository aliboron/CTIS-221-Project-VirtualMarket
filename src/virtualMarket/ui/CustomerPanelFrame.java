package virtualMarket.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

import virtualMarket.authentication.*;
import virtualMarket.items.*;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;



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
    
	/**
	 * Create the frame.
	 */
	
	public CustomerPanelFrame(VMarketMainFrame vmmf) {
		CustomerPanelFrame frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		btnGoBack = new JButton("<--");
		btnGoBack.setBounds(10, 10, 89, 23);
		contentPanel.add(btnGoBack);
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vmmf.setVisible(true);
				dispose();
			}
		});
		
		ShoppingPanel = new JPanel();
		ShoppingPanel.setBounds(0, 0, 584, 361);
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
		
		JList listMarketItems = new JList();
		listMarketItems.setBounds(10, 82, 230, 268);
		ShoppingPanel.add(listMarketItems);
		
		JLabel lblItemList = new JLabel("Item List:");
		lblItemList.setBounds(10, 57, 72, 14);
		ShoppingPanel.add(lblItemList);
		
		btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBounds(250, 99, 100, 23);
		ShoppingPanel.add(btnAddToCart);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(360, 82, 214, 268);
		ShoppingPanel.add(scrollPane);
		
		JList listShoppingCart = new JList();
		scrollPane.setViewportView(listShoppingCart);
		
		btnReduceItemByOne = new JButton("Reduce One");
		btnReduceItemByOne.setBounds(250, 133, 100, 23);
		ShoppingPanel.add(btnReduceItemByOne);
		
		btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setBounds(250, 166, 100, 23);
		ShoppingPanel.add(btnRemoveItem);
		
		
	}
}
