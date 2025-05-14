package virtualMarket.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

import virtualMarket.customer.Customer;
import virtualMarket.customer.CustomerSys;
import virtualMarket.inventory.InventorySystem;
import virtualMarket.items.Item;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;


public class VMarketMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean isAdmin = false;
		
	CustomerPanelFrame cpf;
	MarketAdminPanelFrame mapf;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	
	/**
	 * Create the frame.
	 */
	public VMarketMainFrame() {
		
		VMarketMainFrame thisFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		cpf = new CustomerPanelFrame(this);
		mapf = new MarketAdminPanelFrame(this);

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(79, 384, 175, 51);
		contentPane.add(btnLogin);
		
		JLabel marketIcon = new JLabel("");
		marketIcon.setIcon(new ImageIcon(VMarketMainFrame.class.getResource("/images/Adsız.png")));
		marketIcon.setBounds(27, 11, 296, 281);
		contentPane.add(marketIcon);
		
		JRadioButton rdCustomer = new JRadioButton("Customer");
		rdCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAdmin = false;
			}
		});
		rdCustomer.setSelected(true);
		buttonGroup.add(rdCustomer);
		rdCustomer.setBounds(214, 321, 109, 23);
		contentPane.add(rdCustomer);
		
		JRadioButton rdMarketAdmin = new JRadioButton("Market Admin");
		rdMarketAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAdmin = true;
			}
		});
		buttonGroup.add(rdMarketAdmin);
		rdMarketAdmin.setBounds(27, 321, 109, 23);
		contentPane.add(rdMarketAdmin);
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(thisFrame, "Is admin? : " + isAdmin);
				
				if (!isAdmin) {
					cpf.fillCustomerFrameCustomerCB();
					cpf.fillCustomerFrameItemList();
					cpf.setVisible(true);
					dispose();
				}else {
					
					mapf.setVisible(true);
					dispose();
				}
			}
		});
		

	}
	

}
